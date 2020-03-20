package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class AddProductToBasketTest extends BaseTest {
    @Test
    public void addProductToBasket() {
        String nameProduct = "Ведьмак. Час Презрения";
        userSteps.searchProduct(nameProduct);
        int count = product.countProductBefore();
        product.choiceProduct(nameProduct)
                .validationProductCount(count);
    }

    @Test
    public void deleteProduct() {
        String nameProduct = "Ведьмак. Час Презрения";
        userSteps.goToBasket();
        int count = basket.checkCountProduct();
        basket.deleteProduct(nameProduct)
                .validationAfterDelete(count);
    }
}
