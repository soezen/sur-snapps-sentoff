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

    public SuccessResponse(Number generatedId) {
        super(ResponseStatus.SUCCESS);
        addMessage(MessageType.GENERATED_ID, "id", generatedId.toString());
    }

    public void addMessage(MessageType type, String field, String message) {
        messages.add(new JsonMessage(type, field, message));
    }

    public List<JsonMessage> getMessages() {
        return Collections.unmodifiableList(messages);
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
