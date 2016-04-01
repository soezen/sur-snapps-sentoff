package sur.snapps.sentoff.domain.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.table.PurchasesTable;

import javax.sql.DataSource;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Repository
public class SpendingRepository extends NamedParameterJdbcDaoSupport {

    @Autowired
    public SpendingRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void addSpending(Spending spending) {
        Number generatedKey = insert(spending).into(Tables.purchases());
        spending.setId(generatedKey);
    }

    private <T extends Row> InsertStatement<T> insert(T row) {
        return new InsertStatement<T>(new SimpleJdbcInsert(getJdbcTemplate()), row);
    }

    public static final class Tables {
        static PurchasesTable purchases() {
            return new PurchasesTable();
        }

        static StoreLocationsTable storeLocations() {
            return new StoreLocationsTable();
        }
    }
}
