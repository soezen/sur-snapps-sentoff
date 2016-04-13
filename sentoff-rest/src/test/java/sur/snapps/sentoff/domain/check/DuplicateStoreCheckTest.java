package sur.snapps.sentoff.domain.check;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.StoreLocationRowMapper;
import sur.snapps.sentoff.domain.repo.StoreRepository;
import sur.snapps.sentoff.rest.test.mother.StoreLocationMother;
import sur.snapps.sentoff.test.TestDataSourceConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
    classes = {
        DuplicateStoreCheck.class,
        StoreLocationRepository.class, StoreLocationRowMapper.class,
        StoreRepository.class, TestDataSourceConfig.class},
    loader = AnnotationConfigContextLoader.class)
public class DuplicateStoreCheckTest {

    @Autowired
    private DuplicateStoreCheck checkService;

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @Test
    public void check_duplicateStoreFound() {
        storeLocationRepository.addStoreLocation(StoreLocationMother.colruytDeerlijk());

        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withStoreName("Colruyt").withStoreCity("Deerlijk").withStoreCountry("BE").build();
        List<JsonMessage> result = checkService.check(request);
        assertEquals(1, result.size());
        JsonMessage message = result.get(0);
        assertEquals(MessageType.DUPLICATE_STORE_LOCATION, message.getType());
        assertEquals("store", message.getField());
    }

    @Test
    public void check_duplicateStoreNotFound() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withStoreName("Colruyt").withStoreCity("Deerlijk").withStoreCountry("BE").build();
        List<JsonMessage> result = checkService.check(request);
        assertTrue(result.isEmpty());
    }

}