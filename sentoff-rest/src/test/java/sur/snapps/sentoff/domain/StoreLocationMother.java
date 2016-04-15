package sur.snapps.sentoff.domain;

/**
 * @author rogge
 * @since 11/04/2016.
 */
public class StoreLocationMother {

    public static StoreLocation colruytDeerlijk() {
        return colruytDeerlijk(null);
    }

    public static StoreLocation colruytDeerlijk(Number id) {
        StoreLocation storeLocation = new StoreLocationBuilder()
            .withName("Colruyt Deerlijk")
            .withStore(StoreMother.colruyt(id))
            .withCity("Deerlijk")
            .withCountry("BE")
            .build();
        storeLocation.setId(id);
        return storeLocation;
    }

    public static StoreLocation colruytHarelbeke() {
        StoreLocation storeLocation = new StoreLocationBuilder()
            .withName("Colruyt Harelbeke")
            .withStore(StoreMother.colruyt())
            .withCity("Harelbeke")
            .withCountry("BE")
            .build();
        return storeLocation;
    }

    private static class StoreLocationBuilder {
        private StoreLocation storeLocation = new StoreLocation();

        StoreLocationBuilder withCountry(String country) {
            storeLocation.setCountry(country);
            return this;
        }

        StoreLocationBuilder withStore(Store store) {
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
