package sur.snapps.sentoff.rest.util;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sur.snapps.sentoff.domain.StoreType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author sur
 * @since 30/03/2016
 */
@Component
public class TypeConverter {

    public StoreType toStoreType(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return StoreType.valueOf(value);
    }

    public Date toDate(String value) {
        return new Date(Long.parseLong(value));
    }

    public Integer toInteger(String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Integer.parseInt(value);
    }

    public BigDecimal toBigDecimal(String value) {
        return BigDecimal.valueOf(Double.parseDouble(value));
    }
}
