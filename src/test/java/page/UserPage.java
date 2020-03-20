package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class UserPage extends BasePage {
    private static final By USER_ACCOUNT = By.cssSelector(".b-user-card");
    private static final By GO_TO_PERSONAL_INFO = By.xpath("//a[contains(text(), 'Личные данные')]");
    private static final By GO_TO_BIRTHDAYS = By.xpath("//a[contains(text(), 'Дни рождения')]");
    private static final By GO_TO_WISHLIST = By.id("user-tab-wishlist");
    private static final By SEARCH_PRODUCT = By.id("top-s");
    private static final By PRODUCT_IN_WISHLIST = By.cssSelector(".viewer-type-card_has-filter li");
    private static final By PRODUCT_NAME = By.cssSelector(".item-type-card__title");
    private static final By DELETE_BUTTON = By.cssSelector(".item-type-card__controls-trigger");
    private static final By CONFIRM_DELETE = By.cssSelector(".item-type-card__controls-button");
    private static final By DELETE_SPINER = By.cssSelector(".item-type-card__controls-button-spinner");

    public UserPage(WebDriver driver) {
        super(driver);
    }

    public UserPage openPage() {
        driver.get("https://oz.by/personal/");
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ACCOUNT));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public PersonalInfoPage goToPersonalInfo() {
        driver.findElement(GO_TO_PERSONAL_INFO).click();
        PersonalInfoPage info = new PersonalInfoPage(driver);
        info.openPage();
        return info;
    }

    public BirthdayPage goToBirthdays() {
        driver.findElement(GO_TO_BIRTHDAYS).click();
        BirthdayPage birthday = new BirthdayPage(driver);
        birthday.openPage();
        return birthday;
    }

    public UserPage goToWishList() {
        openPage();
        driver.findElement(GO_TO_WISHLIST).click();
        return this;
    }

    public ProductPage searchProduct(String name) {
        driver.findElement(SEARCH_PRODUCT).sendKeys(name);
        driver.findElement(SEARCH_PRODUCT).sendKeys(Keys.ENTER);
        return new ProductPage(driver);
    }

    public int countFavoriteProduct() {
        List<WebElement> list = driver.findElements(PRODUCT_IN_WISHLIST);
        return list.size();
    }

    public UserPage deleteProductFromWishList(String name) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PRODUCT_IN_WISHLIST));
        List<WebElement> list = driver.findElements(PRODUCT_IN_WISHLIST);
        for (WebElement product : list) {
            String productName = product.findElement(PRODUCT_NAME).getText();
            if (productName.equals(name)) {
                Actions act = new Actions(driver);
                act.moveToElement(product).build().perform();
                act.moveToElement(product.findElement(DELETE_BUTTON)).build().perform();
                act.click(product.findElement(DELETE_BUTTON)).build().perform();
                act.click(product.findElement(CONFIRM_DELETE)).build().perform();
                wait.until(ExpectedConditions.invisibilityOf(product.findElement(DELETE_SPINER)));
            }
            break;
        }
        driver.navigate().refresh();
        return this;
    }

    public UserPage countAfterDeleteProduct(int count) {
        List<WebElement> list = driver.findElements(PRODUCT_IN_WISHLIST);
        assertEquals(count - 1, list.size(), " Не удалился продукт");
        return this;
    }
}
