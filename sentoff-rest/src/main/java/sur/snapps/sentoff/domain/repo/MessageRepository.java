package sur.snapps.sentoff.domain.repo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import sur.snapps.sentoff.domain.Message;
import sur.snapps.sentoff.domain.table.Tables;

/**
 * @author rogge
 * @since 02/01/2017
 */
@Repository
public class MessageRepository extends AbstractRepository {

    @Autowired
    public MessageRepository(DataSource dataSource) {
        super(dataSource);
    }

    public void addMessage(Message message) {
        Number generatedKey = insert(message).into(Tables.MESSAGES);
        message.setId(generatedKey);
    }
}
