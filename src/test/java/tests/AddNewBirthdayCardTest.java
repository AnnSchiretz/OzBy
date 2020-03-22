package tests;

import models.InfoForBirthday;
import tests.base.BaseTest;
import org.testng.annotations.Test;

public class AddNewBirthdayCardTest extends BaseTest {
    @Test(description = "Add and delete birthday card in personal account")
    public void addBirthdayCardAndDelete() {
        InfoForBirthday user = new InfoForBirthday("Маргарита", "+375336754534", "05", "декабря", "1990", "Женский", "алалалалалала");
        userSteps.goToBirthdays();
        int count = birthday.countBeforeChanges();
        birthday.addBirthday(user)
                .birthdayCardValidation(count);
        int countSecond = birthday.countBeforeChanges();
        birthday.deleteCard(user.getName())
                .validationAfterDelete(countSecond);
    }
}
