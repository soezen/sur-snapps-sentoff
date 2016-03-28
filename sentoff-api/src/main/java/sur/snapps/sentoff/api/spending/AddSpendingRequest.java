package sur.snapps.sentoff.api.spending;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import sur.snapps.sentoff.api.validation.ValidAmount;
import sur.snapps.sentoff.api.validation.ValidDate;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rogge
 * @since 26/03/2016.
 */
public class AddSpendingRequest {

    @NotNull
    @ValidDate
    private String date;
    @NotNull
    @ValidAmount
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
