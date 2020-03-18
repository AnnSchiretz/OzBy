package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class AddProductToWishListTest extends BaseTest {
    @Test
    public void addToWishList() {
        String name = "Ручка шариковая синяя \"Original\" (0,5 мм)";
        userSteps.searchProduct(name);
        int count = product.countBeforeAddToWishList();
        product.choiceProduct(name)
                .validationProductCountInWishList(count);
    }

    @Test
    public void deleteProductFromWishList() {
        String name = "Ручка шариковая синяя \"Original\" (0,5 мм)";
        userSteps.goToWishList();
        int count = userSteps.saveCount();
        userSteps.deleteProductFromWishList(name)
                .validationAfterDelete(count);
    }
}
