package sur.snapps.sentoff.domain;

/**
 * @author sur
 * @since 15/04/2016
 */
public class StoreMother {

    public static Store colruyt() {
        return colruyt(null);
    }

    public static Store colruyt(Number id) {
        Store store = new Store();
        store.setId(id);
        store.setName("Colruyt");
        store.setType(StoreType.WAREHOUSE);
        return store;
    }
}
