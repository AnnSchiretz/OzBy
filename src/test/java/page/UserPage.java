package page;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

public class UserPage extends BasePage {
    private static final By USER_ACCOUNT = By.cssSelector(".b-user-card");
    private static final By GO_TO_PERSONAL_INFO = By.xpath("//a[contains(text(), 'Личные данные')]");
    private static final By GO_TO_BIRTHDAYS = By.xpath("//a[contains(text(), 'Дни рождения')]");


    public UserPage(WebDriver driver) {
        super(driver);
    }

    public UserPage openPage() {
        driver.get("https://oz.by/personal/");
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(USER_ACCOUNT));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public PersonalInfoPage goToPersonalInfo() {
        driver.findElement(GO_TO_PERSONAL_INFO).click();
        PersonalInfoPage info = new PersonalInfoPage(driver);
        info.openPage();
        return info;
    }

    public BirthdayPage goToBirthdays() {
        driver.findElement(GO_TO_BIRTHDAYS).click();
        BirthdayPage birthday = new BirthdayPage(driver);
        birthday.openPage();
        return birthday;
    }
}
