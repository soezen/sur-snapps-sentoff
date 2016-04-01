package sur.snapps.sentoff.api.spending;

import com.fasterxml.jackson.annotation.JsonProperty;
import sur.snapps.sentoff.api.validation.AmountValue;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author sur
 * @since 30/03/2016
 */
public class Payment {

    @NotNull
    // TODO verify account id exists in domain
    private String accountId;

    @NotNull
    @AmountValue
    private String amount;

    public String getAccountId() {
        return accountId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
