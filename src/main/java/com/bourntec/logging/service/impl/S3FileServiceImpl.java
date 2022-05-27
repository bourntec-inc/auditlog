package com.bourntec.logging.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
	@Value("${audit.log.folderpattern}")
	private String folderPattern;

	@Value("${audit.log.filenamepattern}")
	String fileNamePattern;
	@Autowired
	AmazonS3Client amazonClient;
	
	@Override
	public void persistLog(AuditLog log) {
		if(type!=null && type.equals(ApplicationConfig.s3Type)) {
		    String str=this.logPattern(log);
		   // amazonClient.createFile(logPath+"/"+formatter.format(date)+"/"+log.getUserId()+"_"+log.getRecordType()+"_"+log.getReqType()+"_"+formatter.format(date), str);
		    amazonClient.createFile(logPath+"/"+getFolderPattern(log)+getFileNamePattern(log), str);
		}
	}
   private String getPattern(String patternString,AuditLog log) {
	   if(!patternString.isEmpty()) {
           	if(patternString!=null && patternString.trim().contains(ApplicationConfig.loggerid)) {
           		patternString=patternString.replace(ApplicationConfig.loggerid, log.getLoggerId()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.userId)) {
           		patternString=patternString.replace(ApplicationConfig.userId, log.getUserId()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.action)) {
           		patternString=patternString.replace(ApplicationConfig.action, log.getAction()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.actionMessage)) {
           		patternString=patternString.replace(ApplicationConfig.actionMessage, log.getActionMessage()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.recordType)) {
           		patternString=patternString.replace(ApplicationConfig.recordType, log.getRecordType()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.reqType)) {
           		patternString=patternString.replace(ApplicationConfig.reqType, log.getReqType()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.recordId)) {
           		patternString=patternString.replace(ApplicationConfig.recordId, log.getRecordId()).trim();
           	}
        	if(patternString!=null && patternString.trim().contains(ApplicationConfig.date)) {
        		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
      		    formatter.setTimeZone(TimeZone.getTimeZone("UTC"));  
      		    Date date = new Date();  
      		    patternString=patternString.replace(ApplicationConfig.date,formatter.format(date)).trim();
           	}
       }
	   return patternString;
   }
	private String getFileNamePattern(AuditLog log) {
		String fileName=getPattern(fileNamePattern,log);
		fileName=fileName.substring(0, fileName.length() - 1);
         return  fileName;
	}

	private String getFolderPattern(AuditLog log) {
	     return  getPattern(folderPattern,log);
	}
}
