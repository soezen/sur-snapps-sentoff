package sur.snapps.sentoff.domain.table;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import sur.snapps.sentoff.domain.Message;
import sur.snapps.sentoff.domain.repo.MessageRowMapper;
import sur.snapps.sentoff.domain.repo.Table;

/**
 * @author sur
 * @since 02/01/2017
 */
public class MessagesTable implements Table<Message> {

	@Override
	public RowMapper<Message> getRowMapper() {
		return new MessageRowMapper();
	}
	
    public Map<String, Object> getInsertValues(Message message) {
        Map<String, Object> values = new HashMap<>();
        values.put("request_timestamp", Timestamp.valueOf(message.getRequestTimestamp()));
        values.put("request_uri", message.getRequestUri());
        values.put("request_method", message.getRequestMethod());
        values.put("request_payload", message.getRequestPayload());
        return values;
    }
    
    public Map<String, Object> getUpdateValues(Message message) {
    	Map<String, Object> values = new HashMap<>();
    	addIfNotNull(values, "request_payload", message.getRequestPayload());
    	addIfNotNull(values, "response_timestamp", Timestamp.valueOf(message.getResponseTimestamp()));
    	addIfNotNull(values, "response_status", message.getResponseStatus());
    	addIfNotNull(values, "response_payload", message.getResponsePayload());
    	return values;
    }
    
    private void addIfNotNull(Map<String, Object> values, String key, Object value) {
    	if (value != null) {
    		values.put(key, value);
    	}
    }

    @Override
    public String getTableName() {
        return "MESSAGES";
    }
}
