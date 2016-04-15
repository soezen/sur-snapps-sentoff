package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.table.Tables;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Repository
public class SpendingRepository extends AbstractRepository {

    @Autowired
    private SpendingRowMapper rowMapper;

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @Autowired
    public SpendingRepository(DataSource dataSource) {
        super(dataSource);
    }

    public Spending findById(Number id) {
        String sql = "SELECT * FROM purchases p JOIN store_locations sl ON sl.id = p.store_location_id JOIN stores s ON s.id = sl.store_id WHERE p.id = :id";
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return getNamedParameterJdbcTemplate()
            .queryForObject(sql, params, rowMapper);
    }

    public void addSpending(Spending spending) {
        if (storeLocationIsNew(spending)) {
            storeLocationRepository.addStoreLocation(spending.getStoreLocation());
        }
        Number generatedKey = insert(spending).into(Tables.PURCHASES);
        spending.setId(generatedKey);
    }

    private boolean storeLocationIsNew(Spending spending) {
        return spending.getStoreLocation() != null
                && spending.getStoreLocationId() == null;
    }
}
