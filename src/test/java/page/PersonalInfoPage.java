package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertEquals;

public class PersonalInfoPage extends BasePage {
    private static final By PERSONAL_INFO = By.cssSelector(".rightcol");
    private static final By ALIAS_INPUT = By.cssSelector(".rec-change input");
    private static final By ALIAS_INPUT_AFTER_CLEAR = By.cssSelector(".rec-v input");
    private static final By SAFE_BUTTON = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_UPDATE = By.cssSelector(".c-green");

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
    }

    public PersonalInfoPage openPage() {
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_INFO));
        } catch (TimeoutException ex) {
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public PersonalInfoPage changeAliasAndSafe(String alias) {
        driver.findElement(ALIAS_INPUT).clear();
        driver.findElement(ALIAS_INPUT_AFTER_CLEAR).sendKeys(alias);
        driver.findElement(SAFE_BUTTON).click();
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFUL_UPDATE)).getText();
        assertEquals(result, "Личные данные успешно сохранены!", "Не произошло обновление данных");
        driver.navigate().refresh();
        return this;
    }

    public PersonalInfoPage validationAlias(String userName) {
        String result = driver.findElement(ALIAS_INPUT).getAttribute("value");
        assertEquals(result, userName, "Не совпадают имена после изменения");
        return this;
    }

}
