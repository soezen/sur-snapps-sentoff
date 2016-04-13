package sur.snapps.sentoff.api.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
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
@DecimalMin(value = "0")
@Digits(integer = 13, fraction = 0)
@ReportAsSingleViolation
public @interface DateValue {

    String message() default "invalid date format";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
