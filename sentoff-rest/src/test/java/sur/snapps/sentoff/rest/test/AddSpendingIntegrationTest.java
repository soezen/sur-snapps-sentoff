package sur.snapps.sentoff.rest.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.store.JsonAddress;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.test.assertion.RestResponseAssertion;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    public void success_maximalRequest() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
                .withStoreName("Colruyt")
                .withStoreCity("Harelbeke").build();
        Number id = postAddSpendingRequest(request)
                .assertSuccess()
                .getGeneratedId();
        Number storeLocationId = 1;
        Number storeId = 1;

        assertDatabaseTable(Tables.PURCHASES)
                .hasNumberOfRows(1)
                .existsRowWithValues(id, "date", "1970-01-01")
                .existsRowWithValues(id, "amount", "1.00")
                .existsRowWithValues(id, "store_location_id", storeLocationId.toString());

        JsonStoreDetails store = request.getStoreDetails();
        JsonAddress address = store.getAddress();
        assertDatabaseTable(Tables.STORE_LOCATIONS)
                .hasNumberOfRows(1)
                .existsRowWithValues(storeLocationId, "name", store.getName() + " " + address.getCity())
                .existsRowWithValues(storeLocationId, "street", address.getStreet())
                .existsRowWithValues(storeLocationId, "number", address.getNumber())
                .existsRowWithValues(storeLocationId, "zip_code", address.getZipCode())
                .existsRowWithValues(storeLocationId, "city", address.getCity())
                .existsRowWithValues(storeLocationId, "country", address.getCountry())
                .existsRowWithValues(storeLocationId, "store_id", storeId.toString());
        assertDatabaseTable(Tables.STORES)
                .hasNumberOfRows(1)
                .existsRowWithValues(storeId, "name", store.getName())
                .existsRowWithValues(storeId, "type", store.getType());
    }

    @Test
    public void success_withStoreReference() {
        insertStoreLocationWithId(1);

        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
            .withStoreReference(1)
            .build();
        Number id = postAddSpendingRequest(request)
            .assertSuccess()
            .getGeneratedId();

        assertDatabaseTable(Tables.PURCHASES)
            .hasNumberOfRows(1)
            .existsRowWithValues(id, "date", "1970-01-01")
            .existsRowWithValues(id, "amount", "1.00")
            .existsRowWithValues(id, "store_location_id", request.getStoreReference().getId());
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(1);
    }

    @Test
    public void failure_incorrectStoreReference() {

        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
            .withStoreReference(1)
            .build();
        postAddSpendingRequest(request)
            .assertFailure()
            .assertErrorOnField("storeReference", "reference_not_found");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_nullDate() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withDate(null).build();
        postAddSpendingRequest(request)
                .assertFailure()
                .assertErrorOnField("date", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_nullAmount() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withAmount(null).build();
        postAddSpendingRequest(request)
                .assertFailure()
                .assertErrorOnField("amount", "not_null");

        assertDatabaseEmpty();
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

        assertDatabaseEmpty();
    }

    private RestResponseAssertion postAddSpendingRequest(AddSpendingRequest request) {
        RestResponse response = postJson("/spending/add", request);
        return new RestResponseAssertion(response);
    }
}
