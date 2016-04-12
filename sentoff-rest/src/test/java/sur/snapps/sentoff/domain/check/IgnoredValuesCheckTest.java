package sur.snapps.sentoff.domain.check;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import sur.snapps.sentoff.api.JsonRequest;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.MessageType;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class IgnoredValuesCheckTest {

    private static final String jsonWithoutIgnoredFields = "{\"name\":\"Natalia\"}";
    private static final String jsonWithIgnoredFields = "{\"name\":\"Natalia\", \"other\":\"test\", \"complexOther\":{\"bla\":\"foo\",\"bar\":1}}";

    private IgnoredValuesCheck checkService = new IgnoredValuesCheck();

    @Test
    public void check_withIgnoredValues() throws Exception {
        JsonExample request = new ObjectMapper().readValue(jsonWithIgnoredFields, JsonExample.class);
        List<JsonMessage> result = checkService.check(request);
        assertEquals(2, result.size());
        for (JsonMessage message : result) {
            assertEquals(MessageType.IGNORED_FIELD, message.getType());
            assertTrue("other".equals(message.getField()) || "complexOther".equals(message.getField()));
        }
    }

    @Test
    public void check_withoutIgnoredValues() throws Exception {
        JsonExample request = new ObjectMapper().readValue(jsonWithoutIgnoredFields, JsonExample.class);
        List<JsonMessage> result = checkService.check(request);
        assertTrue(result.isEmpty());
    }

    public static class JsonExample extends JsonRequest {

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}