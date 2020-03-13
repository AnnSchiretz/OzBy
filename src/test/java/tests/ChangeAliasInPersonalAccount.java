package tests;

import org.testng.annotations.Test;

public class ChangeAliasInPersonalAccount extends BaseTest {
    @Test
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
