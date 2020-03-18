package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import page.UserPage;

public class UserSteps {
    private UserPage userPage;

    public UserSteps(WebDriver driver) {
        userPage = new UserPage(driver);
    }

    @Step("Go to personal account")
    public void goToPersonalInfo() {
        userPage.openPage()
                .goToPersonalInfo();
    }

    @Step("Search product")
    public void searchProduct(String name) {
        userPage.searchProduct(name);
    }

    @Step("Go to wish list ")
    public void goToWishList() {
        userPage.goToWishList();
    }

    @Step("Save count of product in wish list")
    public int saveCount() {
        return userPage.countFavoriteProduct();
    }

    @Step("Delete product from wish list")
    public UserSteps deleteProductFromWishList(String name) {
        userPage.deleteProductFromWishList(name);
        return this;
    }

    @Step("Validate count before and after delete product")
    public UserSteps validationAfterDelete(int count) {
        userPage.countAfterDeleteProduct(count);
        return this;
    }

}
