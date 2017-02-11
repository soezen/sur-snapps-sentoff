package sur.snapps.sentoff.domain.account;

import java.math.BigDecimal;

import sur.snapps.sentoff.domain.repo.Row;

public class Account implements Row {

	private Number id;
	private String name;
	private Number owner;
	private BigDecimal balance;
	
	public Number getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
	public Number getOwner() {
		return owner;
	}
	
	public void setId(Number id) {
		this.id = id;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public void setOwner(Number owner) {
		this.owner = owner;
	}
}
