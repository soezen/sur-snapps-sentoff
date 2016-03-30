package sur.snapps.sentoff.api.spending;

import com.fasterxml.jackson.annotation.JsonProperty;
import sur.snapps.sentoff.api.validation.AmountValue;
import sur.snapps.sentoff.api.validation.DateValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author rogge
 * @since 26/03/2016.
 */
public class AddSpendingRequest {

    @NotNull
    @DateValue
    private String date;
    @NotNull
    @AmountValue
    private String amount;

    @Valid
    private Store store;

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

    public void setStore(Store store) {
        this.store = store;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public Store getStore() {
        return store;
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
}
