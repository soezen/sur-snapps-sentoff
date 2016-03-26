package sur.snapps.sentoff.rest.spending;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Repository
public class SpendingRepository extends NamedParameterJdbcDaoSupport {

    @Autowired
    public SpendingRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void addSpending(Spending spending) {
        SimpleJdbcInsert sji = new SimpleJdbcInsert(getJdbcTemplate())
                .withTableName("SPENDING")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> args = Maps.newHashMap();
        args.put("test", "test");
        Number generatedKey = sji.executeAndReturnKey(args);
        spending.setId(generatedKey);
    }
}
