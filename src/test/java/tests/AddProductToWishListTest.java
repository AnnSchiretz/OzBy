package tests;

import org.testng.annotations.Test;

public class AddProductToWishListTest extends BaseTest {
    @Test
    public void addToWishList() {
        String name = "Ручка шариковая синяя \"Original\" (0,5 мм)";
        userSteps.searchProduct(name);
        int count = product.countBeforeAddToWishList();
        product.choiceProduct(name)
                .validationProductCountInWishList(count);
    }
}
