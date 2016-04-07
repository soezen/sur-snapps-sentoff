package sur.snapps.sentoff.api.response;

import sur.snapps.sentoff.api.error.Error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sur
 * @since 05/04/2016
 */
public class FailureResponse extends RestResponse {


    private List<Error> errors = new ArrayList<>();

    public FailureResponse() {
        super(ResponseStatus.FAILURE);
    }

    public FailureResponse(List<Error> errors) {
        super(ResponseStatus.FAILURE);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return Collections.unmodifiableList(errors);
    }


    public boolean hasErrorOnField(String field) {
        return getFieldError(field) != null;
    }

    public Error getFieldError(String field) {
        for (Error error : errors) {
            if (error.isErrorOnField(field)) {
                return error;
            }
        }
        return null;
    }
}
