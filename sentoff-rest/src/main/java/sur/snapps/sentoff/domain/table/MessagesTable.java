package sur.snapps.sentoff.domain.table;

import java.util.HashMap;
import java.util.Map;

import sur.snapps.sentoff.domain.Message;
import sur.snapps.sentoff.domain.repo.Table;

/**
 * @author sur
 * @since 02/01/2017
 */
public class MessagesTable implements Table<Message> {

    public Map<String, Object> getInsertValues(Message message) {
        Map<String, Object> values = new HashMap<>();
        values.put("date", message.getDate());
        values.put("uri", message.getUri());
        values.put("method", message.getMethod());
        values.put("payload", message.getPayload());
        return values;
    }

    @Override
    public String getTableName() {
        return "MESSAGES";
    }
}
