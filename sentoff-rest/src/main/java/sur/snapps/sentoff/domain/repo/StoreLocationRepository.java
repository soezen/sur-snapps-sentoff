package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.api.store.JsonStoreDetails;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.table.Tables;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
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

    public Number findMostSimilar(JsonStoreDetails storeDetails) {
        String sql = "SELECT sl.id FROM store_locations sl JOIN stores s ON s.id = sl.store_id WHERE ROWNUM <= 1";
        Map<String, Object> args = new HashMap<>();
        if (storeDetails.getName() != null) {
            sql += " AND (s.NAME = :storeName OR s.NAME IS NULL)";
            args.put("storeName", storeDetails.getName());
        }
        if (storeDetails.getType() != null) {
            sql += " AND (s.TYPE = :storeType OR s.TYPE IS NULL)";
            args.put("storeType", storeDetails.getType());
        }
        if (storeDetails.getAddress().getStreet() != null) {
            sql += " AND (sl.STREET = :street OR sl.STREET IS NULL)";
            args.put("street", storeDetails.getAddress().getStreet());
        }
        if (storeDetails.getAddress().getNumber() != null) {
            sql += " AND (sl.NUMBER = :number OR sl.NUMBER IS NULL)";
            args.put("number", storeDetails.getAddress().getNumber());
        }
        if (storeDetails.getAddress().getZipCode() != null) {
            sql += " AND (sl.ZIP_CODE = :zipCode OR sl.ZIP_CODE IS NULL)";
            args.put("zipCode", storeDetails.getAddress().getZipCode());
        }
        if (storeDetails.getAddress().getCity() != null) {
            sql += " AND (sl.CITY = :city OR sl.CITY IS NULL)";
            args.put("city", storeDetails.getAddress().getCity());
        }
        if (storeDetails.getAddress().getCountry() != null) {
            sql += " AND (sl.COUNTRY = :country OR sl.COUNTRY IS NULL)";
            args.put("country", storeDetails.getAddress().getCountry());
        }
        List<Integer> results = getNamedParameterJdbcTemplate()
            .queryForList(sql, args, Integer.class);
        return results.isEmpty() ? null : results.get(0);
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
