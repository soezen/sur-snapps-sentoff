package sur.snapps.sentoff.domain.check;

import sur.snapps.sentoff.api.JsonRequest;
import sur.snapps.sentoff.api.response.JsonMessage;

import java.util.List;

/**
 * @author sur
 * @since 12/04/2016
 */
public interface ICheck {

    boolean appliesTo(JsonRequest subject);

    List<JsonMessage> check(JsonRequest object);
}
