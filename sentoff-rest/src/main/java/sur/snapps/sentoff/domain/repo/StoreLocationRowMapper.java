package sur.snapps.sentoff.domain.repo;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.domain.Store;
import sur.snapps.sentoff.domain.StoreLocation;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author rogge
 * @since 3/04/2016.
 */
@Component
public class StoreLocationRowMapper implements RowMapper<StoreLocation> {

    @Override
    public StoreLocation mapRow(ResultSet rs, int i) throws SQLException {
        Store store = new Store();
        store.setId(rs.getInt("STORE_ID"));
        StoreLocation storeLocation = new StoreLocation();
        storeLocation.setId(rs.getInt("ID"));
        storeLocation.setStore(store);
        return storeLocation;
    }
}
