package sur.snapps.sentoff.rest.spending;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.rest.util.TypeConverter;

/**
 * @author sur
 * @since 30/03/2016
 */
@Component
public class SpendingMapper {

    @Autowired
    private TypeConverter typeConverter;

    public Spending map(AddSpendingRequest request) {
        Spending spending = new Spending();
        spending.setDate(typeConverter.toDate(request.getDate()));
        spending.setAmount(typeConverter.toBigDecimal(request.getAmount()));
        return spending;
    }
}
