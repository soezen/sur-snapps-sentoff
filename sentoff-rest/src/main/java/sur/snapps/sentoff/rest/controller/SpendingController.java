package sur.snapps.sentoff.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Component
@Path("/spendings")
public class SpendingController extends AbstractRestController {

    @Autowired
    private SpendingRepository spendingRepository;

    @Autowired
    private SpendingMapper spendingMapper;

    @Autowired
    private DataCheckService dataCheckService;

    @POST
    @Path("/add")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public RestResponse addSpending(@Valid AddSpendingRequest request) {

        Spending spending = spendingMapper.map(request);
        spendingRepository.addSpending(spending);

        List<JsonMessage> messages = dataCheckService.check(request, spending);

        return new SuccessResponse(messages);
    }
}
