package sur.snapps.sentoff.domain.check;

import sur.snapps.sentoff.api.JsonRequest;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sur
 * @since 12/04/2016
 */
public class IgnoredValuesCheck implements ICheck {
    @Override
    public boolean appliesTo(JsonRequest request) {
        return true;
    }

    @Override
    public List<JsonMessage> check(JsonRequest request) {
        List<JsonMessage> messages = new ArrayList<>();

        for (String ignoredField : request.getIgnoredFields()) {
            messages.add(new JsonMessage(MessageType.IGNORED_FIELD, ignoredField, "field ignored"));
        }
        return messages;
    }
}
