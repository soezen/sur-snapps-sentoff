package sur.snapps.sentoff.rest;

import org.springframework.stereotype.Component;
import sur.snapps.sentoff.domain.StoreLocation;

/**
 * @author sur
 * @since 14/04/2016
 */
@Component
public class LinkFactory {

    public String updateStoreLink(StoreLocation storeLocation, Number storeLocationId) {
        return "/sentoff/stores/replaceStoreLocation?removeId=" + storeLocation.getId() + "&replaceId=" + storeLocationId;
    }
}
