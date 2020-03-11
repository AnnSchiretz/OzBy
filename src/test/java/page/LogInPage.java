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
    private static final By LOGIN_BY_EMAIL = By.id("loginFormLoginEmailLink");
    private static final By EMAIL_INPUT = By.cssSelector(".i-input-group__cell input");
    private static final By PASSWORD_INPUT = By.cssSelector(".i-input-group__cell .i-input_with-padding");
    private static final By LOGIN_BUTTON = By.cssSelector(".authorize button");
    private static final By ICON_USER = By.cssSelector(".top-panel__userbar__dlink--drop");

    public LogInPage(WebDriver driver) {
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
        driver.findElement(LOGIN_BY_EMAIL).click();
    }

    public LogInPage inputEmailAndPassword(String email, String password) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(ICON_USER));
        AllureUtils.takeScreenshot(driver);
        return this;
    }
}
