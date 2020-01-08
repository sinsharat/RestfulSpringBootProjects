package com.sharat.restfulwebservice.usermanage;

import java.util.Date;

public class ExceptionsResponse {

	private Date timestamp;
	
	private String message;
	
	private String details;

	public ExceptionsResponse(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExceptionsResponse [timestamp=");
		builder.append(timestamp);
		builder.append(", message=");
		builder.append(message);
		builder.append(", details=");
		builder.append(details);
		builder.append("]");
		return builder.toString();
	}
	
}
