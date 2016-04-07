package sur.snapps.sentoff.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author rogge
 * @since 27/03/2016.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "status")
@JsonSubTypes({
    @JsonSubTypes.Type(value = SuccessResponse.class, name = "SUCCESS"),
    @JsonSubTypes.Type(value = SuccessResponse.class, name = "SUCCESS_WITH_WARNINGS"),
    @JsonSubTypes.Type(value = ErrorResponse.class, name = "ERROR"),
    @JsonSubTypes.Type(value = FailureResponse.class, name = "FAILURE")
})
public abstract class RestResponse {

    private ResponseStatus status;

    public RestResponse(ResponseStatus status) {
        this.status = status;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    @JsonIgnore
    public boolean isSuccessful() {
        return status.equals(ResponseStatus.SUCCESS);
    }

}
