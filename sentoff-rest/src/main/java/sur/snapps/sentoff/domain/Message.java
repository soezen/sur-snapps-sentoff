package sur.snapps.sentoff.domain;

import java.util.Date;

import sur.snapps.sentoff.domain.repo.Row;

public class Message implements Row {

	private Number id;
	private Date date;
	private String uri;
	private String method;
	private String payload;

	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Number getId() {
		return id;
	}
	
	public void setId(Number id) {
		this.id = id;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getPayload() {
		return payload;
	}
	
	public void setPayload(String payload) {
		this.payload = payload;
	}
	
	public String getUri() {
		return uri;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
}
