import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test
    public void verifyUrlWhenLogOut() {

        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.clickOnLogo();
        String currentpageurl = homePage.getCurrentPageUrl();
        System.out.println(currentpageurl);
        Assert.assertEquals(currentpageurl, "https://picsart.com/");

    }

    @Test
    public void verifyUrlWhenLogIn() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.applyLogin("narinebettest", "_f8RyXePcdB.@uT");
        homePage.myProfile();
        homePage.clickOnLogo();
        String currentpageurl = homePage.getCurrentPageUrl();
        System.out.println(currentpageurl);
        Assert.assertEquals(currentpageurl, "https://picsart.com/create");

    }

    @Test
    public void verifyBlogPageUrl() {

        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        Assert.assertEquals(homePage.getCurrentPageUrl(), "https://picsart.com/blog");

    }

    @Test
    public void verifyBlogCategorisUrl() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        homePage.goToDesignSchool();
        homePage.waitForPageUrlContains("design-school");
        Assert.assertEquals(homePage.getCurrentPageUrl(), "https://picsart.com/blog/category/design-school");
        homePage.goToTrends();
        homePage.waitForPageUrlContains("trends");
        Assert.assertEquals(homePage.getCurrentPageUrl(), "https://picsart.com/blog/category/trends");
        homePage.goToPicsartPro();
        homePage.waitForPageUrlContains("picsart-pro");
        Assert.assertEquals(homePage.getCurrentPageUrl(), "https://picsart.com/blog/category/picsart-pro");
        homePage.goToNews();
        homePage.waitForPageUrlContains("news");
        Assert.assertEquals(homePage.getCurrentPageUrl(), "https://picsart.com/blog/category/news");


    }

    @Test
    public void verifySearchResult() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        homePage.searchInput("test");
        homePage.waitForPageUrlContains("test");
        Assert.assertEquals(homePage.getCurrentPageUrl().contains("test"), true);

    }


    @Test(retryAnalyzer = Retry.class)
    public void verifySlideList() {
        logger.info("verifySlideList");
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        int activeElementIndexFromSlideList = homePage.findActiveElementIndexFromSlideList();
        Assert.assertNotEquals(activeElementIndexFromSlideList, -1);
        homePage.clickRightSlideListButton();
        int activeElementIndexFromSlideList2 = homePage.findActiveElementIndexFromSlideList();
        Assert.assertEquals(activeElementIndexFromSlideList2, activeElementIndexFromSlideList + 2);

    }



}
