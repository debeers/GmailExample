package Data;


import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="positiveLoginData", parallel = true)
    public static Object[][] positiveLoginData(){
        return new Object[][]{
                {"DeBeers1989@gmail.com", "TestMessage_777", "Hello automation QA`s )",
                        System.getProperty("user.dir")+"\\src\\test\\java\\resources\\message.txt"}

        };
    }




}
