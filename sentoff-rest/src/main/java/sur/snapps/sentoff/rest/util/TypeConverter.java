package sur.snapps.sentoff.rest.util;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sur
 * @since 30/03/2016
 */
@Component
public class TypeConverter {

    public Date toDate(String value) {
        return new Date(Long.parseLong(value));
    }

    public BigDecimal toBigDecimal(String value) {
        return BigDecimal.valueOf(Double.parseDouble(value));
    }
}
