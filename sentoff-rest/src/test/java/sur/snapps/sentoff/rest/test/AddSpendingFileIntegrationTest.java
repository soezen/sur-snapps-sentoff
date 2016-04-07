package sur.snapps.sentoff.rest.test;

import com.google.common.io.Resources;
import org.junit.Test;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.test.assertion.RestResponseAssertion;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingFileIntegrationTest extends AbstractIntegrationTest {

    private static final String JSON_FOLDER = "sur/snapps/sentoff/rest/test/json/add_spending/";

    @Test
    public void success_minimalRequest() throws Exception {
        Number id = postAddSpendingRequest("minimal.json")
                .assertSuccess()
                .getGeneratedId();

        assertDatabaseTable(Tables.PURCHASES)
                .hasNumberOfRows(1)
                .existsRowWithValues(id, "date", "2016-03-27")
                .existsRowWithValues(id, "amount", "12.23");
    }

    @Test
    public void success_withStoreReference() throws Exception {
        insertStoreLocationWithId(1);

        Number id = postAddSpendingRequest("store_reference.json")
                .assertSuccess()
                .getGeneratedId();

        assertDatabaseTable(Tables.PURCHASES)
                .hasNumberOfRows(1)
                .existsRowWithValues(id, "date", "2016-03-27")
                .existsRowWithValues(id, "amount", "12.23")
                .existsRowWithValues(id, "store_location_id", "1");
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(1);
    }

    @Test
    public void success_maximalRequest() throws Exception {
        Number id = postAddSpendingRequest("maximal.json")
                .assertSuccess()
                .getGeneratedId();

        assertDatabaseTable(Tables.PURCHASES)
                .hasNumberOfRows(1)
                .existsRowWithValues(id, "date", "2016-03-27")
                .existsRowWithValues(id, "amount", "12.23")
                .existsRowWithValues(id, "store_location_id", "1");
        assertDatabaseTable(Tables.STORE_LOCATIONS)
                .hasNumberOfRows(1)
                .existsRowWithValues(1, "name", "Colruyt Harelbeke")
                .existsRowWithValues(1, "city", "Harelbeke")
                .existsRowWithValues(1, "country", "BE")
                .existsRowWithValues(1, "store_id", "1");
        assertDatabaseTable(Tables.STORES)
                .hasNumberOfRows(1)
                .existsRowWithValues(1, "name", "Colruyt")
                .existsRowWithValues(1, "type", "WAREHOUSE");
    }

    @Test
    public void failure_missingDate() throws Exception {
        postAddSpendingRequest("missing_date.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_nullDate() throws Exception {
        postAddSpendingRequest("null_date.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_invalidDate() throws Exception {
        postAddSpendingRequest("invalid_date.json")
                .assertFailure()
                .assertErrorOnField("date", "invalid_format");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_nullAmount() throws Exception {
        postAddSpendingRequest("null_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_missingAmount() throws Exception {
        postAddSpendingRequest("missing_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_invalidAmount() throws Exception {
        postAddSpendingRequest("invalid_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "invalid_format");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_multipleMissingValues() throws Exception {
        postAddSpendingRequest("multiple_missing_values.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_multipleNullValues() throws Exception {
        postAddSpendingRequest("multiple_null_values.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");

        assertDatabaseEmpty();
    }

    @Test
    public void failure_multipleInvalidValues() throws Exception {
        postAddSpendingRequest("multiple_invalid_values.json")
                .assertFailure()
                .assertErrorOnField("date", "invalid_format")
                .assertErrorOnField("amount", "invalid_format");

        assertDatabaseEmpty();
    }

    private RestResponseAssertion postAddSpendingRequest(String fileName) throws IOException {
        String fileContent = Resources.toString(Resources.getResource(JSON_FOLDER + fileName), Charset.defaultCharset());
        RestResponse response = postJson("/spending/add", fileContent);
        return new RestResponseAssertion(response);
    }
}
