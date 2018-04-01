package sur.snapps.sentoff.domain.table;

import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.repo.SpendingRowMapper;
import sur.snapps.sentoff.domain.repo.Table;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author sur
 * @since 01/04/2016
 */
public class PurchasesTable implements Table<Spending> {
	
	@Override
	public RowMapper<Spending> getRowMapper() {
		return new SpendingRowMapper();
	}

    public Map<String, Object> getInsertValues(Spending spending) {
        Map<String, Object> values = new HashMap<>();
        values.put("date", spending.getDate());
        values.put("amount", spending.getAmount());
        values.put("store_location_id", spending.getStoreLocationId());
        return values;
    }

    @Override
    public String getTableName() {
        return "PURCHASES";
    }
    
    @Override
    public Map<String, Object> getUpdateValues(Spending row) {
    	// TODO Auto-generated method stub
    	return null;
    }
}
