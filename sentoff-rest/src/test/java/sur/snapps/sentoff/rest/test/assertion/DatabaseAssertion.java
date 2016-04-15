package sur.snapps.sentoff.rest.test.assertion;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import sur.snapps.sentoff.domain.repo.Row;
import sur.snapps.sentoff.domain.repo.Table;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.jdbc.JdbcTestUtils.countRowsInTable;

/**
 * @author rogge
 * @since 3/04/2016.
 */
public class DatabaseAssertion<T extends Row> {

    private JdbcTemplate jdbcTemplate;
    private Table<T> table;

    public DatabaseAssertion(JdbcTemplate jdbcTemplate, Table<T> table) {
        this.jdbcTemplate = jdbcTemplate;
        this.table = table;
    }

    public DatabaseAssertion hasNumberOfRows(int count) {
        assertEquals("expected table " + table.getTableName() + " to have " + count + " row(s)", count, countRowsInTable(jdbcTemplate, table.getTableName()));
        return this;
    }

    public DatabaseAssertion assertRowInserted(T row) {
        Map<String, Object> actualValues = jdbcTemplate.queryForMap("SELECT * FROM " + table.getTableName());
        for (Map.Entry<String, Object> entry : table.getInsertValues(row).entrySet()) {
            assertTrue(actualValues.containsKey(entry.getKey()));
            assertEquals(entry.getValue(), actualValues.get(entry.getKey()));
        }
        return this;
    }

    public DatabaseAssertion existsRowWithValues(Number id, String column, String value) {
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT " + column + " FROM " + table.getTableName() + " WHERE id = ?", id);
        assertTrue(sqlRowSet.next());
        assertEquals(value, sqlRowSet.getString(column));
        return this;
    }
}
