package sur.snapps.sentoff.domain.table;

import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.repo.Row;
import sur.snapps.sentoff.domain.repo.Table;

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
        return null;
    }

    @Override
    public <D extends Row> Map<Table<D>, D> getDependencies(StoreLocation row) {
        return null;
    }
}
