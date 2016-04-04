package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.table.Tables;

import javax.sql.DataSource;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Repository
public class SpendingRepository extends AbstractRepository {

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @Autowired
    public SpendingRepository(DataSource dataSource) {
        super(dataSource);
    }

    public void addSpending(Spending spending) {
        if (storeLocationIsNew(spending)) {
            storeLocationRepository.addStoreLocation(spending.getStoreLocation());
        }
        Number generatedKey = insert(spending).into(Tables.PURCHASES);
        spending.setId(generatedKey);
    }

    private boolean storeLocationIsNew(Spending spending) {
        return spending.getStoreLocation() != null
                && spending.getStoreLocationId() == null;
    }
}
