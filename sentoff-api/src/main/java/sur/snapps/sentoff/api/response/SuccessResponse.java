package sur.snapps.sentoff.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sur
 * @since 05/04/2016
 */
public class SuccessResponse extends RestResponse {

    private List<JsonMessage> messages = new ArrayList<>();

    public SuccessResponse() {
        super(ResponseStatus.SUCCESS);
    }

    public SuccessResponse(List<JsonMessage> messages) {
        super(ResponseStatus.SUCCESS);
        this.messages = messages;
    }

    public List<JsonMessage> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public boolean hasMessages() {
        return !messages.isEmpty();
    }

    public String getFieldMessage(String field, MessageType type) {
        for (JsonMessage message : messages) {
            if (message.getField().equals(field)
                    && message.getType().equals(type)) {
                return message.getMessage();
            }
        }
        return null;
    }

    @JsonIgnore
    public Number getGeneratedId() {
        for (JsonMessage message : messages) {
            if (message.isGeneratedId()) {
                return Integer.valueOf(message.getMessage());
            }
        }
        return null;
    }
}
