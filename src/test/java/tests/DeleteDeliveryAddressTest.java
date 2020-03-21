package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class DeleteDeliveryAddressTest extends BaseTest {
    @Test(description = "Delete delivery address in personal account")
    public void deleteDeliveryAddress() {
        String address = "Некрасова, дом 43, кв. 5, подъезд 6, этаж 4";
        userSteps.goToPersonalInfo();
        int result = infoSteps.goToAddresses();
        infoSteps.deleteAddresses(address, result);
    }
}
