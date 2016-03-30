package sur.snapps.sentoff.rest.spending;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rogge
 * @since 26/03/2016.
 */
public class Spending {

    private Number id;
    private Date date;
    private BigDecimal amount;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
