package tests;

import model.InfoForBirthday;
import org.testng.annotations.Test;

public class AddNewBirthdayCardTest extends BaseTest {
    @Test
    public void addBirthday() {
        InfoForBirthday user = new InfoForBirthday("Маргарита", "+375336754534", "05", "декабря", "1990", "Женский", "алалалалалала");
        userSteps.goToBirthdays();
        int count = birthday.countBeforeChanges();
        birthday.addBirthday(user)
                .birthdayCardValidation(count);
    }

    @Test
    public void deleteBirthdayCard() {
        InfoForBirthday user = new InfoForBirthday("Маргарита", "+375336754534", "05", "декабря", "1990", "Женский", "алалалалалала");
        userSteps.goToBirthdays();
        int count = birthday.countBeforeChanges();
        birthday.deleteCard(user.getName())
                .validationAfterDelete(count);
    }
}
