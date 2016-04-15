package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    public Number findMostSimilar(StoreLocation storeDetails) {
        String sql = "SELECT sl.id FROM store_locations sl JOIN stores s ON s.id = sl.store_id WHERE ROWNUM <= 1";
        // TODO refactor this
        Map<String, Object> args = new HashMap<>();
        if (storeDetails.getStore().getName() != null) {
            sql += " AND (s.NAME = :storeName OR s.NAME IS NULL)";
            args.put("storeName", storeDetails.getStore().getName());
        }
        if (storeDetails.getStore().getType() != null) {
            sql += " AND (s.TYPE = :storeType OR s.TYPE IS NULL)";
            args.put("storeType", storeDetails.getStore().getType().name());
        }
        if (storeDetails.getStreet() != null) {
            sql += " AND (sl.STREET = :street OR sl.STREET IS NULL)";
            args.put("street", storeDetails.getStreet());
        }
        if (storeDetails.getNumber() != null) {
            sql += " AND (sl.NUMBER = :number OR sl.NUMBER IS NULL)";
            args.put("number", storeDetails.getNumber());
        }
        if (storeDetails.getZipCode() != null) {
            sql += " AND (sl.ZIP_CODE = :zipCode OR sl.ZIP_CODE IS NULL)";
            args.put("zipCode", storeDetails.getZipCode());
        }
        if (storeDetails.getCity() != null) {
            sql += " AND (sl.CITY = :city OR sl.CITY IS NULL)";
            args.put("city", storeDetails.getCity());
        }
        if (storeDetails.getCountry() != null) {
            sql += " AND (sl.COUNTRY = :country OR sl.COUNTRY IS NULL)";
            args.put("country", storeDetails.getCountry());
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

    public void replaceStoreLocation(Number id, Number replaceId) {
        Number storeId = findById(id).getStoreId();

        String updateSql = "update purchases set store_location_id = :replaceId where store_location_id = :id";
        String removeStoreLocationSql = "delete from store_locations where id = :id";
        String removeStoreSql = "delete from stores where id = :storeId and 0 = (select count(*) from store_locations where store_id = :storeId)";
        Map<String, Object> args = new HashMap<>();
        args.put("id", id);
        args.put("replaceId", replaceId);
        args.put("storeId", storeId);
        getNamedParameterJdbcTemplate().update(updateSql, args);
        getNamedParameterJdbcTemplate().update(removeStoreLocationSql, args);
        getNamedParameterJdbcTemplate().update(removeStoreSql, args);
    }

    private boolean storeIsNew(StoreLocation storeLocation) {
        return storeLocation.getStore() != null
                && storeLocation.getStoreId() == null;
    }
}
