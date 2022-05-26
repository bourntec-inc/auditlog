package com.bourntec.logging.dto.request;


import java.io.Serializable;


public class AuditLog implements Serializable{

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;

	String loggerId;
	String userId;
	String action;
	String actionMessage;
	 String createdDateTime;
	String payload;
	String oldPayload;
	String recordType;//module
	String reqType;//get,post,put
	String recordId;
	long execTime;
	


	public AuditLog(String userId,String action,String actionMessage,String payload,
			String oldPayload,String recordType, String reqType,String recordId,long execTime){
		if(userId.isEmpty()) {
			System.out.println("userId is mandiatory for AuditLog.");
		}
		if(action.isEmpty()) {
			System.out.println("action is mandiatory for AuditLog.");
		}
		if(payload.isEmpty()) {
			System.out.println("payload is mandiatory for AuditLog.");
		}
		if(recordType.isEmpty()) {
			System.out.println("recordType(module) is mandiatory for AuditLog.");
		}
		if(reqType.isEmpty()) {
			System.out.println("reqType(GET,POST,PUT,DELETE) is mandiatory for AuditLog.");
		}
		if(recordId.isEmpty()) {
			System.out.println("recordId is mandiatory for AuditLog.");
		}
		this.userId=userId;
		this.action=action;
		this.payload=payload;
		this.recordType=recordType;
		this.reqType=reqType;
		this.recordId=recordId;
		this.oldPayload=oldPayload;
		this.payload=payload;
		
		
		
	}
	public AuditLog()
	{}


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

	public long getExecTime() {
		return execTime;
	}


	public void setExecTime(long execTime) {
		this.execTime = execTime;
	}


	public String getReqType() {
		return reqType;
	}


	public void setReqType(String reqType) {
		this.reqType = reqType;
	}
}
