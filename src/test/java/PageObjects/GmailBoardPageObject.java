package PageObjects;

import Entities.Message;
import Tests.BasePageObject;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.util.List;

import static Utils.FileUpload.uploadFilesByRobot;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 23.07.2016.
 */
public class GmailBoardPageObject extends BasePageObject implements ErrorHandlerInterface {

    @FindBy(xpath = "//div[contains(text(), 'COMPOSE')]")
    public WebElement composeButton;

    @FindBy(xpath = "(//*[@class='GS']//div/textarea)[1]")
    public WebElement toField;

    @FindBy(xpath = "//input[@placeholder='Subject']")
    public WebElement subjectField;

    @FindBy(xpath = "(//table)[11]//td[2]//div[2]/div")
    public WebElement messageField;

    @FindBy(xpath = "//div[contains(@aria-label,'Send')]")
    public WebElement sendButton;

    @FindBy(xpath = "//div[contains(@aria-label,'Attach files')]")
    public WebElement attachFile;

    @FindBy(xpath = "(//div[contains(@aria-label,'Uploading')])[2]")
    public WebElement uploadingBar;

    @FindBy(xpath = "//a[contains(text(), 'Sign out')]")
    public WebElement signOutButton;

    @FindBy(xpath = ".//*[@class='gb_b gb_8a gb_R']")
    public WebElement accauntLink;

    @FindBy(xpath = "//span[contains(text(),'Send: uploading attachments...')]")
    public WebElement uploadingToastMessage;

    public EmailPageObject openTargetMail(String subject) {
        WebElement targetMail = null;
        try {
            targetMail = BaseTest.driver.findElement(By.xpath(
                    "//div [@class='y6']//b[contains(text(),'" + subject + "')]/../../span[1]"));
        } catch (Exception e) {
            System.out.println("Mail not found on this page:: " + e);
        }
        assert targetMail != null;
        targetMail.click();
        return new EmailPageObject(driver);
    }


    public void singOutFromGmailBoard() throws InterruptedException {

        clickOnAccauntLink();
        clickOnSignOutButton();
    }

    public void clickOnAccauntLink() {
        $(accauntLink).shouldBe(Condition.visible).click();
    }

    public void clickOnSignOutButton() {
        $(signOutButton).shouldBe(Condition.visible).click();
    }

    public void clickOnComposeButton() {
        $(composeButton).shouldBe(Condition.visible).click();
    }

    public void enterReciever(String recieverEmail) {
        $(toField).shouldBe(Condition.visible).sendKeys(recieverEmail);
    }

    public void enterSubject(String subject) {
        $(subjectField).shouldBe(Condition.visible).sendKeys(subject);
    }

    public void enterMessage(String message) {
        $(messageField).shouldBe(Condition.visible).sendKeys(message);
    }

    public void clickOnSendButton() {
        $(sendButton).shouldBe(Condition.visible).click();
    }

    public void attachFile(String filePath) {
        $(attachFile).sendKeys(filePath);
    }

    public String getTextFromAttachedFile(String filename) {
        return driver.findElement(By.xpath(".//div[contains(text(),'" + filename + "')]")).getText();
    }

    public void composeAndSendMessage(Message message) throws InterruptedException, AWTException {
        clickOnComposeButton();
        enterReciever(message.getReciever());
        enterSubject(message.getSubject());
        enterMessage(message.getMessage());
        uploadFilesByRobot(attachFile, message.getPathToFile());

        try {
            if (getTextFromAttachedFile("message.txt").contains("message.txt")) {
                System.out.println("::File downloaded::");
            } else System.out.println("File did not download correctly");
        } catch (Exception e) {
            System.out.println(e);
        }
        clickOnSendButton();

        try {
            if (uploadingToastMessage.isDisplayed()) {
                Thread.sleep(3000);
            }
            System.out.println("No toast message appear, file uploaded and message has been sent");
        } catch (Exception e) {
            System.out.println("Lets go on)");
        }
    }

    public GmailBoardPageObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getFieldsErrors() {
        return null;
    }
}
