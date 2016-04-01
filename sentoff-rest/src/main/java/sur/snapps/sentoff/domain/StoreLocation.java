package sur.snapps.sentoff.domain;

import sur.snapps.sentoff.domain.repo.Row;

/**
 * @author sur
 * @since 01/04/2016
 */
public class StoreLocation implements Row {

    private Number id;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }
}
