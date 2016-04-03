package sur.snapps.sentoff.domain.repo;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public abstract class AbstractRepository extends NamedParameterJdbcDaoSupport {


    public AbstractRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    protected  <T extends Row> InsertStatement<T> insert(T row) {
        return new InsertStatement<T>(new SimpleJdbcInsert(getJdbcTemplate()), row);
    }

    class InsertStatement<T extends Row> {

        private SimpleJdbcInsert insert;
        private T row;

        InsertStatement(SimpleJdbcInsert insert, T row) {
            this.insert = insert;
            this.row = row;
        }

        Number into(Table<T> table) {
            insert.withTableName(table.getTableName())
                    .usingGeneratedKeyColumns("id");

            return insert.executeAndReturnKey(table.getInsertValues(row));
        }
    }
}
