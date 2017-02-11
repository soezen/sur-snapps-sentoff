package sur.snapps.sentoff.domain.table;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public interface Tables {

	public static final MessagesTable MESSAGES = new MessagesTable();
    public static final PurchasesTable PURCHASES = new PurchasesTable();
    public static final StoreLocationsTable STORE_LOCATIONS = new StoreLocationsTable();
    public static final StoresTable STORES = new StoresTable();
    public static final AccountsTable ACCOUNTS = new AccountsTable();

}
