package sur.snapps.sentoff.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sur
 * @since 05/04/2016
 */
public class JsonMessage {

    private MessageType type;
    private String field;
    private String message;

    public JsonMessage(
        @JsonProperty("type") MessageType type,
        @JsonProperty("field") String field,
        @JsonProperty("message") String message) {
        this.type = type;
        this.field = field;
        this.message = message;
    }

    public MessageType getType() {
        return type;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    @JsonIgnore
    public boolean isGeneratedId() {
        return "id".equals(field)
            && type.equals(MessageType.GENERATED_ID);
    }
}
