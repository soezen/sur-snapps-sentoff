package sur.snapps.sentoff.domain;

import org.apache.commons.lang3.time.DateUtils;

import java.math.BigDecimal;

/**
 * @author sur
 * @since 14/04/2016
 */
public class SpendingMother {

    // TODO make DateUtils class
    public static Spending spend5eurosOn4jan2016atColruytDeerlijk() {
        Spending spending = new Spending();
        spending.setId(1);
        spending.setAmount(BigDecimal.valueOf(5));
        try {
            spending.setDate(DateUtils.parseDate("04-01-2016", "DD-MM-YYYY"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        spending.setStoreLocation(StoreLocationMother.colruytDeerlijk());
        return spending;
    }
}
