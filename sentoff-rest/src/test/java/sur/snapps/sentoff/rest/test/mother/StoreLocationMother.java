package sur.snapps.sentoff.rest.test.mother;

import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreLocation;

/**
 * @author rogge
 * @since 11/04/2016.
 */
public class StoreLocationMother {

    public static StoreLocation colruytDeerlijk() {
        return new StoreLocationBuilder()
                .withName("Colruyt Deerlijk")
                .withStoreName("Colruyt")
                .withCity("Deerlijk")
                .withCountry("BE")
                .build();
    }

    private static class StoreLocationBuilder {
        private StoreLocation storeLocation = new StoreLocation();
        private Store store = new Store();

        StoreLocationBuilder withCountry(String country) {
            storeLocation.setCountry(country);
            return this;
        }

        StoreLocationBuilder withStoreName(String name) {
            store.setName(name);
            storeLocation.setStore(store);
            return this;
        }

        StoreLocationBuilder withName(String name) {
            storeLocation.setName(name);
            return this;
        }

        StoreLocationBuilder withCity(String city) {
            storeLocation.setCity(city);
            return this;
        }

        StoreLocation build() {
            return storeLocation;
        }
    }
}
