package tests;

import org.testng.annotations.Test;

public class DeleteDeliveryAddressTest extends BaseTest {
    @Test
    public void deleteDeliveryAddress() {
        String address = "ул. Кирова, дом 22, кв. 10";
        userSteps.goToPersonalInfo();
        int result = infoSteps.goToAddresses();
        infoSteps.deleteAddresses(address, result);
    }
}
