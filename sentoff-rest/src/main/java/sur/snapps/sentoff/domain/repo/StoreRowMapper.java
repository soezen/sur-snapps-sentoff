package sur.snapps.sentoff.domain.repo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreType;
import sur.snapps.sentoff.domain.table.Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author sur
 * @since 15/04/2016
 */
@Component
public class StoreRowMapper extends AbstractRowMapper<Store> {

    private static final String TABLE_NAME = Tables.STORES.getTableName();

    @Override
    public Store mapRow(ResultSet rs, Map<String, Integer> columnIndices) throws SQLException {
        Store store = new Store();
        store.setId(rs.getInt(columnIndices.get(TABLE_NAME + ".ID")));
        store.setName(rs.getString(columnIndices.get(TABLE_NAME + ".NAME")));
        String storeType = rs.getString(columnIndices.get(TABLE_NAME + ".TYPE"));
        if (StringUtils.isNotBlank(storeType)) {
            store.setType(StoreType.valueOf(storeType));
        }
        return store;
    }
}
