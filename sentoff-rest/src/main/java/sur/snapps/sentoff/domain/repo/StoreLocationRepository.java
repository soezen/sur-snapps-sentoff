package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.table.Tables;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rogge
 * @since 2/04/2016.
 */
@Repository
public class StoreLocationRepository extends AbstractRepository {

    @Autowired
    private StoreLocationRowMapper rowMapper;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    public StoreLocationRepository(DataSource dataSource) {
        super(dataSource);
    }

    public StoreLocation findById(Number id) {
        String sql = "SELECT * FROM stores s JOIN store_locations sl ON s.id = sl.store_id where sl.id = :storeLocationId";
        Map<String, Object> params = new HashMap<>();
        params.put("storeLocationId", id);
        return getNamedParameterJdbcTemplate()
                .queryForObject(sql, params, rowMapper);
    }

    public void addStoreLocation(StoreLocation storeLocation) {
        if (storeIsNew(storeLocation)) {
            storeRepository.addStore(storeLocation.getStore());
        }
        Number generatedKey = insert(storeLocation).into(Tables.STORE_LOCATIONS);
        storeLocation.setId(generatedKey);
    }

    private boolean storeIsNew(StoreLocation storeLocation) {
        return storeLocation.getStore() != null
                && storeLocation.getStoreId() == null;
    }
}
