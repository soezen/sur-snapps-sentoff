package sur.snapps.sentoff.domain.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.store.JsonStore;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.rest.util.TypeConverter;

/**
 * @author rogge
 * @since 2/04/2016.
 */
@Component
public class StoreMapper {

    @Autowired
    private TypeConverter typeConverter;

    public Store map(JsonStore jsonStore) {
        if (jsonStore == null) {
            return null;
        }
        Store store = new Store();
        store.setName(jsonStore.getName());
        store.setType(typeConverter.toStoreType(jsonStore.getType()));
        return store;
    }
}
