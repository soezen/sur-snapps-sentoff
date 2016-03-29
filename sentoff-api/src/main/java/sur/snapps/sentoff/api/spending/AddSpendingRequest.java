package sur.snapps.sentoff.api.spending;

import sur.snapps.sentoff.api.validation.AmountValue;
import sur.snapps.sentoff.api.validation.DateValue;

import javax.validation.GroupSequence;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

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

    private String location;

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }
}
