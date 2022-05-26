package com.bourntec.logging.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

import com.bourntec.logging.dto.request.AuditLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface FileService {
	/**
	 * 
	 * @param log
	 */
	public void persistLog(AuditLog log);
	
	default String logPattern(AuditLog auditLog) {
        ObjectMapper objMapper = new ObjectMapper();
        try {
        	auditLog.setLoggerId(UUID.randomUUID().toString());
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        	formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        	auditLog.setCreatedDateTime(formatter.format(new Date()));
		
			return objMapper.writeValueAsString(auditLog);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;

	}

}
