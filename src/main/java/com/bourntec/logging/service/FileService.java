package com.bourntec.logging.service;

import com.bourntec.logging.dto.request.AuditLog;

public interface FileService {
	/**
	 * 
	 * @param log
	 */
	public void persistLog(AuditLog log);
	
    default String logPattern(AuditLog auditLog){
     String log="";
     log=log+"loggerId: "+auditLog.getLoggerId()+"\r\n"
     		+ "userId: "+auditLog.getUserId()+"\r\n"
     		+ "action: "+auditLog.getAction()+"\r\n"
     		+ "actionMessage: "+auditLog.getActionMessage()+"\r\n"
     		+ "createdDateTime: "+auditLog.getCreatedDateTime()+"\r\n"
     		+ "payload: "+auditLog.getPayload()+"\r\n"
     		+ "oldPayload: "+auditLog.getOldPayload()+"\r\n"
     		+ "newPayload: "+auditLog.getNewPayload()+"\r\n"
     		+ "recordType(module): "+auditLog.getRecordType()+"\r\n"
     		+ "recordId: "+auditLog.getRecordId()+"\r\n"
     		+ "execTime: "+auditLog.getExecTime()+"\r\n"
     		+ "requestType: "+auditLog.getReqType()+"\r\n"
     		;
     return log;
     
    }

}
