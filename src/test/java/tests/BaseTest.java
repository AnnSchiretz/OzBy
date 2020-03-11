package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import steps.LogInSteps;
import utils.CapabilitiesGenerator;
import utils.PropertyManager;

public class BaseTest {
    private WebDriver driver;
    protected LogInSteps login;
    PropertyManager props;

    @BeforeMethod(description = "Opening Chrome Driver")
    public void createDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        login = new LogInSteps(driver);
    }

    @BeforeMethod
    public void data() {
        props = new PropertyManager();
    }

    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
