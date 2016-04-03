package sur.snapps.sentoff.rest.test.assertion;

import sur.snapps.sentoff.api.spending.AddSpendingResponse;

import static org.junit.Assert.*;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public class AddSpendingResponseAssertion {

    private AddSpendingResponse response;

    public AddSpendingResponseAssertion(AddSpendingResponse response) {
        assertNotNull(response);
        this.response = response;
    }

    public AddSpendingResponseAssertion assertSuccess() {
        assertFalse("Expected response not to have any errors", response.hasErrors());
        assertNotNull(response.getId());
        return this;
    }

    public Number getSpendingId() {
        return response.getId();
    }

    public AddSpendingResponseAssertion assertFailure() {
        assertTrue("Expected response to contain errors", response.hasErrors());
        return this;
    }

    public AddSpendingResponseAssertion assertErrorOnField(String field, String error) {
        assertTrue("Expected field " + field + " to have an error", response.hasErrorOnField(field));
        assertEquals(error, response.getFieldError(field).getKey());
        return this;
    }
}
