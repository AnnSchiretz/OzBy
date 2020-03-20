package tests.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import steps.*;
import utils.CapabilitiesGenerator;
import utils.PropertyManager;

import static com.codeborne.selenide.Selenide.closeWebDriver;


public class BaseTest {
    private WebDriver driver;
    public LogInSteps login;
    public PersonalInfoSteps infoSteps;
    public UserSteps userSteps;
    public PropertyManager props;
    public ProductSteps product;
    public BasketSteps basket;

    @BeforeMethod(description = "Opening Chrome Driver")
    public void createDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        login = new LogInSteps(driver);
        infoSteps = new PersonalInfoSteps(driver);
        userSteps = new UserSteps(driver);
        product = new ProductSteps(driver);
        basket = new BasketSteps(driver);
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

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }
}
