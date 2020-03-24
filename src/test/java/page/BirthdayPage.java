package page;

import models.InfoForBirthday;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class BirthdayPage extends BasePage {
    private static final By BIRTHDAY_PAGE = By.id("birthdays-list-container");
    private static final By ADD_BIRTHDAY = By.cssSelector(".add-birthday");
    private static final By BIRTHDAY_FORM = By.cssSelector(".i-popup-birthday");
    private static final By BIRTHDAY_NAME = By.name("birthday[name]");
    private static final By BIRTHDAY_PHONE = By.name("birthday[phone]");
    private static final By BIRTHDAY_DAY = By.name("birthday[date]");
    private static final By BIRTHDAY_MONTH = By.name("birthday[month]");
    private static final By BIRTHDAY_YEAR = By.name("birthday[year]");
    private static final By FEMALE_GENDER = By.xpath("//input[@value='female']/../..");
    private static final By MALE_GENDER = By.xpath("//input[@value='male']/../..");
    private static final By BIRTHDAY_COMMENT = By.name("birthday[note]");
    private static final By ADD_BIRTHDAY_BUTTON = By.cssSelector(".i-popup__footer-accent button");
    private static final By BIRTHDAY_CARDS = By.cssSelector(".edit-birthday");
    private static final By BIRTHDAY_NAME_CARD = By.cssSelector(".birthday-card__name");
    private static final By DELETE_BIRTHDAY = By.cssSelector(".delete-birthday");
    private static final By EDIT_FORM = By.cssSelector(".i-popup-birthday__edit");
    private static final By DELETE_CONFIRM = By.cssSelector(".delete-birthday-confirmed");
    private static final By POPUP_CONFIRM = By.cssSelector(".i-popup__line_delete");

    public BirthdayPage(WebDriver driver) {
        super(driver);
    }

    public BirthdayPage openPage() {
        isPageOpened();
        return this;
    }

    private void isPageOpened() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(BIRTHDAY_PAGE));
        } catch (TimeoutException ex) {
            AllureUtils.takeScreenshot(driver);
            throw new TimeoutException("Страница не загрузилась");
        }
    }

    public int countBirthdayCard() {
        List<WebElement> card = driver.findElements(BIRTHDAY_CARDS);
        return card.size();
    }

    public BirthdayPage addNewBirthday(InfoForBirthday user) {
        driver.findElement(ADD_BIRTHDAY).click();
        driver.findElement(BIRTHDAY_FORM).isDisplayed();
        driver.findElement(BIRTHDAY_NAME).sendKeys(user.getName());
        driver.findElement(BIRTHDAY_PHONE).sendKeys(user.getTelephone());
        driver.findElement(BIRTHDAY_DAY).click();
        Select selectDay = new Select(driver.findElement(BIRTHDAY_DAY));
        selectDay.selectByValue(user.getDay());
        driver.findElement(BIRTHDAY_MONTH).click();
        Select selectMonth = new Select(driver.findElement(BIRTHDAY_MONTH));
        selectMonth.selectByVisibleText(user.getMonth());
        driver.findElement(BIRTHDAY_YEAR).click();
        Select selectYear = new Select(driver.findElement(BIRTHDAY_YEAR));
        selectYear.selectByVisibleText(user.getYear());
        driver.findElement(BIRTHDAY_YEAR).click();
        if (user.getGender().equals("Женский")) {
            driver.findElement(FEMALE_GENDER).click();
        } else {
            driver.findElement(MALE_GENDER).click();
        }
        driver.findElement(BIRTHDAY_COMMENT).sendKeys(user.getComment());
        driver.findElement(ADD_BIRTHDAY_BUTTON).click();
        AllureUtils.takeScreenshot(driver);
        driver.navigate().refresh();
        return this;
    }

    public BirthdayPage checkCountBirthdayCardsAfterAdding(int size) {
        List<WebElement> cards = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(BIRTHDAY_CARDS));
        assertEquals(size + 1, cards.size(), "Не произошло добавление карточки дня рождения");
        return this;
    }

    public BirthdayPage checkCountAfterDelete(int size) {
        List<WebElement> cards = driver.findElements(BIRTHDAY_CARDS);
        assertEquals(size - 1, cards.size(), "Не произошло удаления карточки дня рождения");
        return this;
    }

    public BirthdayPage deleteBirthdayCard(String name) {
        List<WebElement> birthdayCards = driver.findElements(BIRTHDAY_CARDS);
        for (WebElement card : birthdayCards) {
            String birthdayName = card.findElement(BIRTHDAY_NAME_CARD).getText();
            System.out.println(birthdayName);
            if (birthdayName.equals(name)) {
                wait.until(ExpectedConditions.elementToBeClickable(card)).click();
                driver.findElement(EDIT_FORM).isDisplayed();
                driver.findElement(DELETE_BIRTHDAY).click();
                AllureUtils.takeScreenshot(driver);
                wait.until(ExpectedConditions.visibilityOfElementLocated(POPUP_CONFIRM)).isDisplayed();
                WebElement confirmDelete = driver.findElement(DELETE_CONFIRM);
                wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();
            }
        }
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOfElementLocated(BIRTHDAY_CARDS));
        return this;
    }

}
