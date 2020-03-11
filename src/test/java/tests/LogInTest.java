package tests;

import org.testng.annotations.Test;

public class LogInTest extends BaseTest {
    @Test
    public void logIn() {
        login.ozIsOpenRegistrationPage()
                .login(props.get("email"), props.get("password"));
    }
}
