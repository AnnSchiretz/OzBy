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
        product.checkProductCountAfterAdding(count);
        return this;
    }
}
