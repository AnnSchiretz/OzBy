package tests;

import org.testng.annotations.Test;

public class AddProductToBasketTest extends BaseTest {
    @Test
    public void addProductToBasket() {
        String nameProduct = "Ведьмак. Час Презрения";
        userSteps.searchProduct(nameProduct);
        int count = product.countProductBefore();
        product.choiceProduct(nameProduct)
                .validationProductCount(count);
    }
}