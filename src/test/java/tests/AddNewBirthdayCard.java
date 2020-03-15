package tests;

import model.InfoForBirthday;
import org.testng.annotations.Test;

public class AddNewBirthdayCard extends BaseTest {
    @Test
    public void addBirthday() {
        InfoForBirthday user = new InfoForBirthday("Маргарита", "+375336754534", "05", "декабря", "1990", "Женский", "алалалалалала");
        userSteps.goToBirthdays();
        int count = birthday.goToBirthday();
        birthday.addBirthday(user)
                .birthdayCardValidation(count);
    }
}
