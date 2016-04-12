package sur.snapps.sentoff.domain.check;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;
import sur.snapps.sentoff.rest.Application;
import sur.snapps.sentoff.rest.config.CheckConfig;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CheckConfig.class)
public class DuplicateStoreCheckTest {

    @Autowired
    private DuplicateStoreCheck checkService;

    @Test
    public void check_duplicateStoreFound() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().build();
        List<JsonMessage> result = checkService.check(request);
        assertEquals(1, result.size());
        JsonMessage message = result.get(0);
        assertEquals(MessageType.DUPLICATE_STORE, message.getType());
        assertEquals("store", message.getField());
    }

    @Test
    public void check_duplicateStoreNotFound() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().build();
        List<JsonMessage> result = checkService.check(request);
        assertTrue(result.isEmpty());
    }
}