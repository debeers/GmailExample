package PageObjects;


import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import Entities.LoginObject;
import Tests.WebDriverFactory;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static Actions.SignInActions.signOut;

public class BaseTest extends WebDriverFactory {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static String baseUrl;
    public static StringBuffer verificationErrors = new StringBuffer();
    public static LoginObject sender;

    @Parameters({"URL", "senderLogin", "senderPassword"})
    @BeforeTest(alwaysRun = true)
    public void setup(String URL, String senderLogin, String senderPassword)
            throws MalformedURLException {

        sender = new LoginObject(senderLogin, senderPassword);
        baseUrl = URL;

        FirefoxProfile fProfile = new FirefoxProfile();
        fProfile.setAcceptUntrustedCertificates(true);

        DesiredCapabilities dc = DesiredCapabilities.firefox();

        dc.setJavascriptEnabled(true);
        dc.setCapability(FirefoxDriver.PROFILE, fProfile);

        driver = WebDriverFactory.getDriver(dc);
        driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 3);
        driver.manage().window().maximize();
        WebDriverRunner.setWebDriver(driver);

        driver.get(baseUrl);

    }


    @AfterTest
    public void tearDown() throws Exception {

        signOut();
        driver.quit();

        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }


}

