package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.BirthdaySteps;
import steps.LogInSteps;
import steps.PersonalInfoSteps;
import steps.UserSteps;
import utils.CapabilitiesGenerator;
import utils.PropertyManager;

public class BaseTest {
    private WebDriver driver;
    LogInSteps login;
    PersonalInfoSteps infoSteps;
    UserSteps userSteps;
    PropertyManager props;
    BirthdaySteps birthday;

    @BeforeMethod(description = "Opening Chrome Driver")
    public void createDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        login = new LogInSteps(driver);
        infoSteps = new PersonalInfoSteps(driver);
        userSteps = new UserSteps(driver);
        birthday = new BirthdaySteps(driver);
    }

    @BeforeMethod
    public void data() {
        props = new PropertyManager();
    }

    @BeforeMethod
    public void login() {
        login.ozIsOpenRegistrationPage()
                .login(props.get("email"), props.get("password"));

    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
