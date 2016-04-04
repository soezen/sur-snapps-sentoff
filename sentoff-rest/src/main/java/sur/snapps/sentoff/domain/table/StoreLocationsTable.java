package sur.snapps.sentoff.domain.table;

import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.repo.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sur
 * @since 01/04/2016
 */
public class StoreLocationsTable implements Table<StoreLocation> {

    @Override
    public String getTableName() {
        return "STORE_LOCATIONS";
    }

    @Override
    public Map<String, Object> getInsertValues(StoreLocation row) {
        Map<String, Object> values = new HashMap<>();
        values.put("id", row.getId());
        values.put("store_id", row.getStoreId());
        values.put("name", row.getName());
        values.put("city", row.getCity());
        values.put("country", row.getCountry());
        return values;
    }
}
