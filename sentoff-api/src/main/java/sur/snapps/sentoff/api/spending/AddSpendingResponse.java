package sur.snapps.sentoff.api.spending;

/**
 * @author rogge
 * @since 26/03/2016.
 */
public class AddSpendingResponse {

    private Number id;

    public AddSpendingResponse() {
        id = 0L;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }
}
