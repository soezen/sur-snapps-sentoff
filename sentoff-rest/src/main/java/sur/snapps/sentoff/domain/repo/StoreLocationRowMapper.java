package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.table.Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author rogge
 * @since 3/04/2016.
 */
@Component
public class StoreLocationRowMapper extends AbstractRowMapper<StoreLocation> {

    private static final String TABLE_NAME = Tables.STORE_LOCATIONS.getTableName();

    @Autowired
    private StoreRowMapper storeRowMapper;

    @Override
    public StoreLocation mapRow(ResultSet rs, Map<String, Integer> columnIndices) throws SQLException {
        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setId(rs.getInt(columnIndices.get(TABLE_NAME + ".ID")));
        storeLocation.setStore(storeRowMapper.mapRow(rs, columnIndices));
        return storeLocation;
    }

}
