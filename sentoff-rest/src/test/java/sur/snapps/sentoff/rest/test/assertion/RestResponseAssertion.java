package sur.snapps.sentoff.rest.test.assertion;

import sur.snapps.sentoff.api.response.FailureResponse;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.response.SuccessResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class RestResponseAssertion {

    private RestResponse response;

    public RestResponseAssertion(RestResponse response) {
        assertNotNull(response);
        this.response = response;
    }

    public RestResponseAssertion assertSuccess() {
        assertTrue("Expected response not to have any errors", response.isSuccessful());
        return this;
    }

    public RestResponseAssertion assertMessage(MessageType type, String field) {
        assertTrue(response instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response;
        assertTrue(successResponse.hasMessages());
        assertNotNull(successResponse.getFieldMessage(field, type));
        return this;
    }

    public RestResponseAssertion assertFailure() {
        assertFalse("Expected response to contain errors", response.isSuccessful());
        return this;
    }

    public RestResponseAssertion assertErrorOnField(String field, String error) {
        assertTrue(response instanceof FailureResponse);
        FailureResponse failureResponse = (FailureResponse) response;
        assertTrue("Expected field " + field + " to have an error", failureResponse.hasErrorOnField(field));
        assertEquals(error, failureResponse.getFieldError(field).getKey());
        return this;
    }

    public Number getGeneratedId() {
        assertTrue(response instanceof SuccessResponse);
        SuccessResponse successResponse = (SuccessResponse) response;
        return successResponse.getGeneratedId();
    }
}
