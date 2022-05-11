package com.bourntec.logging.dto.request;


import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;


public class AuditLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String loggerId;

	private String userId;
	private String action;
	private String actionMessage;
	private String createdDateTime;

	private String payload;

	private String oldPayload;

	private String newPayload;
	private String recordType;//module

	private String recordId;
	

	
	public String getLoggerId() {
		return loggerId;
	}


	public void setLoggerId(String loggerId) {
		this.loggerId = loggerId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getActionMessage() {
		return actionMessage;
	}


	public void setActionMessage(String actionMessage) {
		this.actionMessage = actionMessage;
	}


	public String getCreatedDateTime() {
		return createdDateTime;
	}


	public void setCreatedDateTime(String createdDateTime) {
		this.createdDateTime = createdDateTime;
	}


	public String getPayload() {
		return payload;
	}


	public void setPayload(String payload) {
		this.payload = payload;
	}


	public String getOldPayload() {
		return oldPayload;
	}


	public void setOldPayload(String oldPayload) {
		this.oldPayload = oldPayload;
	}


	public String getNewPayload() {
		return newPayload;
	}


	public void setNewPayload(String newPayload) {
		this.newPayload = newPayload;
	}


	public String getRecordType() {
		return recordType;
	}


	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}


	public String getRecordId() {
		return recordId;
	}


	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public AuditLog toModel(String logMessage) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		AuditLog auditLog = new AuditLog();
		auditLog = objectMapper.readValue(logMessage, AuditLog.class);
		return auditLog;
	}
}
