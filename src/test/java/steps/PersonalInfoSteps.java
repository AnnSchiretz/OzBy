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
    public void changeUserAlias(String alias) {
        infoPage.openPage()
                .changeAliasAndSafe(alias);
    }

    @Step("Upload user image")
    public void uploadImg(String path, int count) {
        infoPage.uploadImage(path, count);
    }

    @Step("Add new address")
    public void addAddress(Address address) {
        infoPage.goToAddressInfo();
        int sizeAddress = infoPage.checkAddressBeforeAdding();
        infoPage.addAddressDelivery(address);
        infoPage.checkAddressAfterAdding(sizeAddress);
    }
}
