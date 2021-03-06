package tests;

import models.Address;
import tests.base.BaseTest;
import org.testng.annotations.Test;


public class AddNewDeliveryAddressTest extends BaseTest {
    @Test(description = "Add new delivery address in personal account")
    public void addNewAddress() {
        Address address = new Address("220083", "Некрасова", "43", "5", "6", "4");
        userSteps.goToPersonalInfo();
        infoSteps.addAddress(address);
    }
}
