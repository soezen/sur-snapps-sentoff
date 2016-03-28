package sur.snapps.sentoff.api.spending;

import sur.snapps.sentoff.api.RestResponse;

/**
 * @author rogge
 * @since 26/03/2016.
 */
public class AddSpendingResponse extends RestResponse {

    private Number id;
    private AddSpendingRequest request;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public AddSpendingRequest getRequest() {
        return request;
    }

    public void setRequest(AddSpendingRequest request) {
        this.request = request;
    }
}
