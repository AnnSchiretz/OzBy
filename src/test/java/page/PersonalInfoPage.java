package page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;
import java.io.File;
import java.util.List;

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
    private static final By GO_TO_ADDRESS = By.xpath("//a[contains(text(), 'Адреса доставки')]");
    private static final By ADDED_ADDRESSES = By.cssSelector(".b-addresses-list li");
    private static final By ADDRESS_NAME = By.cssSelector(".b-addresses-list li h2");
    private static final By DELETE_BUTTON = By.cssSelector(".btn-delete");
    private static final By ADDRESS_FORM = By.cssSelector(".address-edit-frm");

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

    public PersonalInfoPage goToAddressInfo() {
        driver.findElement(GO_TO_ADDRESS).click();
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public int getCountAddresses() {
        List<WebElement> addresses;
        if (driver.findElements(ADDED_ADDRESSES).isEmpty()) {
            return 0;
        } else {
            addresses = driver.findElements(ADDED_ADDRESSES);
            assertNotEquals(addresses.size(), 0, "");
        }
        return addresses.size();
    }

    public PersonalInfoPage deleteAddress(String address) {
        List<WebElement> addresses = driver.findElements(ADDED_ADDRESSES);
        if (addresses.size() == 0) {
            driver.findElement(ADDRESS_FORM).isDisplayed();
        } else {
            for (WebElement a : addresses) {
                String name = a.findElement(ADDRESS_NAME).getText();
                System.out.println(name);
                if (name.equals(address)) {
                    Actions act = new Actions(driver);
                    act.moveToElement(a).build().perform();
                    act.moveToElement(a.findElement(DELETE_BUTTON)).build().perform();
                    JavascriptExecutor executor = (JavascriptExecutor) driver;
                    executor.executeAsyncScript("arguments[0].click();", a.findElement(DELETE_BUTTON));
                    driver.switchTo().alert().accept();
                }
            }
        }
        return this;
    }

    public PersonalInfoPage checkCountAddressesAfterDelete(int size) {
        System.out.println(size);
        List<WebElement> addressesAfterAdding = driver.findElements(ADDED_ADDRESSES);
        System.out.println(addressesAfterAdding.size());
        if (size == 0) {
            assertEquals(addressesAfterAdding.size(), size, "Не совпало количество до и после, значит не удалился адрес");
        } else {
            assertEquals(addressesAfterAdding.size(), size - 1, "Не совпало количество до и после, значит не удалился адрес");
        }
        return this;
    }
}
