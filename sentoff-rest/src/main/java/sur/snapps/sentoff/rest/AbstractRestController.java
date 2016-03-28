package sur.snapps.sentoff.rest;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.google.common.base.CaseFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import sur.snapps.sentoff.api.RestResponse;
import sur.snapps.sentoff.api.error.Error;
import sur.snapps.sentoff.api.error.FieldError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author rogge
 * @since 27/03/2016.
 */
public abstract class AbstractRestController {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResponse handleGeneralException(Exception ex) {
        List<Error> errors = new ArrayList<>();
        errors.add(createError(ex));
        return new RestResponse(errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<Error> errors = ex.getBindingResult().getFieldErrors().stream().map((Function<org.springframework.validation.FieldError, Error>) this::createError).collect(Collectors.toList());
        return new RestResponse(errors);
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
        return new RestResponse(errors);
    }

    private Error createError(org.springframework.validation.FieldError fieldError) {
        return new FieldError(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, fieldError.getCode()), fieldError.getField(), fieldError.getDefaultMessage());
    }

    private Error createError(Exception ex) {
        return new Error(ex.getClass().getSimpleName(), ex.getMessage());
    }

}
