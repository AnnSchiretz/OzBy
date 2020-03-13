package steps;

import io.qameta.allure.Step;
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
                .changeAliasAndSave(alias);
    }

    @Step("Upload user image")
    public void uploadImg(String path) {
        infoPage.uploadImage(path);
    }

    @Step("Validation user name after update")
    public void validationUserName(String userName) {
        infoPage.validationAlias(userName);
    }
}
