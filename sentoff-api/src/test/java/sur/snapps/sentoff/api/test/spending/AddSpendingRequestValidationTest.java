package sur.snapps.sentoff.api.test.spending;

import org.junit.Before;
import org.junit.Test;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AddSpendingRequestValidationTest {

    private Validator validator;

    @Before
    public void createValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void validateDate_valid() {
        Set<ConstraintViolation<AddSpendingRequest>> violations = validator.validateValue(AddSpendingRequest.class, "date", String.valueOf(System.currentTimeMillis()));
        assertTrue(violations.isEmpty());
    }

    @Test
    public void validateDate_invalid_nullValue() {
        Set<ConstraintViolation<AddSpendingRequest>> violations = validator.validateValue(AddSpendingRequest.class, "date", null);
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validateDate_invalid_emptyValue() {
        Set<ConstraintViolation<AddSpendingRequest>> violations = validator.validateValue(AddSpendingRequest.class, "date", "  ");
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validateDate_invalid_notNumber() {
        Set<ConstraintViolation<AddSpendingRequest>> violations = validator.validateValue(AddSpendingRequest.class, "date", "test");
        assertFalse(violations.isEmpty());
    }

    @Test
    public void validateDate_invalid_notPositiveNumber() {
        Set<ConstraintViolation<AddSpendingRequest>> violations = validator.validateValue(AddSpendingRequest.class, "date", "-45646544");
        assertFalse(violations.isEmpty());
    }
}