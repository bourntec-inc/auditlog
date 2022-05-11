package com.bourntec.logging.s3;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class AmazonS3Client {

    private AmazonS3 s3client;

    @Value("${s3.endpointUrl}")
    private String endpointUrl;

    @Value("${s3.bucketName}")
    private String bucketName;

    @Value("${s3.accessKeyId}")
    private String accessKeyId;

    @Value("${s3.secretKey}")
    private String secretKey;

    @Value("${s3.region}")
    private String region;
    
   
    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKeyId, this.secretKey);
        this.s3client = AmazonS3ClientBuilder
                .standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
    }

    //used
    public void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(bucketName, fileName, file);
    }
 
	public byte[] readS3Bucket(String key) {
        try {
            S3Object object = s3client.getObject(bucketName, key);
            S3ObjectInputStream objectContent = object.getObjectContent();
            byte[] imageArray = IOUtils.toByteArray(objectContent);
            objectContent.close();
            return imageArray;
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to read the file", e);
        }
    }
	public void createFile( String fileName,String content) {
	   String finalContent="";
		if(s3client.doesObjectExist(bucketName, fileName)) {
			String responseBody = getS3ObjectContentAsString(bucketName,fileName);
			finalContent=responseBody+"\r\n"+content;
		}
		else {
			
			finalContent=content;
		}
		 ObjectMetadata metadata = new ObjectMetadata();
		 byte[] buffer = finalContent.getBytes();

		    metadata.setContentLength(buffer.length);
		    metadata.setContentType("text/plain");
		  InputStream emptyContent = new ByteArrayInputStream(finalContent.getBytes());
		    // create a PutObjectRequest passing the folder name suffixed by /
		    PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
		    		fileName , emptyContent, metadata);

		    // send request to S3 to create folder
		     s3client.putObject(putObjectRequest);
		 
	}
	public String getS3ObjectContentAsString(String bucketName, String key) {
        try {
            if (key.startsWith("/")) {
                key = key.substring(1);
            }
            if (key.endsWith("/")) {
                key = key.substring(0, key.length());
            }
            try (InputStream is = s3client.getObject(bucketName, key).getObjectContent()) {
                return StreamUtils.copyToString(is, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
