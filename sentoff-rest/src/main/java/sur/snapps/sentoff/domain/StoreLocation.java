package sur.snapps.sentoff.domain;

import sur.snapps.sentoff.domain.repo.Row;

/**
 * @author sur
 * @since 01/04/2016
 */
public class StoreLocation implements Row {

    private Number id;
    private String name;
    private Store store;
    private String city;
    private String country;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getStoreId() {
        return store == null ? null : store.getId();
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
