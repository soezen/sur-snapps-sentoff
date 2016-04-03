package sur.snapps.sentoff.rest.test;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.spending.AddSpendingResponse;
import sur.snapps.sentoff.domain.repo.Table;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.test.assertion.AddSpendingResponseAssertion;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Before
    public void clearDatabase() {
        JdbcTestUtils.deleteFromTables(jdbcTemplate,
                Tables.PURCHASES.getTableName(),
                Tables.STORE_LOCATIONS.getTableName(),
                Tables.STORES.getTableName());
    }

    @Test
    public void success_minimalRequest() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().build();
        postAddSpendingRequest(request)
                .assertSuccess();

        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.STORES.getTableName()));
        assertEquals(0, countRowsInTable(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void success_withStoreReference() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
                .withStoreReference(1)
                .build();
        postAddSpendingRequest(request)
                .assertSuccess();

        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORES.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
    }

    @Test
    public void success_maximalRequest() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
                .withStoreName("Colruyt")
                .withStoreCity("Harelbeke").build();
        postAddSpendingRequest(request)
                .assertSuccess();

        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.PURCHASES.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORE_LOCATIONS.getTableName()));
        assertEquals(1, countRowsInTable(jdbcTemplate, Tables.STORES.getTableName()));
    }

    @Test
    public void failure_nullDate() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withDate(null).build();
        postAddSpendingRequest(request)
                .assertFailure()
                .assertErrorOnField("date", "not_null");
    }

    @Test
    public void failure_nullAmount() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withAmount(null).build();
        postAddSpendingRequest(request)
                .assertFailure()
                .assertErrorOnField("amount", "not_null");
    }

    @Test
    public void failure_multipleErrors() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
                .withAmount(null)
                .withDate(null)
                .build();
        postAddSpendingRequest(request)
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");
    }

    private AddSpendingResponseAssertion postAddSpendingRequest(AddSpendingRequest request) {
        AddSpendingResponse response = postJson("/spending/add", AddSpendingResponse.class, request);
        return new AddSpendingResponseAssertion(response);
    }
}
