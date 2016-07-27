package Actions;

import PageObjects.EmailPageObject;
import org.openqa.selenium.Keys;

import static PageObjects.BaseTest.driver;
import static Utils.Helpers.GmailRefresh;

import org.openqa.selenium.interactions.Actions;
/**
 * Created by DeBeers on 25.07.2016.
 */
public class EmailActions {

    public static void closeAndDeleteMessage() throws InterruptedException {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.ESCAPE).perform();
        GmailRefresh();
        EmailPageObject emailPageObject = new EmailPageObject(driver);
        emailPageObject.deleteCurrentEmail();
    }
}
