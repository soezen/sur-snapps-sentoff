package sur.snapps.sentoff.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import sur.snapps.sentoff.api.error.Error;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author rogge
 * @since 27/03/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestResponse {

    private List<Error> errors = new ArrayList<>();

    public RestResponse() { }

    public RestResponse(List<Error> errors) {
        this.errors = errors;
    }

    public List<Error> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
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
