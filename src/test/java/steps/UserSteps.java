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

    @Step("Go to basket")
    public void goToBasket() {
        userPage.goToBasket();
    }
}
