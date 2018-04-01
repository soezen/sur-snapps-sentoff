package sur.snapps.sentoff.domain.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import sur.snapps.sentoff.domain.repo.Row;

public class Balance implements Row {

	private Number id;
	@NotNull
	private LocalDateTime timestamp;
	@NotNull
	private BigDecimal value;
	private Number accountId;

	public Number getId() {
		return id;
	}
	
	public void setId(Number id) {
		this.id = id;
	}
	
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public Number getAccountId() {
		return accountId;
	}
	
	public void setAccountId(Number accountId) {
		this.accountId = accountId;
	}
}
