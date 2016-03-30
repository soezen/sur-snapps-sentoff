package sur.snapps.sentoff.rest.test;

import org.junit.Test;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.spending.AddSpendingResponse;
import sur.snapps.sentoff.rest.test.assertion.AddSpendingResponseAssertion;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;

import static org.junit.Assert.assertNotNull;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void success_minimalRequest() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().build();
        postAddSpendingRequest(request)
                .assertSuccess();
    }

    @Test
    public void success_maximalRequest() {
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest().withStoreName("Colruyt").withStoreLocation("Store in City").build();
        postAddSpendingRequest(request)
                .assertSuccess();
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
