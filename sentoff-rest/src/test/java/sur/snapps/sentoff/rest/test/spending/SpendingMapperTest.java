package sur.snapps.sentoff.rest.test.spending;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;
import sur.snapps.sentoff.rest.spending.Spending;
import sur.snapps.sentoff.rest.spending.SpendingMapper;
import sur.snapps.sentoff.rest.util.TypeConverter;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * @author sur
 * @since 08/03/2016
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpendingMapper.class, TypeConverter.class})
public class SpendingMapperTest {

    @Autowired
    private SpendingMapper mapper;

    @Test
    public void success_minimal() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().build();
        Spending spending = mapper.map(request);

        assertNotNull(spending);
        assertNotNull(spending.getAmount());
        assertNotNull(spending.getDate());

        assertNull(spending.getId());

        assertEquals(BigDecimal.valueOf(1.0), spending.getAmount());
        assertEquals(new Date(123), spending.getDate());
    }
}
