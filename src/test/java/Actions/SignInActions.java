package Actions;

import Entities.LoginObject;
import PageObjects.BaseTest;
import PageObjects.GmailBoardPageObject;
import PageObjects.GoogleStartPageObject;

/**
 * Created by DeBeers on 23.07.2016.
 */
public class SignInActions extends BaseTest {

    public static GoogleStartPageObject signInGoogleAccount(LoginObject sender) {

        GoogleStartPageObject start = new GoogleStartPageObject(driver);
        return start.
                clickOnEnterToGoogleAccButton()
                .enterEmailAndClickNextButton(sender.getLogin())
                .enterPasswordAndClickOnSignInButton(sender.getPassword());

    }

    public static void signOut() throws InterruptedException {

        GmailBoardPageObject googleStart = new GmailBoardPageObject(driver);
        googleStart.singOutFromGmailBoard();

    }

}
