package sur.snapps.sentoff.domain.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public abstract class AbstractRepository extends NamedParameterJdbcDaoSupport {


    public AbstractRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    protected  <T extends Row> InsertStatement<T> insert(T row) {
        return new InsertStatement<T>(new SimpleJdbcInsert(getJdbcTemplate()), row);
    }
    
    protected <T extends Row> UpdateStatement<T> update(Table<T> table) {
    	return new UpdateStatement<T>(table);
    }
    
    protected <T extends Row> SelectStatement<T> selectFrom(Table<T> table) {
    	return new SelectStatement<T>(table);
    }
    
    class SelectStatement<T extends Row> {
    	
    	private Table<T> table;
    	private static final String SELECT_STATEMENT = "select * from %s";
    	
    	public SelectStatement(Table<T> table) {
    		this.table = table;
		}
    	
    	public T whereId(Number id) {
        	String sql = String.format(SELECT_STATEMENT, table.getTableName()) + " where ID = :id";
        	Map<String, Object> params = new HashMap<>();
        	params.put("id", id);
        	List<T> results = getNamedParameterJdbcTemplate()
    			.query(sql, params, table.getRowMapper());
        	if (results.size() > 1) throw new IllegalStateException("More than 1 row found with id " + id);
    		return results.isEmpty() ? null : results.get(0);
    	}
    	
    	public List<T> all() {
    		String sql = String.format(SELECT_STATEMENT, table.getTableName());
    		return getNamedParameterJdbcTemplate()
    				.query(sql, table.getRowMapper());
    	}
    }
    
    class UpdateStatement<T extends Row> {
    	
    	private Table<T> table;
    	
    	public UpdateStatement(Table<T> table) {
    		this.table = table;
		}
    	    	
    	void values(T row) {
    		Map<String, Object> params = table.getUpdateValues(row);
    		String updateColumns = StringUtils.join(params.keySet().stream()
				.map(AbstractRepository::updateColumnString)
				.toArray(), ",");
    		String sql = String.format("update %s set %s where id = :id", table.getTableName(), updateColumns);
    		params.put("id", row.getId());
    		getNamedParameterJdbcTemplate().update(sql, params);
    		
    	}
    	
    }
    
    static String updateColumnString(String columnName) {
		return columnName + " = :" + columnName;
	}

    class InsertStatement<T extends Row> {

        private SimpleJdbcInsert insert;
        private T row;

        InsertStatement(SimpleJdbcInsert insert, T row) {
            this.insert = insert;
            this.row = row;
        }

        Number into(Table<T> table) {
            Number key = row.getId();
            if (key == null) {
                insert.withTableName(table.getTableName()).usingGeneratedKeyColumns("id");

                key = insert.executeAndReturnKey(table.getInsertValues(row));
            } else {
                insert.withTableName(table.getTableName()).execute(table.getInsertValues(row));
            }
            return key;
        }
    }
}
