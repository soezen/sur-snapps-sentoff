package sur.snapps.sentoff.api.spending;

import javax.validation.constraints.NotNull;

/**
 * @author sur
 * @since 30/03/2016
 */
public class Store {

    @NotNull
    private String name;

    @NotNull
    private String location;

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }
}
