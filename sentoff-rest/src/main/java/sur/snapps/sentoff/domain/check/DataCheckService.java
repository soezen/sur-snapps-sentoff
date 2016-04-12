package sur.snapps.sentoff.domain.check;

import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.domain.Spending;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rogge
 * @since 11/04/2016.
 */
@Component
public class DataCheckService {

    public List<JsonMessage> check(Spending spending) {
        List<JsonMessage> messages = new ArrayList<>();
        messages.add(new JsonMessage(MessageType.GENERATED_ID, "id", String.valueOf(spending.getId())));
        return messages;
    }
}
