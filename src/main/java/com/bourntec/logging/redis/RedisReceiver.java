package com.bourntec.logging.redis;


import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bourntec.logging.dto.request.AuditLog;
import com.bourntec.logging.service.FileService;
import com.bourntec.logging.service.LoggingService;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class RedisReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisReceiver.class);
    @Autowired
	LoggingService loggingService;
    private AtomicInteger counter = new AtomicInteger();
    
    @Autowired
    @Qualifier("S3FileServiceImpl")
    private FileService fileService;
    public void receiveMessage(String message) {
        LOGGER.info("Received <" + message + ">");
        ObjectMapper obj=new ObjectMapper();
   // 	AuditLog auditLogObj = new AuditLog(null, null, null, null, null, null, null, null, 0) ;
		try {
			//auditLogObj = auditLogObj.toModel(message.toString());
			AuditLog auditLogObj=obj.readValue(message.toString(), AuditLog.class);
			fileService.persistLog(auditLogObj);
	        counter.incrementAndGet();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

    public int getCount() {
        return counter.get();
    }
}
