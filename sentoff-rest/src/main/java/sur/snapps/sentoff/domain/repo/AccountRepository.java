package sur.snapps.sentoff.domain.repo;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sur.snapps.sentoff.domain.account.Account;
import sur.snapps.sentoff.domain.account.Balance;
import sur.snapps.sentoff.domain.table.Tables;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Repository
public class AccountRepository extends AbstractRepository {

    @Autowired
    public AccountRepository(DataSource dataSource) {
        super(dataSource);
    }
    
    // TODO use spring data? or something else
   
    public List<Account> findAll() {
    	return selectFrom(Tables.ACCOUNTS).all();
    }
    
    public Account findById(Number id) {
    	return selectFrom(Tables.ACCOUNTS).whereId(id);
    }
 
    public Number save(Account account) {
    	return insert(account).into(Tables.ACCOUNTS);
    }
    
    public Number save(Balance balance) {
    	return insert(balance).into(Tables.BALANCES);
    }
}
