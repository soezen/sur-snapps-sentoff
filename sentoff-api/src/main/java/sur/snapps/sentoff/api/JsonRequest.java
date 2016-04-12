package sur.snapps.sentoff.api;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author sur
 * @since 12/04/2016
 */
public class JsonRequest {

    private Map<String, Object> ignoredValues = new HashMap<>();

    @JsonAnySetter
    public void setIgnoredValue(String key, Object value) {
        ignoredValues.put(key, value);
    }

    public Set<String> getIgnoredFields() {
        return Collections.unmodifiableSet(ignoredValues.keySet());
    }
}
