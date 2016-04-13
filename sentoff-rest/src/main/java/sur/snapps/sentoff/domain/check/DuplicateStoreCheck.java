package sur.snapps.sentoff.domain.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.JsonRequest;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sur
 * @since 12/04/2016
 */
@Component
public class DuplicateStoreCheck implements ICheck {

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @Override
    public boolean appliesTo(JsonRequest request) {
        return getStoreDetails(request) != null;
    }

    @Override
    public List<JsonMessage> check(JsonRequest request) {
        List<JsonMessage> messages = new ArrayList<>();
        JsonStoreDetails storeDetails = getStoreDetails(request);
        Number similarStoreId = storeLocationRepository.findMostSimilar(storeDetails);
        if (similarStoreId != null) {
            messages.add(new JsonMessage(MessageType.DUPLICATE_STORE_LOCATION, "store", "TODO update url similar to " + similarStoreId));
        }
        return messages;
    }

    private JsonStoreDetails getStoreDetails(JsonRequest request) {
        if (request instanceof AddSpendingRequest) {
            return ((AddSpendingRequest) request).getStoreDetails();
        }
        return null;
    }
}