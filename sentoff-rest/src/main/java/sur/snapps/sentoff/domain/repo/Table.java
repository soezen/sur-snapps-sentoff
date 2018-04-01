package sur.snapps.sentoff.domain.repo;

import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author sur
 * @since 01/04/2016
 */
public interface Table<T extends Row> {

	RowMapper<T> getRowMapper();
	
    String getTableName();

    Map<String, Object> getInsertValues(T row);
    
    Map<String, Object> getUpdateValues(T row);

}
