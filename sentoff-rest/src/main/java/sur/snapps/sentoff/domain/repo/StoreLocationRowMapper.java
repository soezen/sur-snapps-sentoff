package sur.snapps.sentoff.domain.repo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreLocation;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rogge
 * @since 3/04/2016.
 */
@Component
public class StoreLocationRowMapper implements RowMapper<StoreLocation> {

    private Map<String, Integer> columnIndices = new HashMap<>();

    @Override
    public StoreLocation mapRow(ResultSet rs, int i) throws SQLException {
        initColumnIndices(rs.getMetaData());
        Store store = new Store();
        store.setId(rs.getInt(columnIndices.get("STORES.ID")));
        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setId(rs.getInt(columnIndices.get("STORE_LOCATIONS.ID")));
        storeLocation.setStore(store);
        return storeLocation;
    }

    private void initColumnIndices(ResultSetMetaData metaData) throws SQLException {
        int count = metaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            columnIndices.put(metaData.getTableName(i) + "." + metaData.getColumnName(i), i);
        }
    }
}
