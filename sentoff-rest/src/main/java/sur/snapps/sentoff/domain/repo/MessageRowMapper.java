package sur.snapps.sentoff.domain.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Component;

import sur.snapps.sentoff.domain.Message;
import sur.snapps.sentoff.domain.table.Tables;

/**
 * @author sur
 * @since 15/04/2016
 */
@Component
public class MessageRowMapper extends AbstractRowMapper<Message> {

    private static final String TABLE_NAME = Tables.MESSAGES.getTableName();

    @Override
    public Message mapRow(ResultSet rs, Map<String, Integer> columnIndices) throws SQLException {
    	Message row = new Message();
        row.setId(rs.getInt(columnIndices.get(TABLE_NAME + ".ID")));
        row.setRequestTimestamp(rs.getTimestamp(columnIndices.get(TABLE_NAME + ".REQUEST_TIMESTAMP")).toLocalDateTime());
        return row;
    }

}
