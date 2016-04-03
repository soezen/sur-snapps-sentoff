package sur.snapps.sentoff.api.store;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public class JsonStoreReference {

    @NotNull
    private String id;

    @JsonCreator
    public JsonStoreReference(@JsonProperty("id") String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
