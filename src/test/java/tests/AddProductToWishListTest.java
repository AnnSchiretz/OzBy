package tests;

import org.testng.annotations.Test;
import tests.base.BaseTest;

public class AddProductToWishListTest extends BaseTest {
    @Test(description = "Add product to wish-list and deleting them")
    public void addToWishListAndDelete() {
        String name = "Ручка шариковая синяя \"Original\" (0,5 мм)";
        userSteps.searchProduct(name);
        int count = product.countBeforeAddToWishList();
        product.choiceProduct(name)
                .validationProductCountInWishList(count);
        userSteps.goToWishList();
        int countFavoriteProduct = userSteps.saveCount();
        userSteps.deleteProductFromWishList(name)
                .validationAfterDelete(countFavoriteProduct);
    }
}
