package page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

public class BasketPage extends BasePage {
    private static final By PRODUCT_IN_BASKET = By.cssSelector(".i-layout-column_full table");
    private static final By EMPTY_BASKET = By.cssSelector(".i-textual__plain");
    private static final By PRODUCT_LIST_IN_BASKET = By.xpath("//div[contains(@class, 'i-layout-column_full')]//tr[contains(@class, 'goods-table__row ')]");
    private static final By PRODUCT_NAME = By.xpath("//div[@class='goods-table-cell__link']/a");
    private static final By PRODUCT_CHECKBOX = By.xpath("//div[@class='goods-table-cell']//input");
    private static final By DELETE_BUTTON = By.xpath("//div[contains(@class,'i-layout-column_full')]//button[contains(@class, 'remove')]");
    private static final By DELETE_CONFIRMATION = By.cssSelector(".goods-table-popup_visible .remove-yes");
    private static final By INFO_MODAL_LIST = By.cssSelector(".i-popover__footer");
    private static final By POP_UP_INFO = By.xpath("//tr[contains(@class, 'goods-table__row_temp')]/following-sibling::tr//button[contains(@class, 'goods-order-help-popup')]");
    private static final By POP_UP_INFO2 = By.xpath("//div[contains(@class, 'deal-form-main__popover_top')]//button");

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public BasketPage openPage() {
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        if (driver.findElements(INFO_MODAL_LIST).size() > 2) {
            driver.findElement(POP_UP_INFO).click();
        }
        if (driver.findElements(PRODUCT_IN_BASKET).isEmpty()) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(EMPTY_BASKET));
            } catch (TimeoutException ex) {
                AllureUtils.takeScreenshot(driver);
                throw new TimeoutException("Страница не загрузилась");
            }
        }
        if (!driver.findElements(PRODUCT_IN_BASKET).isEmpty()) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(PRODUCT_IN_BASKET));
            } catch (TimeoutException ex) {
                AllureUtils.takeScreenshot(driver);
                throw new TimeoutException("Страница не загрузилась");
            }
        }
    }

    public int countProduct() {
        List<WebElement> list;
        if (driver.findElements(PRODUCT_LIST_IN_BASKET).isEmpty()) {
            return 0;
        } else {
            list = driver.findElements(PRODUCT_LIST_IN_BASKET);
            assertNotEquals(list.size(), 0, "Равны значения, значит размер листа равен 0");
            AllureUtils.takeScreenshot(driver);
        }
        return list.size();
    }

    public BasketPage deleteProduct(String name) {
        List<WebElement> list = driver.findElements(PRODUCT_LIST_IN_BASKET);
        for (WebElement product : list) {
            String productName = product.findElement(PRODUCT_NAME).getText();
            if (productName.equals(name)) {
                product.findElement(PRODUCT_CHECKBOX).click();
                if (driver.findElements(INFO_MODAL_LIST).size() > 2) {
                    AllureUtils.takeScreenshot(driver);
                    driver.findElement(POP_UP_INFO2).click();
                }
                WebElement firstButton = wait.until(ExpectedConditions.elementToBeClickable(DELETE_BUTTON));
                firstButton.click();
                WebElement confButton = wait.until(ExpectedConditions.elementToBeClickable(DELETE_CONFIRMATION));
                wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(DELETE_CONFIRMATION), "Да, удалить"));
                confButton.click();
                break;
            }
        }
        driver.navigate().refresh();
        driver.findElement(EMPTY_BASKET).isDisplayed();
        return this;
    }

    public BasketPage validationAfterDelete(int count) {
        List<WebElement> list = driver.findElements(PRODUCT_LIST_IN_BASKET);
        if (list.size() == 0) {
            driver.findElement(EMPTY_BASKET).isDisplayed();
        } else {
            assertEquals(count - 1, list.size(), "Не сошлось количество продуктов после удаления, значит не удалился продукт");
            AllureUtils.takeScreenshot(driver);
        }
        return this;
    }
}
