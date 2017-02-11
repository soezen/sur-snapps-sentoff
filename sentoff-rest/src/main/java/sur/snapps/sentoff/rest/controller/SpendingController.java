package sur.snapps.sentoff.rest.controller;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import sur.snapps.sentoff.api.response.JsonMessage;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.response.SuccessResponse;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.check.DataCheckService;
import sur.snapps.sentoff.domain.mapper.SpendingMapper;
import sur.snapps.sentoff.domain.repo.SpendingRepository;

/**
 * @author rogge
 * @since 26/03/2016.
 */
@Api
@Component
@Path("/spendings")
public class SpendingController {

    @Autowired
    private SpendingRepository spendingRepository;

    @Autowired
    private SpendingMapper spendingMapper;

    @Autowired
    private DataCheckService dataCheckService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse addSpending(@Valid AddSpendingRequest request) {

        Spending spending = spendingMapper.map(request);
        spendingRepository.addSpending(spending);

        List<JsonMessage> messages = dataCheckService.check(request, spending);

        return new SuccessResponse(messages);
    }
}
