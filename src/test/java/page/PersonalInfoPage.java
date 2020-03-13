package page;

import models.Address;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.io.File;
import java.util.List;

import static org.testng.Assert.*;

public class PersonalInfoPage extends BasePage {
    private static final By PERSONAL_INFO = By.cssSelector(".rightcol");
    private static final By ALIAS_INPUT = By.cssSelector(".rec-change input");
    private static final By ALIAS_INPUT_AFTER_CLEAR = By.cssSelector(".rec-v input");
    private static final By SAFE_BUTTON = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_UPDATE = By.cssSelector(".c-green");
    private static final By BUTTON_UPLOAD_IMAGE = By.xpath("//input[@type='file']");
    private static final By AVATAR = By.cssSelector(".avatar-view");
    private static final By CHANGE_BUTTON = By.cssSelector(".dashed");
    private static final By GO_TO_ADDRESS = By.xpath("//a[contains(text(), 'Адреса доставки')]");
    private static final By ADDRESS_FORM = By.cssSelector(".address-edit-frm");
    private static final By ZIP_CODE = By.id("i-zip");
    private static final By STREET_INPUT = By.id("i-street");
    private static final By NUMBER_HOUSE = By.id("i-house");
    private static final By NUMBER_APARTMENT = By.id("i-flat");
    private static final By NUMBER_ENTRANCE = By.id("i-entrance");
    private static final By NUMBER_STOREY = By.id("i-floor");
    private static final By ADD_ADDRESS = By.cssSelector(".button-small");
    private static final By SUCCESSFUL_ADD_ADDRESS = By.cssSelector(".attention-imp-p");
    private static final By ADDED_ADDRESSES = By.cssSelector(".b-addresses-list li");
    private static final By ADD_ANOTHER_ADDRESS_BUTTON = By.cssSelector(".b-addresses-add a");

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

    public PersonalInfoPage goToAddressInfo() {
        driver.findElement(GO_TO_ADDRESS).click();
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    public PersonalInfoPage addAddressDelivery(Address address) {
        if (driver.findElements(ADDED_ADDRESSES).size() > 0) {
            driver.findElement(ADD_ANOTHER_ADDRESS_BUTTON).click();
        }
        AllureUtils.takeScreenshot(driver);
        driver.findElement(ADDRESS_FORM).isDisplayed();
        driver.findElement(ZIP_CODE).sendKeys(address.getZipCode());
        driver.findElement(STREET_INPUT).sendKeys(address.getStreet());
        driver.findElement(NUMBER_HOUSE).sendKeys(address.getNumberHouse());
        driver.findElement(NUMBER_APARTMENT).sendKeys(address.getNumberApartment());
        driver.findElement(NUMBER_ENTRANCE).sendKeys(address.getEntrance());
        driver.findElement(NUMBER_STOREY).sendKeys(address.getStorey());
        driver.findElement(ADD_ADDRESS).click();
        String result = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESSFUL_ADD_ADDRESS)).getText();
        assertEquals("Адрес успешно добавлен\n" +
                "Позже, если захотите, вы сможете его удалить", result, "Произошла ошибка");
        driver.findElement(GO_TO_ADDRESS).click();
        return this;
    }

    public int checkAddressBeforeAdding() {
        List<WebElement> addresses;
        if (driver.findElements(ADDED_ADDRESSES).isEmpty()) {
            return 0;
        } else {
            addresses = driver.findElements(ADDED_ADDRESSES);
            assertNotEquals(addresses.size(), 0, "Количество адресов равно 0, либо что-то пошло не так");
            AllureUtils.takeScreenshot(driver);
        }
        return addresses.size();
    }

    public PersonalInfoPage checkAddressAfterAdding(int size) {
        List<WebElement> addressesAfterAdding = driver.findElements(ADDED_ADDRESSES);
        assertEquals(size + 1, addressesAfterAdding.size(), "Не совпало количество адресов после добавления и до добавления");
        return this;
    }
}
