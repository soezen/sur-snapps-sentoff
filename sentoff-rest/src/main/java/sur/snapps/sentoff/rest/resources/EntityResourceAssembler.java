package sur.snapps.sentoff.rest.resources;

import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import sur.snapps.sentoff.domain.repo.Row;

@Component
public abstract class EntityResourceAssembler<E extends Row> extends ResourceAssemblerSupport<E, EntityResource<E>> {
	
	private Class<?> controllerClass;
	
	public EntityResourceAssembler(Class<?> controllerClass, Class<EntityResource<E>> resourceType) {
		super(controllerClass, resourceType);
		this.controllerClass = controllerClass;
	}

	@Override
	public EntityResource<E> toResource(E entity) {
		EntityResource<E> resource = createResourceWithId(entity.getId(), entity);
		resource.setEntity(entity);
		return resource;
	}

	@Override
    protected EntityResource<E> createResourceWithId(Object id, E entity, Object... parameters) {
        
		EntityResource<E> instance = instantiateResource(entity);

        instance.add(
                JaxRsLinkBuilder.linkTo(controllerClass, parameters)
                        .slash(id)
                        .withSelfRel());
        return instance;
    }
}
