package sur.snapps.sentoff.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.response.SuccessResponse;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.check.DataCheckService;
import sur.snapps.sentoff.domain.mapper.SpendingMapper;
import sur.snapps.sentoff.domain.repo.SpendingRepository;
import sur.snapps.sentoff.rest.AbstractRestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@RestController
@RequestMapping("/spending")
public class SpendingController extends AbstractRestController {

    @Autowired
    private SpendingRepository spendingRepository;

    @Autowired
    private SpendingMapper spendingMapper;

    @Autowired
    private DataCheckService dataCheckService;

    @RequestMapping(value = "/add", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse addSpending(
            @RequestBody @Valid AddSpendingRequest request) {

        Spending spending = spendingMapper.map(request);
        spendingRepository.addSpending(spending);

        List<JsonMessage> messages = dataCheckService.check(request, spending);

        return new SuccessResponse(messages);
    }
}
