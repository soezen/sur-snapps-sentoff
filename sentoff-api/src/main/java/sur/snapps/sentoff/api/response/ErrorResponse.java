package sur.snapps.sentoff.api.response;

import sur.snapps.sentoff.api.error.Error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author sur
 * @since 05/04/2016
 */
public class ErrorResponse extends RestResponse {


    private List<Error> errors = new ArrayList<>();

    public ErrorResponse(List<Error> errors) {
        super(ResponseStatus.ERROR);
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return Collections.unmodifiableList(errors);
    }

}
