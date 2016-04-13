package sur.snapps.sentoff.rest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import sur.snapps.sentoff.api.error.Error;
import sur.snapps.sentoff.api.error.FieldError;
import sur.snapps.sentoff.api.response.ErrorResponse;
import sur.snapps.sentoff.api.response.FailureResponse;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.rest.util.ErrorMessages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public abstract class AbstractRestController {

    private Log LOG = LogFactory.getLog(AbstractRestController.class);

    @Autowired
    private ErrorMessages errorMessages;

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse handleGeneralException(Exception ex) {
        List<Error> errors = new ArrayList<>();
        errors.add(createError(ex));
        return new ErrorResponse(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errors = ex.getBindingResult().getFieldErrors().stream().map(this::createError).collect(Collectors.toList());
        return new FailureResponse(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public RestResponse handleInvalidFormatException(HttpMessageNotReadableException ex) {
        List<Error> errors = new ArrayList<>();
        if (ex.getCause() instanceof InvalidFormatException) {
            InvalidFormatException invalidFormatException = (InvalidFormatException) ex.getCause();
            errors.add(new FieldError("invalid_format", invalidFormatException.getPath().get(0).getFieldName(), invalidFormatException.getOriginalMessage()));
        } else {
            return handleGeneralException(ex);
        }
        return new FailureResponse(errors);
    }

    private Error createError(org.springframework.validation.FieldError fieldError) {
        LOG.error(fieldError);
        String errorCode = errorMessages.translate("error.code." + fieldError.getCode());
        String errorMessage = errorMessages.translate("error.msg." + fieldError.getCode(), fieldError.getDefaultMessage());
        return new FieldError(errorCode, fieldError.getField(), errorMessage);
    }

    private Error createError(Exception ex) {
        LOG.error("Exception during rest call", ex);
        return new Error(ex.getClass().getSimpleName(), ex.getMessage());
    }

}
