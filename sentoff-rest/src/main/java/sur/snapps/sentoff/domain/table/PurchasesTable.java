package sur.snapps.sentoff.domain.table;

import com.google.common.collect.Tables;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.repo.Row;
import sur.snapps.sentoff.domain.repo.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sur
 * @since 01/04/2016
 */
public class PurchasesTable implements Table<Spending> {

    public Map<String, Object> getInsertValues(Spending spending) {
        Map<String, Object> values = new HashMap<>();
        values.put("date", spending.getDate());
        values.put("amount", spending.getAmount());
        values.put("store_location_id", spending.getStoreLocationId());
        return values;
    }

    @Override
    public <D extends Row> Map<Table<D>, D> getDependencies(Spending spending) {
        Map<Table<D>, D> dependencies = new HashMap<>();
        if (spending.getStoreLocationId() == null && spending.getStoreLocation() != null) {
            dependencies.put(Tables.storeLocations(), spending.getStoreLocation());
        }
        return dependencies;
    }

    @Override
    public String getTableName() {
        return "PURCHASES";
    }
}
