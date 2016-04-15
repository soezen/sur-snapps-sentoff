package sur.snapps.sentoff.domain.check;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.SpendingMother;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.StoreLocationRowMapper;
import sur.snapps.sentoff.domain.repo.StoreRepository;
import sur.snapps.sentoff.rest.LinkFactory;
import sur.snapps.sentoff.domain.StoreLocationMother;
import sur.snapps.sentoff.test.TestDataSourceConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {
        DuplicateStoreCheck.class, LinkFactory.class,
        StoreLocationRepository.class, StoreLocationRowMapper.class,
        StoreRepository.class, TestDataSourceConfig.class},
    loader = AnnotationConfigContextLoader.class)
public class DuplicateStoreCheckTest {

    @Autowired
    private DuplicateStoreCheck checkService;

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    private Spending spending;

    @Before
    public void setup() {
        spending = SpendingMother.spend5eurosOn4jan2016atColruytDeerlijk();
    }

    @Test
    public void check_duplicateStoreFound() {
        storeLocationRepository.addStoreLocation(StoreLocationMother.colruytDeerlijk());

        List<JsonMessage> result = checkService.check(spending);
        assertEquals(1, result.size());
        JsonMessage message = result.get(0);
        assertEquals(MessageType.DUPLICATE_STORE_LOCATION, message.getType());
        assertEquals("store", message.getField());
    }

    @Test
    public void check_duplicateStoreNotFound() {
        List<JsonMessage> result = checkService.check(spending);
        assertTrue(result.isEmpty());
    }

    @Test
    public void appliesTo_trueForSpendingWithStoreLocation() {
        assertTrue(checkService.appliesTo(spending));
    }

    @Test
    public void appliesTo_falseForSpendingWithoutStoreLocation() {
        assertFalse(checkService.appliesTo(new Spending()));
    }

    @Test
    public void appliesTo_falseForOtherObjects() {
        assertFalse(checkService.appliesTo(new AddSpendingRequest()));
    }
}