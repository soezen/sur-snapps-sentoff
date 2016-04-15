package sur.snapps.sentoff.domain.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.domain.Spending;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rogge
 * @since 11/04/2016.
 */
@Component
public class DataCheckService {

    @Autowired
    private List<ICheck> checks;

    public List<JsonMessage> check(AddSpendingRequest request, Spending spending) {
        List<JsonMessage> messages = new ArrayList<>();
        messages.add(new JsonMessage(MessageType.GENERATED_ID, "id", String.valueOf(spending.getId())));

        for (ICheck check : checks) {
            if (check.appliesTo(request)) {
                messages.addAll(check.check(request));
            } else if (check.appliesTo(spending)) {
                messages.addAll(check.check(spending));
            }
        }

        return messages;
    }
}
