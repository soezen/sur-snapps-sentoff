package sur.snapps.sentoff.rest.test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.StoreType;
import sur.snapps.sentoff.domain.repo.Row;
import sur.snapps.sentoff.domain.repo.SpendingRepository;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.StoreRepository;
import sur.snapps.sentoff.domain.repo.Table;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.Application;
import sur.snapps.sentoff.rest.test.assertion.DatabaseAssertion;

import static org.junit.Assert.assertEquals;

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
    protected StoreLocationRepository storeLocationRepository;

    @Autowired
    protected StoreRepository storeRepository;

    @Autowired
    protected SpendingRepository spendingRepository;

    private String getBaseUrl() {
        return "http://localhost:" + port;
    }

    @Before
    public void clearTables() {
        clearTable(Tables.PURCHASES);
        clearTable(Tables.STORE_LOCATIONS);
        clearTable(Tables.STORES);
    }

    public void clearTable(Table<?> table) {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, table.getTableName());
        jdbcTemplate.execute("alter table " + table.getTableName() + " alter column id restart with 1");
    }

    protected RestResponse postJson(final String requestMappingUrl, Object body) {
        final TestRestTemplate template = new TestRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        final HttpEntity<Object> requestEntity = new HttpEntity<>(body, headers);

        try {
            final ResponseEntity<RestResponse> entity = template.exchange(getBaseUrl() + requestMappingUrl, HttpMethod.POST, requestEntity, RestResponse.class);
            return entity.getBody();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected String getString(final String requestUrl) {
        final TestRestTemplate template = new TestRestTemplate();
        ResponseEntity<String> responseEntity = template.getForEntity(getBaseUrl() + requestUrl, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        return responseEntity.getBody();
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
        storeLocation.setId(id);
        storeLocationRepository.addStoreLocation(storeLocation);
    }

    protected void insertStoreLocation(StoreLocation storeLocation) {
        storeLocationRepository.addStoreLocation(storeLocation);
    }

    protected void insertStore(Store store) {
        storeRepository.addStore(store);
    }

    protected void assertDatabaseEmpty() {
        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(0);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(0);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(0);
    }
}
