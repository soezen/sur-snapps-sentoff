package sur.snapps.sentoff.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.response.SuccessResponse;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author sur
 * @since 14/04/2016
 */
@Component
@Path("/stores")
public class StoreController {

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @POST
    @Path("/replaceStoreLocation")
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse replaceStoreLocation(@QueryParam("removeId") Integer removeId, @QueryParam("replaceId") Integer replaceId) {
        storeLocationRepository.replaceStoreLocation(removeId, replaceId);
        return new SuccessResponse();
    }
}
