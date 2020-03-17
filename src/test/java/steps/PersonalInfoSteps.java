package steps;

import io.qameta.allure.Step;
import models.Address;
import org.openqa.selenium.WebDriver;
import page.PersonalInfoPage;


public class PersonalInfoSteps {
    private PersonalInfoPage infoPage;


    public PersonalInfoSteps(WebDriver driver) {
        infoPage = new PersonalInfoPage(driver);
    }

    @Step("Change user alias")
    public void changeUserAlias(String newAlias) {
        infoPage.openPage()
                .changeAliasAndSave(newAlias);
    }

    @Step("Upload user image")
    public void uploadImg(String path) {
        infoPage.uploadImage(path);
    }

    @Step("Validation user name after update")
    public void validationUserName(String userName) {
        infoPage.validationAlias(userName);
    }

    @Step("Add new address")
    public void addAddress(Address address) {
        infoPage.goToAddressInfo();
        int sizeAddress = infoPage.checkAddressBeforeAdding();
        infoPage.addAddressDelivery(address);
        infoPage.checkAddressAfterAdding(sizeAddress);
    }
    @Step("Go to addresses list and check count before delete ")
    public int goToAddresses() {
        infoPage.goToAddressInfo();
        int result = infoPage.getCountAddresses();
        return result;
    }

    @Step("Delete address and check count after deleting")
    public void deleteAddresses(String address, int result) {
        infoPage.deleteAddress(address)
                .checkCountAddressesAfterDelete(result);
    }
}
