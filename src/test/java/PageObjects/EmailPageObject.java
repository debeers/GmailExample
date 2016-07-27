package PageObjects;

import Tests.BasePageObject;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 25.07.2016.
 */
public class EmailPageObject extends BasePageObject implements ErrorHandlerInterface {

    @FindBy(xpath = ".//div[contains(text(),'Attachments area')]/following-sibling::div[2]/span/a")
    public WebElement attachment;

    @FindBy(xpath = "//pre")
    public WebElement attachmentContent;

    @FindBy(xpath = "(//div[@title='Delete'])")
    public WebElement deleteMessageButton;

    @FindBy(xpath = "//span[contains(text(),'The conversation has been moved to the Trash.')]")
    public WebElement deleteMessage;


    public void deleteCurrentEmail() throws InterruptedException {
        clickOnDeleteMessageButton();
        getDeleteMessageToast();

    }

    public void getDeleteMessageToast() {
        try {
            if ($(deleteMessage).shouldBe(Condition.visible).isDisplayed()) {
                System.out.println("Message deleted");
            }
        } catch (Exception e) {
            System.out.println("Message did not delete");
        }
    }

    public void clickOnDeleteMessageButton() throws InterruptedException {
        try {
            deleteMessageButton.click();
        } catch (Exception e) {
            System.out.println("Delete button did not detected");
        }

    }

    public String getTextFromAttachmentContent() {
        return $(attachmentContent).shouldBe(Condition.visible).getText();
    }

    public EmailPageObject openAttacment() {
        $(attachment).shouldBe(Condition.visible).click();
        return this;
    }

    public EmailPageObject(WebDriver driver) {
        super(driver);
    }

    @Override
    public List<String> getFieldsErrors() {
        return null;
    }

}
