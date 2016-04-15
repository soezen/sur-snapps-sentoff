package sur.snapps.sentoff.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.response.SuccessResponse;
import sur.snapps.sentoff.domain.repo.StoreLocationRepository;
import sur.snapps.sentoff.rest.AbstractRestController;

/**
 * @author sur
 * @since 14/04/2016
 */
@RestController
@RequestMapping("/stores")
public class StoreController extends AbstractRestController {

    @Autowired
    private StoreLocationRepository storeLocationRepository;

    @RequestMapping(value = "/replaceStoreLocation", method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse replaceStoreLocation(Number removeId, Number replaceId) {
        storeLocationRepository.replaceStoreLocation(removeId, replaceId);
        return new SuccessResponse();
    }
}
