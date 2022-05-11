package com.bourntec.logging.service;

import com.bourntec.logging.dto.request.AuditLog;

/**
 * Service interface for LoggingService
 * @author Babu
 *
 */
public interface LoggingService {
	/**
	 * This method will call from client project
	 * @param log
	 */
	 void log(AuditLog log);
	 
	
}
