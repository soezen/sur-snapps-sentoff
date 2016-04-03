package sur.snapps.sentoff.domain.repo;

import java.util.Map;

/**
 * @author sur
 * @since 01/04/2016
 */
public interface Table<T extends Row> {

    String getTableName();

    Map<String, Object> getInsertValues(T row);

}
