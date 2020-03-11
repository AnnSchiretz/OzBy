package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.util.Arrays;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class PersonalInfoPage extends BasePage {
    private static final By PERSONAL_INFO = By.cssSelector(".rightcol");
    private static final By ALIAS_INPUT = By.cssSelector(".rec-change input");
    private static final By ALIAS_INPUT_AFTER_CLEAR = By.cssSelector(".rec-v input");
    private static final By SAFE_BUTTON = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_UPDATE = By.cssSelector(".c-green");
    private static final By BUTTON_UPLOAD_IMAGE = By.xpath("//input[@type='file']");
    private static final By AVATAR = By.cssSelector(".avatar-view");
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
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public PersonalInfoPage changeAliasAndSafe(String alias) {
        driver.findElement(ALIAS_INPUT).clear();
        driver.findElement(ALIAS_INPUT_AFTER_CLEAR).sendKeys(alias);
        driver.findElement(SAFE_BUTTON).click();
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFUL_UPDATE)).getText();
        assertEquals(result, "Личные данные успешно сохранены!", "Не произошло обновление данных");
        return this;
    }

    public PersonalInfoPage uploadImage(String pathImg, int count) {
        if (driver.findElement(CHANGE_BUTTON).isDisplayed()) {
            driver.findElement(CHANGE_BUTTON).click();
        }
        WebElement inputImg = driver.findElement(BUTTON_UPLOAD_IMAGE);
        for (int i = 0; i < count; i++) {
            File file = new File(pathImg);
            inputImg.sendKeys(file.getAbsolutePath());
        }
        driver.findElement(SAFE_BUTTON).click();
        assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(AVATAR)).isDisplayed(),
                "Не загрузилось изображение");
        return this;
    }

}
