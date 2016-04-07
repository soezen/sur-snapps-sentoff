package sur.snapps.sentoff.api.response;

/**
 * @author sur
 * @since 05/04/2016
 */
public enum ResponseStatus {

    /**
     * Request successfully handled, no warnings.
     */
    SUCCESS,
    /**
     * Validation of request failed.
     */
    FAILURE,
    /**
     * Unexpected exception occurred during handling of request.
     */
    ERROR
}
