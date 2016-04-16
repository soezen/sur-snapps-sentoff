package sur.snapps.sentoff.rest.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import sur.snapps.sentoff.api.error.Error;
import sur.snapps.sentoff.api.error.FieldError;
import sur.snapps.sentoff.api.response.FailureResponse;
import sur.snapps.sentoff.rest.util.ErrorMessages;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * @author sur
 * @since 16/04/2016
 */
@Provider
public class ValidationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

    @Autowired
    private ErrorMessages errorMessages;

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<Error> errors = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : constraintViolations) {
            String errorCode = errorMessages.translate("error.code." + violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName());
            String errorMessage = errorMessages.translate("error.msg." + errorCode, violation.getMessage());
            Iterator<Path.Node> iterator = violation.getPropertyPath().iterator();
            StringBuilder fieldBuilder = new StringBuilder();
            while (iterator.hasNext()) {
                Path.Node next = iterator.next();
                if (next.getKind() == ElementKind.PROPERTY) {
                    fieldBuilder.append(".").append(next.getName());
                }
            }
            fieldBuilder.delete(0, 1);
            errors.add(new FieldError(errorCode, fieldBuilder.toString(), errorMessage));
        }

        return Response.ok(new FailureResponse(errors))
            .build();
    }
}
