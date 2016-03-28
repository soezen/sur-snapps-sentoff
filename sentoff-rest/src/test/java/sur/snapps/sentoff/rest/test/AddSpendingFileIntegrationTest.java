package sur.snapps.sentoff.rest.test;

import com.google.common.io.Resources;
import org.junit.Test;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sur.snapps.sentoff.api.spending.AddSpendingResponse;
import sur.snapps.sentoff.rest.test.assertion.AddSpendingResponseAssertion;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingFileIntegrationTest extends AbstractIntegrationTest {

    private static final String JSON_FOLDER = "sur/snapps/sentoff/rest/test/json/add_spending/";

    @Test
    public void success_minimalRequest() throws Exception {
        postAddSpendingRequest("minimal.json")
                .assertSuccess();
    }

    @Test
    public void success_maximalRequest() throws Exception {
        postAddSpendingRequest("maximal.json")
            .assertSuccess();
    }

    @Test
    public void failure_missingDate() throws Exception {
        postAddSpendingRequest("missing_date.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null");
    }

    @Test
    public void failure_nullDate() throws Exception {
        postAddSpendingRequest("null_date.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null");
    }

    @Test
    public void failure_invalidDate() throws Exception {
        postAddSpendingRequest("invalid_date.json")
                .assertFailure()
                .assertErrorOnField("date", "invalid_format");
    }

    @Test
    public void failure_nullAmount() throws Exception {
        postAddSpendingRequest("null_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "not_null");
    }

    @Test
    public void failure_missingAmount() throws Exception {
        postAddSpendingRequest("missing_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "not_null");
    }

    @Test
    public void failure_invalidAmount() throws Exception {
        postAddSpendingRequest("invalid_amount.json")
                .assertFailure()
                .assertErrorOnField("amount", "invalid_format");
    }

    @Test
    public void failure_multipleMissingValues() throws Exception {
        postAddSpendingRequest("multiple_missing_values.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");
    }

    @Test
    public void failure_multipleNullValues() throws Exception {
        postAddSpendingRequest("multiple_null_values.json")
                .assertFailure()
                .assertErrorOnField("date", "not_null")
                .assertErrorOnField("amount", "not_null");
    }

    @Test
    public void failure_multipleInvalidValues() throws Exception {
        postAddSpendingRequest("multiple_invalid_values.json")
                .assertFailure()
                .assertErrorOnField("date", "invalid_format")
                .assertErrorOnField("amount", "invalid_format");
    }

    private AddSpendingResponseAssertion postAddSpendingRequest(String fileName) throws IOException {
        String fileContent = Resources.toString(Resources.getResource(JSON_FOLDER + fileName), Charset.defaultCharset());
        AddSpendingResponse response = postJson("/spending/add", AddSpendingResponse.class, fileContent);
        return new AddSpendingResponseAssertion(response);
    }
}
