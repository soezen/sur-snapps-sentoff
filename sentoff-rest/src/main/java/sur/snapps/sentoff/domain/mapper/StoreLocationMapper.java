package sur.snapps.sentoff.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.store.JStore;
import sur.snapps.sentoff.api.store.JsonAddress;
import sur.snapps.sentoff.api.store.JsonStore;
import sur.snapps.sentoff.api.store.JsonStoreReference;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.StoreRepository;
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

    public StoreLocation map(JStore store) {
        if (store == null) {
            return null;
        }
        if (store instanceof JsonStore) {
            JsonStore jsonStore = (JsonStore) store;
            StoreLocation storeLocation = new StoreLocation();
            JsonAddress address = jsonStore.getAddress();
            storeLocation.setName(jsonStore.getName() + " " + address.getCity());
            storeLocation.setCity(address.getCity());
            storeLocation.setCountry(address.getCountry());
            storeLocation.setStore(storeMapper.map(jsonStore));
            return storeLocation;
        }
        if (store instanceof JsonStoreReference) {
            Number storeId = typeConverter.toInteger(((JsonStoreReference) store).getId());
            return storeLocationRepository.findById(storeId);
        }
        throw new IllegalArgumentException("Unsupported implementation of JStore: " + store.getClass());
    }
}
