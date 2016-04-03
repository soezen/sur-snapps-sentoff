package sur.snapps.sentoff.rest.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.StoreType;
import sur.snapps.sentoff.domain.repo.Row;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.Table;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.Application;
import sur.snapps.sentoff.rest.test.assertion.DatabaseAssertion;

/**
 * @author rogge
 * @since 27/03/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest(randomPort = true)
@ActiveProfiles("test")
public abstract class AbstractIntegrationTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port + "/sentoff";
    }

    @Before
    public void clearTables() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, Tables.PURCHASES.getTableName());
        JdbcTestUtils.deleteFromTables(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName());
        JdbcTestUtils.deleteFromTables(jdbcTemplate, Tables.STORES.getTableName());
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

    protected <T extends Row> DatabaseAssertion<T> assertDatabaseTable(Table<T> table) {
        return new DatabaseAssertion<T>(jdbcTemplate, table);
    }

    protected void insertStoreLocationWithId(Number id) {
        Store store = new Store();
        store.setName("Colruyt");
        store.setType(StoreType.WAREHOUSE);
        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setName("Colruyt Harelbeke");
        storeLocation.setCity("Harelbeke");
        storeLocation.setCountry("BE");
        storeLocation.setStore(store);
        storeLocation.setId(1);
        storeLocationRepository.addStoreLocation(storeLocation);
    }

    protected void assertDatabaseEmpty() {
        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(0);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(0);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(0);
    }
}
