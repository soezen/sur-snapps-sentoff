package sur.snapps.sentoff.domain.repo;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sur
 * @since 15/04/2016
 */
public abstract class AbstractRowMapper<T> implements RowMapper<T> {

    public abstract T mapRow(ResultSet rs, Map<String, Integer> columnIndices) throws SQLException;

    @Override
    public T mapRow(ResultSet resultSet, int i) throws SQLException {
        Map<String, Integer> columnIndices = createColumnIndices(resultSet.getMetaData());
        return mapRow(resultSet, columnIndices);
    }

    private Map<String, Integer> createColumnIndices(ResultSetMetaData metaData) throws SQLException {
        Map<String, Integer> columnIndices = new HashMap<>();
        int count = metaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
            columnIndices.put(metaData.getTableName(i).toUpperCase() + "." + metaData.getColumnName(i).toUpperCase(), i);
        }
        return columnIndices;
    }

}
