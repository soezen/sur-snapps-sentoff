package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.table.Tables;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author sur
 * @since 15/04/2016
 */
@Component
public class SpendingRowMapper extends AbstractRowMapper<Spending> {

    private static final String TABLE_NAME = Tables.PURCHASES.getTableName();

    @Autowired
    private StoreLocationRowMapper storeLocationRowMapper;

    @Override
    public Spending mapRow(ResultSet rs, Map<String, Integer> columnIndices) throws SQLException {
        Spending spending = new Spending();
        spending.setId(rs.getInt(columnIndices.get(TABLE_NAME + ".ID")));
        spending.setAmount(rs.getBigDecimal(columnIndices.get(TABLE_NAME + ".AMOUNT")));
        spending.setDate(rs.getDate(columnIndices.get(TABLE_NAME + ".DATE")));
        spending.setStoreLocation(storeLocationRowMapper.mapRow(rs, columnIndices));
        return spending;
    }

}
