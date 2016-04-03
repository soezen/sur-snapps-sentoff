package sur.snapps.sentoff.api.store;

import javax.validation.constraints.NotNull;

/**
 * @author rogge
 * @since 2/04/2016.
 */
public class JsonStoreReference implements JStore {

    @NotNull
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
