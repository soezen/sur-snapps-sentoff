package sur.snapps.sentoff.domain.account;

import sur.snapps.sentoff.domain.repo.Row;

public class Account implements Row {

	private Number id;
	private String name;
	private Number owner;
	
	public Number getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Number getOwner() {
		return owner;
	}
	
	public void setId(Number id) {
		this.id = id;
	}
	
	public void setOwner(Number owner) {
		this.owner = owner;
	}
}
