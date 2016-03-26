package sur.snapps.sentoff.rest.spending;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.spending.AddSpendingResponse;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@RestController
@RequestMapping("/spending")
public class SpendingController {

    @RequestMapping("/add")
    public AddSpendingResponse addSpending(AddSpendingRequest request) {
        return new AddSpendingResponse();
    }
}
