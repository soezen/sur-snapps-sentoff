package sur.snapps.sentoff.rest.test;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author sur
 * @since 16/04/2016
 */
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void swaggerApiAvailable() {
        String swaggerApi = getString("/sentoff/api/swagger.json");
        assertNotNull(swaggerApi);
    }
}
