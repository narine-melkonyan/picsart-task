import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SliderTest extends TestBase {

    @Test(retryAnalyzer = Retry.class)
    public void verifySlideList() {
        logger.info("verifySlideList");
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
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
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
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
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        loginPage.acceptAllCookie();
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
