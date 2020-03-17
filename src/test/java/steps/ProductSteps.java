package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.ProductPage;

public class ProductSteps {
    private ProductPage product;

    public ProductSteps(WebDriver driver) {
        product = new ProductPage(driver);
    }

    @Step("Get count item in basket before adding")
    public int countProductBefore() {
        return product.countBeforeAddProduct();
    }

    @Step("Choice a product in product list")
    public ProductSteps choiceProduct(String name) {
        product.isPageOpened()
                .choiceProduct(name);
        return this;
    }

    @Step("Validation count product after adding")
    public ProductSteps validationProductCount(int count) {
        product.addProductAndCheckCountAfterAdding(count);
        return this;
    }

    @Step("Count product before add to wish list")
    public int countBeforeAddToWishList() {
        return product.countBeforeAddInWishList();
    }

    @Step("Count product after adding in wish list")
    public ProductSteps validationProductCountInWishList(int count) {
        product.addProductToWishListAndCheckCount(count);
        return this;
    }
}
