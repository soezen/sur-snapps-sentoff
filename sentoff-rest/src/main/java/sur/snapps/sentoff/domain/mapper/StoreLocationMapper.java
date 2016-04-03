package sur.snapps.sentoff.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.store.JsonAddress;
import sur.snapps.sentoff.api.store.JsonStore;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.rest.util.TypeConverter;

/**
 * @author rogge
 * @since 2/04/2016.
 */
@Component
public class StoreLocationMapper {

    @Autowired
    private TypeConverter typeConverter;

    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    public StoreLocation map(JsonStore store) {
        if (store == null || (store.getDetails() == null && store.getReference() == null)) {
            return null;
        }
        if (store.getDetails() != null) {
            JsonStoreDetails storeDetails = store.getDetails();
            StoreLocation storeLocation = new StoreLocation();
            JsonAddress address = storeDetails.getAddress();
            storeLocation.setName(storeDetails.getName() + " " + address.getCity());
            storeLocation.setCity(address.getCity());
            storeLocation.setCountry(address.getCountry());
            storeLocation.setStore(storeMapper.map(storeDetails));
            return storeLocation;
        }
        Number storeId = typeConverter.toInteger(store.getReference().getId());
        return storeLocationRepository.findById(storeId);
    }
}
