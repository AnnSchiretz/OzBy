package steps;

import io.qameta.allure.Step;
import model.InfoForBirthday;
import org.openqa.selenium.WebDriver;
import page.BirthdayPage;

public class BirthdaySteps {
    private BirthdayPage birthday;


    public BirthdaySteps(WebDriver driver) {
        birthday = new BirthdayPage(driver);
    }

    @Step("Go to Birthday page")
    public int goToBirthday() {
        birthday.openPage();
        return birthday.countBirthdayCard();
    }

    @Step("Add new Birthday")
    public BirthdaySteps addBirthday(InfoForBirthday user) {
        birthday.addNewBirthday(user);
        return this;
    }

    @Step("Check that card has been added")
    public BirthdaySteps birthdayCardValidation(int result) {
        birthday.checkCountBirthdayCards(result);
        return this;
    }
}
