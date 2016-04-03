package sur.snapps.sentoff.domain.table;

import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.repo.Row;
import sur.snapps.sentoff.domain.repo.Table;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public interface Tables {

    public static final PurchasesTable PURCHASES = new PurchasesTable();
    public static final StoreLocationsTable STORE_LOCATIONS = new StoreLocationsTable();
    public static final StoresTable STORES = new StoresTable();

}
