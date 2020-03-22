package steps;

import io.qameta.allure.Step;
import models.InfoForBirthday;
import org.openqa.selenium.WebDriver;
import page.BirthdayPage;

public class BirthdaySteps {
    private BirthdayPage birthday;


    public BirthdaySteps(WebDriver driver) {
        birthday = new BirthdayPage(driver);
    }

    @Step("Go to Birthday page")
    public int countBeforeChanges() {
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
        birthday.checkCountBirthdayCardsAfterAdding(result);
        return this;
    }

    @Step("Delete a birthday card")
    public BirthdaySteps deleteCard(String name) {
        birthday.deleteBirthdayCard(name);
        return this;
    }

    @Step("Validation cards after delete")
    public BirthdaySteps validationAfterDelete(int count) {
        birthday.checkCountAfterDelete(count);
        return this;
    }
}
