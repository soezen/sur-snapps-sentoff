package sur.snapps.sentoff.domain.table;

import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.repo.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public class StoresTable implements Table<Store> {

    @Override
    public String getTableName() {
        return "STORES";
    }

    @Override
    public Map<String, Object> getInsertValues(Store row) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", row.getId());
        values.put("name", row.getName());
        values.put("type", row.getTypeAsString());
        return values;
    }
}
