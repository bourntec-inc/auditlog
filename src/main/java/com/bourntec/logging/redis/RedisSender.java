package com.bourntec.logging.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.bourntec.logging.dto.request.AuditLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSender.class);
	@Value("${redis.queue.topic}")
	String channelTopic;
	@Autowired
	private StringRedisTemplate redisTemplate;

	public void sendDataToRedisQueue(AuditLog input) {
		  ObjectMapper objMapper = new ObjectMapper();
		try {
			redisTemplate.convertAndSend(channelTopic, objMapper.writeValueAsString(input));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		LOGGER.info("Data - " + input + " sent through Redis Topic - " + channelTopic);
	}
}