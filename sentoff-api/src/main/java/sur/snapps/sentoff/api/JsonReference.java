package sur.snapps.sentoff.api;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.constraints.NotNull;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public class JsonReference {

    @NotNull
    private String id;

    public JsonReference(String id) {
        this.id = id;
    }

    public JsonReference(int id) {
        this.id = String.valueOf(id);
    }

    @JsonValue
    public String getId() {
        return id;
    }

}
