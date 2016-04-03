package sur.snapps.sentoff.api.store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;

/**
 * @author sur
 * @since 30/03/2016
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonStore {

    @Valid
    @JsonProperty("store")
    private JsonStoreDetails details;

    @Valid
    @JsonProperty("store_ref")
    private JsonStoreReference reference;

    @JsonIgnore
    @AssertTrue(message = "store.id cannot be combined with store details")
    public boolean isValid() {
        return details == null || reference == null;
    }

    public JsonStoreDetails getDetails() {
        return details;
    }

    public void setDetails(JsonStoreDetails details) {
        this.details = details;
    }

    public JsonStoreReference getReference() {
        return reference;
    }

    public void setReference(JsonStoreReference reference) {
        this.reference = reference;
    }

    // TODO remove this class and put everything in request itself
    // TODO create annotation on classlevel to validate XOR
    // TODO create abstract Json object to catch any extra properties and generate warnings
}
