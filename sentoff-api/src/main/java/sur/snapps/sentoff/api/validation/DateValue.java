package sur.snapps.sentoff.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author sur
 * @since 29/03/2016
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { })
@Digits(integer = 13, fraction = 0)
@DecimalMin(value = "0")
public @interface DateValue {

    String message() default "{sur.snapps.sentoff.api.validation.date_value.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
