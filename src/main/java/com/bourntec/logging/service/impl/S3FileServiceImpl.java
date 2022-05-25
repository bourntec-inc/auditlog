package com.bourntec.logging.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bourntec.logging.config.ApplicationConfig;
import com.bourntec.logging.dto.request.AuditLog;
import com.bourntec.logging.s3.AmazonS3Client;
import com.bourntec.logging.service.FileService;
@Component("S3FileServiceImpl")
public class S3FileServiceImpl implements FileService{

	@Value("${audit.log.method}")
	String type;
	@Value("${audit.log.path}")
	String logPath;
	@Autowired
	AmazonS3Client amazonClient;
	
	@Override
	public void persistLog(AuditLog log) {
		if(type!=null && type.equals(ApplicationConfig.s3Type)) {
			
		    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		    Date date = new Date();  
		    String str=this.logPattern(log);
		    amazonClient.createFile(logPath+"/"+formatter.format(date)+"/"+log.getUserId()+"_"+log.getRecordType()+"_"+log.getReqType()+"_"+formatter.format(date), str);
		}
	}
}
