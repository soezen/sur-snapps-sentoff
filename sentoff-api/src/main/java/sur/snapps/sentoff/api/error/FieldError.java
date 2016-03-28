package sur.snapps.sentoff.api.error;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class FieldError extends Error {

    private String field;

    @JsonCreator
    public FieldError(@JsonProperty("key") String key, @JsonProperty("field") String field, @JsonProperty("message") String message) {
        super(key, message);
        this.field = field;
    }

    @Override
    public boolean isErrorOnField(String field) {
        return this.field.equals(field);
    }

    public String getField() {
        return field;
    }
}
