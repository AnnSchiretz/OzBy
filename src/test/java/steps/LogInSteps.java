package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.LogInPage;

public class LogInSteps {
    private LogInPage login;


    public LogInSteps(WebDriver driver) {
        login = new LogInPage(driver);
    }

    @Step("Log in OZ.by")
    public LogInSteps ozIsOpenRegistrationPage() {
        login.openPage();
        return this;
    }

    @Step("Send email and password")
    public LogInSteps login(String email, String password) {
        login.sendEmailAndPassword(email, password);
        return this;
    }

}
