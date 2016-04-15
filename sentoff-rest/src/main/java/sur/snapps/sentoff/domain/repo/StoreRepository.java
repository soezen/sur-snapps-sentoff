package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.table.Tables;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rogge
 * @since 2/04/2016.
 */
@Repository
public class StoreRepository extends AbstractRepository {

    @Autowired
    private StoreRowMapper rowMapper;

    @Autowired
    public StoreRepository(DataSource dataSource) {
        super(dataSource);
    }

    public void addStore(Store store) {
        Number generatedKey = insert(store).into(Tables.STORES);
        store.setId(generatedKey);
    }

    public Store findById(Number id) {
        String sql = "SELECT * FROM stores WHERE id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getNamedParameterJdbcTemplate()
            .queryForObject(sql, params, rowMapper);
    }
}
