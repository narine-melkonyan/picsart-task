import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public static final String acceptCookieElementXpath = "//button[text()='Accept All Cookies']";

    public void acceptAllCookie() {
        waitForElementPresent1(acceptCookieElementXpath).click();
    }

    public static final String logoXpath = "//a[@data-test='picsart-logo']";

    public void clickOnLogo() {
        waitForElementPresent1(logoXpath).click();
    }

    public static final String loginXpath = "//button[@data-mode='login']";

    public void logIn() {
        waitForElementPresent1(loginXpath).click();
    }


    public static final String userNameXpath = "//input[@name='username']";

    public void setUsername(String userName) {
        waitForElementPresent1(userNameXpath).sendKeys(userName);
    }

    public static final String passwordXpath = "//input[@name='password']";

    public void setPassword(String pass) {
        waitForElementPresent1(passwordXpath).sendKeys(pass);
    }

    public static final String applyLoginXpath = "//button[@data-test='login']";

    public void applyLogin(String user, String pass) {
        logIn();
        setUsername(user);
        setPassword(pass);
        waitForElementPresent(applyLoginXpath).click();
    }

    public static final String profileXpath = "//span[contains(@class,'rc-header-dev-profile-button')]";

    public void myProfile() {
        waitForElementPresent(profileXpath).click();
    }

    public static final String logoutXpath = "//a[contains(@class,'log-out')]";

    public void logOut() {
        waitForElementPresent1(logoutXpath).click();
    }


}
