package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.LogInSteps;
import steps.PersonalInfoSteps;
import steps.ProductSteps;
import steps.UserSteps;
import utils.CapabilitiesGenerator;
import utils.PropertyManager;

public class BaseTest {
    private WebDriver driver;
    LogInSteps login;
    PersonalInfoSteps infoSteps;
    UserSteps userSteps;
    PropertyManager props;
    ProductSteps product;

    @BeforeMethod(description = "Opening Chrome Driver")
    public void createDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        login = new LogInSteps(driver);
        infoSteps = new PersonalInfoSteps(driver);
        userSteps = new UserSteps(driver);
        product = new ProductSteps(driver);
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
