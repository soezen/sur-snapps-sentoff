package sur.snapps.sentoff.domain.table;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import sur.snapps.sentoff.domain.account.Balance;
import sur.snapps.sentoff.domain.repo.Table;

/**
 * @author rogge
 * @since 31/03/2018
 */
public class BalancesTable implements Table<Balance> {

	@Override
	public RowMapper<Balance> getRowMapper() {
		return null;
	}
	
    @Override
    public String getTableName() {
        return "BALANCES";
    }

    @Override
    public Map<String, Object> getInsertValues(Balance row) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", row.getId());
        values.put("timestamp", row.getTimestamp());
        values.put("value", row.getValue());
        values.put("account_id", row.getAccountId());
        return values;
    }
    
    @Override
    public Map<String, Object> getUpdateValues(Balance row) {
    	// TODO Auto-generated method stub
    	return null;
    }
}
