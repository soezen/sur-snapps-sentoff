package sur.snapps.sentoff.rest.test.spending;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.mapper.SpendingMapper;
import sur.snapps.sentoff.domain.mapper.StoreLocationMapper;
import sur.snapps.sentoff.domain.mapper.StoreMapper;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.StoreLocationRowMapper;
import sur.snapps.sentoff.domain.repo.StoreRepository;
import sur.snapps.sentoff.rest.util.TypeConverter;
import sur.snapps.sentoff.test.TestDataSourceConfig;

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
@ContextConfiguration(
    classes = {
        StoreLocationMapper.class, StoreMapper.class, SpendingMapper.class, TypeConverter.class,
        StoreLocationRowMapper.class,
        StoreLocationRepository.class, StoreRepository.class, TestDataSourceConfig.class},
    loader = AnnotationConfigContextLoader.class)
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
