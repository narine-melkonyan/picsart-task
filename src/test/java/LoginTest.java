import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {


    @Test(retryAnalyzer = Retry.class)
    public void verifyUrlWhenLogOut() {

        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.clickOnLogo();
        String currentpageurl = homePage.getCurrentPageUrl();
        Assert.assertEquals(currentpageurl, BASE_URL + "/");

    }

    // This test runs only with "mvn test"
    @Test(retryAnalyzer = Retry.class)
    @Parameters({"username", "password"})
    public void verifyUrlWhenLogIn(String username, String password) {

        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.applyLogin(username, password);
        homePage.myProfile();
        homePage.clickOnLogo();
        String currentPageUrl = homePage.getCurrentPageUrl();
        Assert.assertEquals(currentPageUrl, BASE_URL + "/create");

    }

    @Test(retryAnalyzer = Retry.class)
    public void verifyBlogPageUrl() {

        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        Assert.assertEquals(homePage.getCurrentPageUrl(), BASE_URL + "/blog");

    }

    @Test(retryAnalyzer = Retry.class)
    public void verifyBlogCategoriesUrl() {
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
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
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        homePage.searchInput("test");
        homePage.waitForPageUrlContains("test");
        Assert.assertTrue(homePage.getCurrentPageUrl().contains("test"));
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
        Assert.assertEquals(activeElementIndexFromSlideList2, activeElementIndexFromSlideList + 1);
        homePage.clickLeftSlideListButton();
        int activeElementIndexFromSlideList3 = homePage.findActiveElementIndexFromSlideList();
        Assert.assertEquals(activeElementIndexFromSlideList3, activeElementIndexFromSlideList);
    }

    @Test(retryAnalyzer = Retry.class)
    public void verifySlideListItemUrl() {
        logger.info("verifySlideListItemUrl");
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        WebElement activeElement = homePage.findActiveElementFromSlideList();
        Assert.assertNotNull(activeElement);
        WebElement link = activeElement.findElement(By.cssSelector("[class^=main-carousel-item-titleLink]"));
        String href = link.getAttribute("href");
        link.click();
        homePage.waitForPageUrlContains(href);
        Assert.assertEquals(homePage.getCurrentPageUrl(), href);

    }

    @Test(retryAnalyzer = Retry.class)
    public void verifySlideListItemHolder() {
        logger.info("verifySlideListItemHolder");
        HomePage homePage = new HomePage(driver);
        homePage.acceptAllCookie();
        homePage.goToBlog();
        WebElement someItem = homePage.findSlideListItems().get(3);
        String suggestionImageSrc = someItem.findElement(By.cssSelector("img")).getAttribute("src");
        someItem.click();
        WebElement activeElement = homePage.findActiveElementFromSlideList();
        String activeImageSrc = activeElement.findElement(By.cssSelector("img")).getAttribute("src");
        String suggestionUrl = homePage.getSlideImageUrlPath(suggestionImageSrc);
        Assert.assertNotNull(suggestionUrl);
        String activeImageUrl = homePage.getSlideImageUrlPath(activeImageSrc);
        Assert.assertNotNull(activeImageUrl);
        Assert.assertEquals(activeImageUrl, suggestionUrl);
    }


}
