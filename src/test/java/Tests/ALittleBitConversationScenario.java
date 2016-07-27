package Tests;

import Entities.Message;
import PageObjects.BaseTest;
import Data.DataProviders;
import PageObjects.GmailBoardPageObject;
import PageObjects.GoogleStartPageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Actions.EmailActions.closeAndDeleteMessage;
import static Actions.SignInActions.signInGoogleAccount;
import static Utils.Helpers.GmailRefresh;

/**
 * Created by DeBeers on 23.07.2016.
 */
public class ALittleBitConversationScenario extends BaseTest {

    @Test(dataProvider="positiveLoginData", dataProviderClass= DataProviders.class)
    public void twoYsersConversationScenario(String reciever, String subject,
                                             String textMessage, String file) throws Exception {

        Message message = new Message(reciever, subject, textMessage, file);

        GoogleStartPageObject googleStartPageObject = signInGoogleAccount(sender);
        Assert.assertTrue(googleStartPageObject.checkUserAccEmail(sender.getLogin()));

        googleStartPageObject
                .openGmail()
                .composeAndSendMessage(message);
        GmailRefresh();

        GmailBoardPageObject gmailBoardPageObject = new GmailBoardPageObject(driver);

        String attachmentText = gmailBoardPageObject
                .openTargetMail(subject)
                .openAttacment()
                .getTextFromAttachmentContent();

        Assert.assertEquals(attachmentText, "Hello nigga!)", "File content is not equals");
        closeAndDeleteMessage();
    }
}
