package sur.snapps.sentoff.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.store.JsonAddress;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.api.JsonReference;
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

    public StoreLocation map(JsonStoreDetails storeDetails, JsonReference storeReference) {
        if (storeDetails == null && storeReference == null) {
            return null;
        }
        if (storeDetails != null) {
            StoreLocation storeLocation = new StoreLocation();
            JsonAddress address = storeDetails.getAddress();
            storeLocation.setName(storeDetails.getName() + " " + address.getCity());
            storeLocation.setCity(address.getCity());
            storeLocation.setCountry(address.getCountry());
            storeLocation.setStore(storeMapper.map(storeDetails));
            return storeLocation;
        }
        Number storeId = typeConverter.toInteger(storeReference.getId());
        return storeLocationRepository.findById(storeId);
    }
}
