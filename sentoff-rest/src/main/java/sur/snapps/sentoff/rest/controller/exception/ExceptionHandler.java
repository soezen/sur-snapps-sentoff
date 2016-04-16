package sur.snapps.sentoff.rest.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import sur.snapps.sentoff.rest.util.ErrorMessages;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author sur
 * @since 16/04/2016
 */
@Provider
public class ExceptionHandler implements ExceptionMapper<Throwable> {

    @Autowired
    private ErrorMessages errorMessages;

    @Override
    public Response toResponse(Throwable e) {
        return Response.serverError()
            .entity(e.getMessage())
            .build();
    }
}
