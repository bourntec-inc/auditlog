package com.bourntec.logging.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bourntec.logging.dto.request.AuditLog;
import com.bourntec.logging.redis.RedisSender;
import com.bourntec.logging.service.LoggingService;
@Component("RedisServiceImpl")
public class RedisServiceImpl implements LoggingService {

	
	@Autowired
	RedisSender sender;
	
	@Override
	public void log(AuditLog log) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
		log.setCreatedDateTime(formatter.format(new Date()));		
		sender.sendDataToRedisQueue(log);
	}
	 public String getValue(){
	        return "some ddddddddddddddvalue";
	    }
}
