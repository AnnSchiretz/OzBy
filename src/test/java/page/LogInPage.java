package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

public class LogInPage extends BasePage {

    private static final By SEARCH_TOP = By.id("search-top");
    private static final By BUTTON_LOGIN = By.xpath("//a[@onclick='Auth.open();return false;']");
    private static final By FORM_LOGIN = By.id("loginPopupIntro");

    LogInPage(WebDriver driver) {
        super(driver);
    }


    public LogInPage openPage() {
        driver.get("https://oz.by/");
        isPageOpened();
        goToLogin();
        return this;
    }


    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_TOP));
        } catch (TimeoutException ex) {
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    private void goToLogin() {
        driver.findElement(BUTTON_LOGIN).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(FORM_LOGIN));
        AllureUtils.takeScreenshot(driver);
    }
}
