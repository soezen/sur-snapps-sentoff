package sur.snapps.sentoff.domain;

import sur.snapps.sentoff.domain.repo.Row;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author rogge
 * @since 26/03/2016.
 */
public class Spending implements Row {

    private Number id;
    private Date date;
    private BigDecimal amount;
    private StoreLocation storeLocation;

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

    public StoreLocation getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(StoreLocation storeLocation) {
        this.storeLocation = storeLocation;
    }

    public Number getStoreLocationId() {
        return storeLocation == null ? null : storeLocation.getId();
    }
}
