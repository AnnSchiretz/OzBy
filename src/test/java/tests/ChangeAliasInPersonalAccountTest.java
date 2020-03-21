package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class ChangeAliasInPersonalAccountTest extends BaseTest {
    @Test(description = "Change user alias in personal info page")
    public void goToPersonalInfo() {
        String alias = "Helena";
        String newAlias = "Hanna";
        userSteps.goToPersonalInfo();
        infoSteps.changeUserAlias(alias);
        infoSteps.validationUserName(alias);
        infoSteps.changeUserAlias(newAlias);
        infoSteps.validationUserName(newAlias);

    }
}
