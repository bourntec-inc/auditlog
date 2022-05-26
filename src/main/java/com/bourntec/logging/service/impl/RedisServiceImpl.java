package com.bourntec.logging.service.impl;

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
		sender.sendDataToRedisQueue(log);
	}
}
