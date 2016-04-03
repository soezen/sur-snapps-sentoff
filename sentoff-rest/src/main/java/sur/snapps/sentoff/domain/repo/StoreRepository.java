package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.table.Tables;

import javax.sql.DataSource;

/**
 * @author rogge
 * @since 2/04/2016.
 */
@Repository
public class StoreRepository extends AbstractRepository {

    @Autowired
    public StoreRepository(DataSource dataSource) {
        super(dataSource);
    }

    public void addStore(Store store) {
        Number generatedKey = insert(store).into(Tables.STORES);
        store.setId(generatedKey);
    }
}
