package sur.snapps.sentoff.domain.repo;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * @author sur
 * @since 01/04/2016
 */
public class InsertStatement<T extends Row> {

    private SimpleJdbcInsert insert;
    private T row;

    public InsertStatement(SimpleJdbcInsert insert, T row) {
        this.insert = insert;
        this.row = row;
    }

    public Number into(Table<T> table) {
        table.getDependencies(row);

        insert.withTableName(table.getTableName())
            .usingGeneratedKeyColumns("id");

        return insert.executeAndReturnKey(table.getInsertValues(row));
    }
}
