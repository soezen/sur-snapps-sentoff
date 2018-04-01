package sur.snapps.sentoff.domain;

import java.time.LocalDateTime;

import sur.snapps.sentoff.domain.repo.Row;

public class Message implements Row {

	private Number id;
	private LocalDateTime requestTimestamp;
	private String requestUri;
	private String requestMethod;
	private String requestPayload;
	private LocalDateTime responseTimestamp;
	private int responseStatus;
	private String responsePayload;


	@Override
	public Number getId() {
		return id;
	}
	
	public void setId(Number id) {
		this.id = id;
	}

	public LocalDateTime getRequestTimestamp() {
		return requestTimestamp;
	}
	
	public void setRequestTimestamp(LocalDateTime requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}
	
	public String getRequestUri() {
		return requestUri;
	}
	
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
	public String getRequestMethod() {
		return requestMethod;
	}
	
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	
	public String getRequestPayload() {
		return requestPayload;
	}
	
	public void setRequestPayload(String requestPayload) {
		this.requestPayload = requestPayload;
	}
	
	public int getResponseStatus() {
		return responseStatus;
	}
	
	public void setResponseStatus(int responseStatus) {
		this.responseStatus = responseStatus;
	}
	
	public LocalDateTime getResponseTimestamp() {
		return responseTimestamp;
	}
	
	public void setResponseTimestamp(LocalDateTime responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}
	
	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}
	
	public String getResponsePayload() {
		return responsePayload;
	}
}
