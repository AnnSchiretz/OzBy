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
    public void changeUserAlias(String alias) {
        infoPage.openPage()
                .changeAliasAndSafe(alias);
    }
}
