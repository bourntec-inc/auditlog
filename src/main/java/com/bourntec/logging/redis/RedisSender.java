package com.bourntec.logging.redis;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.bourntec.logging.dto.request.AuditLog;
import com.google.gson.Gson;

@Component
public class RedisSender {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisSender.class);
	@Value("${redis.queue.topic}")
	String channelTopic;
	@Autowired
	private StringRedisTemplate redisTemplate;

	public void sendDataToRedisQueue(AuditLog input) {
		Gson gson = new Gson();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

		input.setCreatedDateTime(formatter.format(new Date()));
		redisTemplate.convertAndSend(channelTopic, gson.toJson(input));
		LOGGER.info("Data - " + input + " sent through Redis Topic - " + channelTopic);
	}
}