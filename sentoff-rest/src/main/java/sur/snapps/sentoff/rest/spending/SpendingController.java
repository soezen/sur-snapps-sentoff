package sur.snapps.sentoff.rest.spending;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.spending.AddSpendingResponse;
import sur.snapps.sentoff.rest.AbstractRestController;

import javax.validation.Valid;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@RestController
@RequestMapping("/spending")
public class SpendingController extends AbstractRestController {

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public AddSpendingResponse addSpending(
            @RequestBody @Valid AddSpendingRequest request) {
        AddSpendingResponse response = new AddSpendingResponse();
        response.setRequest(request);
        response.setId(5);
        return response;
    }
}
