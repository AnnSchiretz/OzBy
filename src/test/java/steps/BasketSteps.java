package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.BasketPage;

public class BasketSteps {
    private BasketPage basket;

    public BasketSteps(WebDriver driver) {
        basket = new BasketPage(driver);
    }

    @Step("Check count product in basket")
    public int checkCountProduct() {
        basket.openPage()
                .countProduct();
        return basket.countProduct();
    }

    @Step("Delete product in basket")
    public BasketSteps deleteProduct(String name) {
        basket.deleteProduct(name);
        return this;
    }

    @Step("Validation product after delete")
    public BasketSteps validationAfterDelete(int count) {
        basket.validationAfterDelete(count);
        return this;
    }
}
