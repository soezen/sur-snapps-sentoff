package sur.snapps.sentoff.api.spending;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.api.JsonReference;
import sur.snapps.sentoff.api.validation.AmountValue;
import sur.snapps.sentoff.api.validation.DateValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddSpendingRequest {

    @NotNull
    @DateValue
    private String date;
    @NotNull
    @AmountValue
    private String amount;

    @Valid
    @JsonProperty("store")
    private JsonStoreDetails storeDetails;

    @Valid
    @JsonProperty("storeRef")
    private JsonReference storeReference;

    @Valid
    private List<Payment> payments = Collections.emptyList();

    @Valid
    @JsonProperty("products")
    private List<PurchasedProduct> purchasedProducts = Collections.emptyList();

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public List<Payment> getPayments() {
        return Collections.unmodifiableList(payments);
    }

    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    public List<PurchasedProduct> getPurchasedProducts() {
        return Collections.unmodifiableList(purchasedProducts);
    }

    public void addPurchasedProduct(PurchasedProduct purchasedProduct) {
        purchasedProducts.add(purchasedProduct);
    }

    public JsonStoreDetails getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(JsonStoreDetails storeDetails) {
        this.storeDetails = storeDetails;
    }

    public JsonReference getStoreReference() {
        return storeReference;
    }

    public void setStoreReference(JsonReference storeReference) {
        this.storeReference = storeReference;
    }
}
