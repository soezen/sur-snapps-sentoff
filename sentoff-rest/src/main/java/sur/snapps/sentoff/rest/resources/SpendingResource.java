package sur.snapps.sentoff.rest.resources;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpendingResource extends ResourceSupport {

	private final LocalDate date;
	private final BigDecimal amount;
	
	@JsonCreator
	public SpendingResource(
			@JsonProperty("date") LocalDate date,
			@JsonProperty("amount") BigDecimal amount) {
		this.date = date;
		this.amount = amount;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}
	
	public LocalDate getDate() {
		return date;
	}
}
