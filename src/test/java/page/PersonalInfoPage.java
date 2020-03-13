package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;
import java.io.File;

import static org.testng.Assert.*;

public class PersonalInfoPage extends BasePage {
    private static final By PERSONAL_INFO = By.cssSelector(".rightcol");
    private static final By ALIAS_INPUT = By.cssSelector(".rec-change input");
    private static final By ALIAS_INPUT_AFTER_CLEAR = By.cssSelector(".rec-v input");
    private static final By SAVE_BUTTON = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_UPDATE = By.cssSelector(".c-green");
    private static final By BUTTON_UPLOAD_IMAGE = By.cssSelector("input[type=file]");
    private static final By AVATAR = By.cssSelector(".avatar-view");
    private static final By IMAGE = By.cssSelector(".avatar-view img");
    private static final By CHANGE_BUTTON = By.cssSelector(".dashed");

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
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public PersonalInfoPage changeAliasAndSave(String alias) {
        driver.findElement(ALIAS_INPUT).clear();
        driver.findElement(ALIAS_INPUT_AFTER_CLEAR).sendKeys(alias);
        driver.findElement(SAVE_BUTTON).click();
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFUL_UPDATE)).getText();
        AllureUtils.takeScreenshot(driver);
        assertEquals(result, "Личные данные успешно сохранены!", "Не произошло обновление данных");
        driver.navigate().refresh();
        return this;
    }

    public PersonalInfoPage validationAlias(String userName) {
        String result = driver.findElement(ALIAS_INPUT).getAttribute("value");
        assertEquals(result, userName, "Не совпадают имена после изменения");
        return this;
    }

    public PersonalInfoPage uploadImage(String pathImg) {
        String avatarBeforeUpdate = driver.findElement(IMAGE).getAttribute("src");
        if (driver.findElement(CHANGE_BUTTON).isDisplayed()) {
            driver.findElement(CHANGE_BUTTON).click();
        }
        WebElement inputImg = driver.findElement(BUTTON_UPLOAD_IMAGE);
        File file = new File(pathImg);
        inputImg.sendKeys(file.getAbsolutePath());
        driver.findElement(SAVE_BUTTON).click();
        AllureUtils.takeScreenshot(driver);
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(AVATAR)).isDisplayed(),
                "Не загрузилось изображение");
        String avatarAfterUpdate = driver.findElement(IMAGE).getAttribute("src");
        assertNotEquals(avatarBeforeUpdate, avatarAfterUpdate, "Не произошла загрузка, т.к url изображений совпадает");
        return this;
    }
}
