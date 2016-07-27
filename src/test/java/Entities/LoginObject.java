package Entities;

/**
 * Created by DeBeers on 24.07.2016.
 */
public class LoginObject {
    private final String Login;
    private final String Password;

    public LoginObject(String Login, String Password) {
        this.Login = Login;
        this.Password = Password;
    }

    public String getLogin() {

        return Login;
    }

    public String getPassword() {

        return Password;
    }
}
