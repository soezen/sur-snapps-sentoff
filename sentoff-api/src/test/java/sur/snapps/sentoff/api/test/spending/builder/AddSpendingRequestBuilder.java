package sur.snapps.sentoff.api.test.spending.builder;

import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.store.JsonAddress;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.api.JsonReference;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingRequestBuilder {

    private AddSpendingRequest request;
    private JsonStoreDetails storeDetails;
    private JsonAddress storeAddress;

    private AddSpendingRequestBuilder() {
        request = new AddSpendingRequest();
        storeDetails = new JsonStoreDetails();
        storeAddress = new JsonAddress();
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

    public AddSpendingRequestBuilder withStoreType(String type) {
        storeDetails.setType(type);
        request.setStoreDetails(storeDetails);
        return this;
    }

    public AddSpendingRequestBuilder withStoreName(String name) {
        storeDetails.setName(name);
        request.setStoreDetails(storeDetails);
        return this;
    }

    public AddSpendingRequestBuilder withStoreCity(String city) {
        storeAddress.setCity(city);
        storeDetails.setAddress(storeAddress);
        request.setStoreDetails(storeDetails);
        return this;
    }

    public AddSpendingRequestBuilder withStoreReference(int reference) {
        request.setStoreReference(new JsonReference(String.valueOf(reference)));
        return this;
    }

    public AddSpendingRequest build() {
        return request;
    }
}
