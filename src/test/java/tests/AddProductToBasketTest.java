package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class AddProductToBasketTest extends BaseTest {
    @Test(description = "Add product in user basket and removing them")
    public void addAndDeleteProductToBasket() {
        String nameProduct = "Ведьмак. Час Презрения";
        userSteps.searchProduct(nameProduct);
        int count = product.countProductBefore();
        product.choiceProduct(nameProduct)
                .validationProductCount(count);
        userSteps.goToBasket();
        int countSecond = basket.checkCountProduct();
        basket.deleteProduct(nameProduct)
                .validationAfterDelete(countSecond);
    }
}
