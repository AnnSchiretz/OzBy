package tests;

import org.testng.annotations.Test;

public class ChangeAliasInPersonalAccount extends BaseTest {
    @Test
    public void goToPersonalInfo() {
        String alias = "Hanna";
        userSteps.goToPersonalInfo();
        changeUserAlias(alias);
    }

    private void changeUserAlias(String alias) {
        infoSteps.changeUserAlias(alias);
    }
}
