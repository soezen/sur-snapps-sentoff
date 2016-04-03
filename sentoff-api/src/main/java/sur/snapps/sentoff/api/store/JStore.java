package sur.snapps.sentoff.api.store;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import sur.snapps.sentoff.api.error.FieldError;

/**
 * @author rogge
 * @since 2/04/2016.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JsonStoreReference.class, name = "reference"),
        @JsonSubTypes.Type(value = JsonStore.class, name = "details")
})
public interface JStore {
}
