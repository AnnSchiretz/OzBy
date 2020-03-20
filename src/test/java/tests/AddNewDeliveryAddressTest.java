package tests;

import models.Address;
import org.testng.annotations.Test;
import tests.base.BaseTest;


public class AddNewDeliveryAddressTest extends BaseTest {
    @Test
    public void addNewAddress() {
        Address address = new Address("220083", "Некрасова", "43", "5", "6", "4");
        userSteps.goToPersonalInfo();
        infoSteps.addAddress(address);
    }
}
