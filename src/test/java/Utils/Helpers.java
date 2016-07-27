package Utils;

import static PageObjects.BaseTest.driver;

/**
 * Created by DeBeers on 26.07.2016.
 */
public class Helpers {

    public static void GmailRefresh() throws InterruptedException {
        driver.navigate().refresh();
        Thread.sleep(5000);
    }
}
