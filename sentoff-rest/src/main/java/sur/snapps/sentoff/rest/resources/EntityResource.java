package sur.snapps.sentoff.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import sur.snapps.sentoff.domain.repo.Row;

public class EntityResource<E extends Row> extends ResourceSupport {

	private E entity;

	public EntityResource() {
		// TODO Auto-generated constructor stub
	}

	public void setEntity(E entity) {
		this.entity = entity;
	}

	@JsonUnwrapped
	public E getEntity() {
		return entity;
	}
}
