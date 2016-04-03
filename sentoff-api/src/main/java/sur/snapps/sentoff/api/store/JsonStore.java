package sur.snapps.sentoff.api.store;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author sur
 * @since 30/03/2016
 */
public class JsonStore implements JStore {

    @NotNull
    private String name;

    @NotNull @Valid
    private JsonAddress address;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public JsonAddress getAddress() {
        return address;
    }

    public void setAddress(JsonAddress address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
