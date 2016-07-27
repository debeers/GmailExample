package PageObjects;

import Tests.BasePageObject;
import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by DeBeers on 23.07.2016.
 */
public class SingInPagePageObject extends BasePageObject implements ErrorHandlerInterface{

    @FindBy(xpath = ".//*[@id='Email']")
    public WebElement emailField;

    @FindBy(xpath = ".//*[@id='errormsg_0_Email']")
    public WebElement emailErrorMessage;

    @FindBy(xpath = ".//*[@id='next']")
    public WebElement nextButton;

    @FindBy(xpath = ".//*[@id='Passwd']")
    public WebElement passwordField;

    @FindBy(xpath = ".//*[@id='email-display']")
    public WebElement emailDisplay;

    @FindBy(xpath = ".//*[@id='errormsg_0_Passwd']")
    public WebElement passwordErrorMessage;

    @FindBy(xpath = ".//*[@id='signIn']")
    public WebElement signInButton;


    public SingInPagePageObject(WebDriver driver) {
        super(driver);
    }


    public String enterEmail(String email){
        $(emailField).shouldBe(Condition.visible).sendKeys(email);
        return emailField.getText();
    }

    public SingInPagePageObject enterEmailAndClickNextButton(String email){
        enterEmail(email);
        return clickOnNextButton();
    }

    public SingInPagePageObject clickOnNextButton(){
        $(nextButton).shouldBe(Condition.visible).click();

            if (getFieldsErrors().isEmpty()){
                return new SingInPagePageObject(driver);
            } else {
                System.out.println(
                        "Oops, some errors were detected in entering data and you take a null pointer)");
                return null;
            }
    }

    public void enterPassword(String password){
        $(passwordField).shouldBe(Condition.visible).sendKeys(password);
    }

    public GoogleStartPageObject clickOnSingInButton(){
        $(signInButton).shouldBe(Condition.visible).click();
        return new GoogleStartPageObject(driver);
    }

    public GoogleStartPageObject enterPasswordAndClickOnSignInButton(String password){
        enterPassword(password);
        return clickOnSingInButton();
    }


    @Override
    public List<String> getFieldsErrors() {
        List<String> errorsList = new ArrayList<>();
        try {
            String error = emailErrorMessage.getText();
            if (!error.isEmpty()) {
                    errorsList.add(error);
                    System.out.println("ERRORS EXIST:: " + error);
                return errorsList;
            }

        } catch (Exception e) {
            System.out.println("Everything looks fine, no error messages was found in entering data");
        }
        return errorsList;
    }

}
