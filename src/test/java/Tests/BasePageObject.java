package Tests;

import PageObjects.BasePageObjectInterface;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by DeBeers on 23.07.2016.
 */
public abstract class BasePageObject implements BasePageObjectInterface {

    protected static WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BasePageObject(WebDriver driver) {
        this.driver = driver;
        this.init(driver);
    }

    protected void init(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
