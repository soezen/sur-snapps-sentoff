package sur.snapps.sentoff.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.JsonReference;
import sur.snapps.sentoff.api.validation.PresentInDB;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author sur
 * @since 11/04/2016
 */
@Component
public class PresentInDBValidator implements ConstraintValidator<PresentInDB, JsonReference> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private String tableName;

    @Override
    public void initialize(PresentInDB constraintAnnotation) {
        this.tableName = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(JsonReference value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        
        Integer count = jdbcTemplate.queryForObject("select count(*) from " + tableName + " where id = ?", Integer.class, value.getId());
        return count > 0;
    }
}
