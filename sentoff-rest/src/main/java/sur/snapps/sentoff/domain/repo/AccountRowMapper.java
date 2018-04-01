package sur.snapps.sentoff.domain.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Component;

import sur.snapps.sentoff.domain.account.Account;
import sur.snapps.sentoff.domain.table.Tables;

/**
 * @author sur
 * @since 15/04/2016
 */
@Component
public class AccountRowMapper extends AbstractRowMapper<Account> {

    private static final String TABLE_NAME = Tables.ACCOUNTS.getTableName();

    @Override
    public Account mapRow(ResultSet rs, Map<String, Integer> columnIndices) throws SQLException {
    	Account row = new Account();
        row.setId(rs.getInt(columnIndices.get(TABLE_NAME + ".ID")));
        row.setName(rs.getString(columnIndices.get(TABLE_NAME + ".NAME")));
        row.setOwner(rs.getInt(columnIndices.get(TABLE_NAME + ".OWNER_ID")));
        return row;
    }

}
