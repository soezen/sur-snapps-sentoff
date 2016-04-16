package sur.snapps.sentoff.rest.test;

import org.junit.Test;

/**
 * @author sur
 * @since 16/04/2016
 */
public class SwaggerIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void swaggerApiAvailable() {
        String swaggerApi = getString("/sentoff/api/swagger.json");
        System.out.println(swaggerApi);
    }
}
