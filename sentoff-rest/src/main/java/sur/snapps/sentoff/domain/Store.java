package sur.snapps.sentoff.domain;

import sur.snapps.sentoff.domain.repo.Row;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public class Store implements Row {

    private Number id;
    private String name;
    private StoreType type;

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StoreType getType() {
        return type;
    }

    public void setType(StoreType type) {
        this.type = type;
    }

    public String getTypeAsString() {
        return type == null ? null : type.name();
    }
}
