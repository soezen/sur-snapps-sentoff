package sur.snapps.sentoff.domain.table;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import sur.snapps.sentoff.domain.account.Account;
import sur.snapps.sentoff.domain.repo.AccountRowMapper;
import sur.snapps.sentoff.domain.repo.Table;

/**
 * @author rogge
 * @since 11/02/2017
 */
public class AccountsTable implements Table<Account> {

	@Override
	public RowMapper<Account> getRowMapper() {
		return new AccountRowMapper();
	}
	
    @Override
    public String getTableName() {
        return "ACCOUNTS";
    }

    @Override
    public Map<String, Object> getInsertValues(Account row) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", row.getId());
        values.put("name", row.getName());
        values.put("owner_id", row.getOwner());
        return values;
    }
    
    @Override
    public Map<String, Object> getUpdateValues(Account row) {
    	// TODO Auto-generated method stub
    	return null;
    }
}
