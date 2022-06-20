import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class UrlTest extends TestBase {

    @Test(retryAnalyzer = Retry.class)
    public void verifyUrlWhenLogOut() {
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
        loginPage.clickOnLogo();
        String currentpageurl = homePage.getCurrentPageUrl();
        Assert.assertEquals(currentpageurl, BASE_URL + "/");
    }

    // This test runs only with "mvn test"
    @Test(retryAnalyzer = Retry.class)
    @Parameters({"username", "password"})
    public void verifyUrlWhenLogIn(String username, String password) {
        logger.info("verifyUrlWhenLogIn");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
        loginPage.applyLogin(username, password);
        loginPage.myProfile();
        loginPage.clickOnLogo();
        String currentPageUrl = homePage.getCurrentPageUrl();
        Assert.assertEquals(currentPageUrl, BASE_URL + "/create");
    }

    @Test(retryAnalyzer = Retry.class)
    public void verifyBlogPageUrl() {
        logger.info("verifyBlogPageUrl");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
        homePage.goToBlog();
        Assert.assertEquals(homePage.getCurrentPageUrl(), BASE_URL + "/blog");
    }

    @Test(retryAnalyzer = Retry.class)
    public void verifyBlogCategoriesUrl() {
        logger.info("verifyBlogCategoriesUrl");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
        homePage.goToBlog();
        homePage.goToDesignSchool();
        homePage.waitForPageUrlContains("design-school");
        Assert.assertEquals(homePage.getCurrentPageUrl(), BASE_URL + "/blog/category/design-school");
        homePage.goToTrends();
        homePage.waitForPageUrlContains("trends");
        Assert.assertEquals(homePage.getCurrentPageUrl(), BASE_URL + "/blog/category/trends");
        homePage.goToPicsartPro();
        homePage.waitForPageUrlContains("picsart-pro");
        Assert.assertEquals(homePage.getCurrentPageUrl(), BASE_URL + "/blog/category/picsart-pro");
        homePage.goToNews();
        homePage.waitForPageUrlContains("news");
        Assert.assertEquals(homePage.getCurrentPageUrl(), BASE_URL + "/blog/category/news");
    }

    @Test(retryAnalyzer = Retry.class)
    public void verifySearchResult() {
        logger.info("verifySearchResult");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
        homePage.goToBlog();
        homePage.searchInput("summer");
        homePage.waitForPageUrlContains("summer");
        Assert.assertNotNull(loginPage.waitForElementPresent1("//div[contains(@class,'layout-content')]"));
        Assert.assertTrue(homePage.getCurrentPageUrl().contains("summer"));
    }
}
