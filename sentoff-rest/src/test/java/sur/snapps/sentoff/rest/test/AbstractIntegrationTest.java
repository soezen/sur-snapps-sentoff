package sur.snapps.sentoff.rest.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sur.snapps.sentoff.rest.Application;

/**
 * @author rogge
 * @since 27/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
public abstract class AbstractIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/sentoff";
    }

    protected <T> T postJson(final String requestMappingUrl, final Class<T> returnType, Object body) {
        final TestRestTemplate template = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        try {
            final ResponseEntity<T> entity = template.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.POST, requestEntity, returnType);
            return entity.getBody();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
