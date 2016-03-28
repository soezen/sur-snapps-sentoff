package sur.snapps.sentoff.api.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author rogge
 * @since 27/03/2016.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = FieldError.class, name = "field_error")
})
public class Error {

    private String key;
    private String message;

    @JsonCreator
    public Error(@JsonProperty("key") String key, @JsonProperty("message") String message) {
        this.key = key;
        this.message = message;
    }

    public String getKey() {
        return key;
    }

    public String getMessage() {
        return message;
    }

    public boolean isErrorOnField(String field) {
        return false;
    }
}
