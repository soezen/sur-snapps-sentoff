package sur.snapps.sentoff.domain.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sur.snapps.sentoff.domain.account.Account;
import sur.snapps.sentoff.domain.table.Tables;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Repository
public class AccountRepository extends AbstractRepository {

    @Autowired
    private AccountRowMapper rowMapper;

    @Autowired
    public AccountRepository(DataSource dataSource) {
        super(dataSource);
    }
    
    // TODO use spring data? or something else
   
    public List<Account> findAll() {
    	String sql = "select * from ACCOUNTS";
    	return getNamedParameterJdbcTemplate()
    			.query(sql, rowMapper);
    }
    
    public Account findById(Number id) {
    	String sql = "select * from ACCOUNTS where ID = :id";
    	Map<String, Object> params = new HashMap<>();
    	params.put("id", id);
    	List<Account> results = getNamedParameterJdbcTemplate()
			.query(sql, params, rowMapper);
    	if (results.size() > 1) throw new IllegalStateException("More than 1 account found with id " + id);
		return results.isEmpty() ? null : results.get(0);
    }
 
    public Number save(Account account) {
    	return insert(account).into(Tables.ACCOUNTS);
    }
}
