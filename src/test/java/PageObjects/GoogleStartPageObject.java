package PageObjects;

import Tests.BasePageObject;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by DeBeers on 23.07.2016.
 */
public class GoogleStartPageObject extends BasePageObject implements ErrorHandlerInterface{

    @FindBy(xpath = ".//*[@class='gb_b gb_Rb']")
    public WebElement googleAppsButton;

    @FindBy(xpath = ".//*[@id='gb23']/span[@class='gb_3']")
    public WebElement gmailAppButton;

    @FindBy(xpath = ".//*[@id='gb_70']")
    public WebElement enterGoogleAccButton;

    @FindBy(xpath = ".//*[@id='gbw']//a[@class='gb_b gb_8a gb_R']")
    public WebElement userAccLink;

    @FindBy(xpath = ".//*[@id='gbw']//div[@class='gb_qb']")
    public WebElement userAccEmail;

    public GoogleStartPageObject(WebDriver driver) {
        super(driver);
    }


    public void clickOnGoogleAppsButton(){
        $(googleAppsButton).click();
    }

    public void clickOnGmailAppButton(){
        $(gmailAppButton).click();
    }

    public GmailBoardPageObject openGmail(){
        clickOnGoogleAppsButton();
        clickOnGmailAppButton();
        return new GmailBoardPageObject(driver);
    }

    public SingInPagePageObject clickOnEnterToGoogleAccButton(){
        $(enterGoogleAccButton).shouldBe(Condition.visible).click();
        return new SingInPagePageObject(driver);
    }

    public void clickOnUserAccLink(){
        $(userAccLink).shouldBe(Condition.visible).click();
    }

    public boolean checkUserAccEmail(String email){
        clickOnUserAccLink();
        try {
            if ($(userAccEmail).getText().equals(email)) {

                return true;
            }else return false;

        }catch (Exception e){
            System.out.println("Email is not equals with entered data");
        }
        return false;
    }

    public List<String> getFieldsErrors() {
        return null;
    }
}
