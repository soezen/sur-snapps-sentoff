package sur.snapps.sentoff.domain.table;

import java.util.HashMap;
import java.util.Map;

import sur.snapps.sentoff.domain.account.Account;
import sur.snapps.sentoff.domain.repo.Table;

/**
 * @author rogge
 * @since 11/02/2017
 */
public class AccountsTable implements Table<Account> {

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
        values.put("balance", row.getBalance());
        return values;
    }
}
