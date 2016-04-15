package sur.snapps.sentoff.domain.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.rest.LinkFactory;

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

    @Autowired
    private LinkFactory linkFactory;

    @Override
    public boolean appliesTo(Object domainObject) {
        return getStoreLocation(domainObject) != null;
    }

    @Override
    public List<JsonMessage> check(Object domainObject) {
        List<JsonMessage> messages = new ArrayList<>();
        StoreLocation storeLocation = getStoreLocation(domainObject);
        Number similarStoreId = storeLocationRepository.findMostSimilar(storeLocation);
        if (similarStoreId != null) {
            messages.add(new JsonMessage(MessageType.DUPLICATE_STORE_LOCATION, "store", linkFactory.updateStoreLink(storeLocation, similarStoreId)));
        }
        return messages;
    }

    private StoreLocation getStoreLocation(Object domainObject) {
        if (domainObject instanceof Spending) {
            return ((Spending) domainObject).getStoreLocation();
        }
        return null;
    }
}