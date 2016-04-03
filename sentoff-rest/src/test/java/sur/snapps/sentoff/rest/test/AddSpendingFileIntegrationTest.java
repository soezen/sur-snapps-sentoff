package sur.snapps.sentoff.rest.test;

import com.google.common.io.Resources;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import sur.snapps.sentoff.api.spending.AddSpendingResponse;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.StoreType;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.domain.repo.StoreRepository;
import sur.snapps.sentoff.domain.repo.Table;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.test.assertion.AddSpendingResponseAssertion;

import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingFileIntegrationTest extends AbstractIntegrationTest {

    private static final String JSON_FOLDER = "sur/snapps/sentoff/rest/test/json/add_spending/";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @Before
    public void clearTables() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate, Tables.PURCHASES.getTableName());
        JdbcTestUtils.deleteFromTables(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName());
        JdbcTestUtils.deleteFromTables(jdbcTemplate, Tables.STORES.getTableName());
    }

    @Test
    public void success_minimalRequest() throws Exception {
        postAddSpendingRequest("minimal.json")
                .assertSuccess();

        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void success_withStoreReference() throws Exception {
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

        postAddSpendingRequest("store_reference.json")
                .assertSuccess();

        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORES.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void success_maximalRequest() throws Exception {
        postAddSpendingRequest("maximal.json")
            .assertSuccess();

        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORES.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_missingDate() throws Exception {
        postAddSpendingRequest("missing_date.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_nullDate() throws Exception {
        postAddSpendingRequest("null_date.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_invalidDate() throws Exception {
        postAddSpendingRequest("invalid_date.json")
                .assertFailure()
                .assertErrorOnField("date", "invalid_format");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_nullAmount() throws Exception {
        postAddSpendingRequest("null_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "not_null");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_missingAmount() throws Exception {
        postAddSpendingRequest("missing_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "not_null");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_invalidAmount() throws Exception {
        postAddSpendingRequest("invalid_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "invalid_format");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_multipleMissingValues() throws Exception {
        postAddSpendingRequest("multiple_missing_values.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_multipleNullValues() throws Exception {
        postAddSpendingRequest("multiple_null_values.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void failure_multipleInvalidValues() throws Exception {
        postAddSpendingRequest("multiple_invalid_values.json")
                .assertFailure()
                .assertErrorOnField("date", "invalid_format")
                .assertErrorOnField("amount", "invalid_format");

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    private AddSpendingResponseAssertion postAddSpendingRequest(String fileName) throws IOException {
        String fileContent = Resources.toString(Resources.getResource(JSON_FOLDER + fileName), Charset.defaultCharset());
        AddSpendingResponse response = postJson("/spending/add", AddSpendingResponse.class, fileContent);
        return new AddSpendingResponseAssertion(response);
    }
}
