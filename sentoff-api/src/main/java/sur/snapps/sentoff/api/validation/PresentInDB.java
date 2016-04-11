package sur.snapps.sentoff.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sur
 * @since 11/04/2016
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { })
public @interface PresentInDB {

    /**
     * Table name to which this value references.
     */
    String value();

    String message() default "reference value not found in database";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
