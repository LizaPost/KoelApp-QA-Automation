import io.cucumber.java.After;

public class ResetHelper {
    @After("@resetPassword")
    public void resetPassword() {
        PasswordAndEmailResetter.resetPassword();
    }
    @After("@resetEmail")
    public void resetEmail() {
        PasswordAndEmailResetter.resetEmail();
    }
    @After("@resetEmailAndPassword")
    public void resetEmailAndPassword() {
        PasswordAndEmailResetter.resetEmailAndPassword();
    }
}
