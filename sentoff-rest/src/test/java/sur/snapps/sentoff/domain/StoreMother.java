package sur.snapps.sentoff.domain;

/**
 * @author sur
 * @since 15/04/2016
 */
public class StoreMother {

    public static Store colruyt() {
        Store store = new Store();
        store.setId(999);
        store.setName("Colruyt");
        store.setType(StoreType.WAREHOUSE);
        return store;
    }
}
