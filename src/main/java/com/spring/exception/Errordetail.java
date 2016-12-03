package com.spring.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Errordetail {
	private String title;
	private int status;
	private String detail;
	private long timeStamp;
	private String developerMessage;
	private Map<String, List<ValidationError>> validationErrors = new HashMap<>();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public Map<String, List<ValidationError>> getValidationErrors() {
		return validationErrors;
	}

	public void setValidationErrors(Map<String, List<ValidationError>> validationErrors) {
		this.validationErrors = validationErrors;
	}
}
