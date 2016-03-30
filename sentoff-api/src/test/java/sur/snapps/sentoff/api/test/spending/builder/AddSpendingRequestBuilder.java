package sur.snapps.sentoff.api.test.spending.builder;

import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.spending.Store;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingRequestBuilder {

    private AddSpendingRequest request;
    private Store store;

    private AddSpendingRequestBuilder() {
        request = new AddSpendingRequest();
        store = new Store();
    }

    /**
     * Create new {@link AddSpendingRequestBuilder} with all required fields already set to a default value.
     */
    public static AddSpendingRequestBuilder minimalAddSpendingRequest() {
        return new AddSpendingRequestBuilder()
                .withDate(new Date(123))
                .withAmount(BigDecimal.ONE);
    }

    public AddSpendingRequestBuilder withDate(Date date) {
        request.setDate(date == null ? null : String.valueOf(date.getTime()));
        return this;
    }

    public AddSpendingRequestBuilder withAmount(BigDecimal amount) {
        request.setAmount(amount == null ? null : amount.toString());
        return this;
    }

    public AddSpendingRequestBuilder withStoreLocation(String location) {
        store.setLocation(location);
        request.setStore(store);
        return this;
    }

    public AddSpendingRequestBuilder withStoreName(String name) {
        store.setName(name);
        request.setStore(store);
        return this;
    }

    public AddSpendingRequest build() {
        return request;
    }
}
