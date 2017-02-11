package sur.snapps.sentoff.rest.test;

import org.junit.Test;
import sur.snapps.sentoff.api.response.MessageType;
import sur.snapps.sentoff.api.response.RestResponse;
import sur.snapps.sentoff.api.response.SuccessResponse;
import sur.snapps.sentoff.api.spending.AddSpendingRequest;
import sur.snapps.sentoff.api.test.spending.builder.AddSpendingRequestBuilder;
import sur.snapps.sentoff.domain.Spending;
import sur.snapps.sentoff.domain.StoreLocation;
import sur.snapps.sentoff.domain.StoreLocationMother;
import sur.snapps.sentoff.domain.StoreMother;
import sur.snapps.sentoff.domain.table.Tables;
import sur.snapps.sentoff.rest.test.assertion.RestResponseAssertion;

/**
 * @author sur
 * @since 14/04/2016
 */
public class ReplaceStoreLocationIntegrationTest extends AbstractIntegrationTest {

    @Test
    public void success_removeStoreAndStoreLocation() {
        insertStore(StoreMother.colruyt(999));
        insertStoreLocation(StoreLocationMother.colruytDeerlijk(999));

        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
            .withStoreName("Colruyt")
            .withStoreCity("Deerlijk")
            .withStoreCountry("BE")
            .build();
        SuccessResponse response = postAddSpendingRequest(request);

        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(2);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(2);

        RestResponse replaceResponse = postJson(response.getFieldMessage("store", MessageType.DUPLICATE_STORE_LOCATION), null);
        new RestResponseAssertion(replaceResponse).assertSuccess();

        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(1);
    }

    @Test
    public void success_removeOnlyStoreLocation() {
        // START SITUATION: existing store and store location
        insertStore(StoreMother.colruyt(999));
        insertStoreLocation(StoreLocationMother.colruytDeerlijk(999));

        // adding new store and store location
        AddSpendingRequest request = AddSpendingRequestBuilder.minimalAddSpendingRequest()
            .withStoreName("Colruyt")
            .withStoreCity("Deerlijk")
            .withStoreCountry("BE")
            .build();
        SuccessResponse response = postAddSpendingRequest(request);
        Number spendingId = response.getGeneratedId();
        Spending spending = spendingRepository.findById(spendingId);

        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(2);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(2);

        // attach new store location to added store
        StoreLocation storeLocation = StoreLocationMother.colruytHarelbeke();
        storeLocation.getStore().setId(spending.getStoreLocation().getStoreId());
        insertStoreLocation(storeLocation);

        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(3);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(2);

        // update new store location to similar store location, but do not remove store as another store location is still attached
        RestResponse replaceResponse = postJson(response.getFieldMessage("store", MessageType.DUPLICATE_STORE_LOCATION), null);
        new RestResponseAssertion(replaceResponse).assertSuccess();

        assertDatabaseTable(Tables.PURCHASES).hasNumberOfRows(1);
        assertDatabaseTable(Tables.STORE_LOCATIONS).hasNumberOfRows(2);
        assertDatabaseTable(Tables.STORES).hasNumberOfRows(2);
    }

    private SuccessResponse postAddSpendingRequest(AddSpendingRequest request) {
        RestResponse response = postJson("/sentoff/spendings", request);
        new RestResponseAssertion(response).assertSuccess();
        return (SuccessResponse) response;
    }
}
