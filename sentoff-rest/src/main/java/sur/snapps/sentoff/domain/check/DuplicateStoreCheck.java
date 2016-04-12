package sur.snapps.sentoff.domain.check;

import sur.snapps.sentoff.api.JsonRequest;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.store.JsonStoreDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sur
 * @since 12/04/2016
 */
public class DuplicateStoreCheck implements ICheck {

    @Override
    public boolean appliesTo(JsonRequest request) {
        return getStoreDetails(request) != null;
    }

    @Override
    public List<JsonMessage> check(JsonRequest request) {
        List<JsonMessage> messages = new ArrayList<>();
        JsonStoreDetails storeDetails = getStoreDetails(request);
        return messages;
    }

    private JsonStoreDetails getStoreDetails(JsonRequest request) {
        if (request instanceof AddSpendingRequest) {
            return ((AddSpendingRequest) request).getStoreDetails();
        }
        return null;
    }
}
