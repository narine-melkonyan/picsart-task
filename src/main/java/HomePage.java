import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    Actions action = new Actions(driver);

    public static final String acceptcookieelementxpath = "//button[text()='Accept All Cookies']";

    public void acceptAllCookie() {
        waitForElementPresent1(acceptcookieelementxpath).click();
    }

    public static final String logoxpath = "//a[@data-test='picsart-logo']";

    public void clickOnLogo() {

        waitForElementPresent1(logoxpath).click();

    }

    public static final String loginxpath = "//button[@data-mode='login']";

    public void logIn() {
        waitForElementPresent1(loginxpath).click();
    }


    public static final String usernamexpath = "//input[@name='username']";

    public void setUsername(String userName) {
        waitForElementPresent1(usernamexpath).sendKeys(userName);
    }

    public static final String passwordxpath = "//input[@name='password']";

    public void setPassword(String pass) {
        waitForElementPresent1(passwordxpath).sendKeys(pass);
    }

    public static final String applyloginxpath = "//button[@data-test='login']";

    public void applyLogin(String user, String pass) {
        logIn();
        setUsername(user);
        setPassword(pass);
        waitForElementPresent(applyloginxpath).click();
        System.out.println("loged in");
    }

    public static final String profilexpath = "//span[@class='rc-header-dev-profile-button-0-1-1093']";

    public void myProfile() {
        waitForElementPresent(profilexpath).click();
    }

    public boolean logedIn() {
        return waitForElementPresent1(profilexpath).isDisplayed();
    }


    public static final String logoutxpath = "//a[@class='rc-header-dev-top-profile-content-userLink-0-1-2107 log-out']";

    public void logOut() {
        waitForElementPresent1(logoutxpath).click();
    }


    public static final String blogxpath = "//a[text()='Blog']";
    public static final String designschoolxpath = "//a[text()='Design School']";
    public static final String trendsxpath = "//a[text()='Trends']";
    public static final String picsartproxpath = "//a[text()='Picsart Pro']";
    public static final String newsxpath = "//a[text()='News']";

    public void goToBlog() {
        waitForElementPresent1(blogxpath).click();
    }

    public void goToDesignSchool() {
        waitForElementPresent1(designschoolxpath).click();
    }

    public void goToTrends() {
        waitForElementPresent1(trendsxpath).click();
    }

    public void goToPicsartPro() {
        waitForElementPresent1(picsartproxpath).click();
    }

    public void goToNews() {
        waitForElementPresent1(newsxpath).click();
    }


    public static final String searchbuttonxpath = "//button[contains(@class,'searchButton')]";

    public void clickSearchButton() {
        waitForElementPresent1(searchbuttonxpath).click();
    }

    public static final String searchinputxpath = "//input[contains(@class,'searchInput')]";

    public void searchInput(String s) {
        clickSearchButton();
        WebElement element = waitForElementPresent1(searchinputxpath);
        element.sendKeys(s);
        element.submit();
    }


    public static final String slideListElementsSelector = ".main-carousel-item-root-0-2-34";


    public int findActiveElementIndexFromSlideList() {

        waitForSlideList();

        List<WebElement> slideListImgs = driver.findElements(By.cssSelector(slideListElementsSelector));
        for (int i = 0; i < slideListImgs.size(); i++) {
            if (Arrays.asList(slideListImgs.get(i).getDomAttribute("class").split(" ")).contains("active")) {
                return i;
            }
        }
        return -1;
    }


    public void clickRightSlideListButton() {

        driver.findElement(By.cssSelector(".main-carousel-root-0-2-30 .right.arrow-button")).click();

    }

    public void waitForSlideList() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        wait.until((driver) -> {
            List<WebElement> slideListImgs = driver.findElements(By.cssSelector(slideListElementsSelector));
            return slideListImgs.size() == 9;
        });
    }

    public String getCurrentPageUrl() {

        return driver.getCurrentUrl();

    }


//    public void logOut(){
//
//        boolean isuserlogout = isElementPresent(userprofile);
//        if(isuserlogout == true){
//            action.moveToElement(userprofile);
//            waitForElementPresent(userlogout).click();
//        }
//
//    }


}
