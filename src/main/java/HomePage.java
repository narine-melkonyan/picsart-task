import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }


    public static final String blogXpath = "//a[text()='Blog']";
    public static final String designSchoolXpath = "//a[text()='Design School']";
    public static final String trendsXpath = "//a[text()='Trends']";
    public static final String picsartProXpath = "//a[text()='Picsart Pro']";
    public static final String newsXpath = "//a[text()='News']";

    public void goToBlog() {
        waitForElementPresent1(blogXpath).click();
    }

    public void goToDesignSchool() {
        waitForElementPresent1(designSchoolXpath).click();
    }

    public void goToTrends() {
        waitForElementPresent1(trendsXpath).click();
    }
    public void goToPicsartPro() {
        waitForElementPresent1(picsartProXpath).click();
    }
    public void goToNews() {
        waitForElementPresent1(newsXpath).click();
    }


    public static final String searchButtonXpath = "//button[contains(@class,'searchButton')]";
    public void clickSearchButton() {
        waitForElementPresent1(searchButtonXpath).click();
    }

    public static final String searchInputXpath = "//input[contains(@class,'searchInput')]";
    public void searchInput(String s) {
        clickSearchButton();
        WebElement element = waitForElementPresent1(searchInputXpath);
        element.sendKeys(s);
        element.submit();
    }


    public static final String slideListElementsXpath = "//*[contains(@class,'main-carousel-item-root')]";

    public int findActiveElementIndexFromSlideList() {
        waitForSlideList();
        List<WebElement> slideListImgs = driver.findElements(By.xpath(slideListElementsXpath));
        for (int i = 0; i < slideListImgs.size(); i++) {
            if (Arrays.asList(slideListImgs.get(i).getDomAttribute("class").split(" ")).contains("active")) {
                return i;
            }
        }
        return -1;
    }

    public WebElement findActiveElementFromSlideList() {
        waitForSlideList();
        List<WebElement> slideListImgs = driver.findElements(By.xpath(slideListElementsXpath));
        for (int i = 0; i < slideListImgs.size(); i++) {
            WebElement webElement = slideListImgs.get(i);
            if (Arrays.asList(webElement.getDomAttribute("class").split(" ")).contains("active")) {
                return webElement;
            }
        }
        return null;
    }

    public static final String slideListItemsXpath = "//*[contains(@class,'main-carousel-suggestions-itemHolder')]";

    public List<WebElement> findSlideListItems() {
        waitForSlideList();
        List<WebElement> slideListItems = driver.findElements(By.xpath(slideListItemsXpath));
        return slideListItems;
    }


    public void clickRightSlideListButton() {
        driver.findElement(By.xpath("//button[contains(@class,'right arrow-button')]")).click();
    }

    public void clickLeftSlideListButton() {
        driver.findElement(By.xpath("//button[contains(@class,'left arrow-button')]")).click();
    }

    public void waitForSlideList() {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        wait.until((driver) -> {
            List<WebElement> slideListImgs = driver.findElements(By.xpath(slideListElementsXpath));
            return slideListImgs.size() == 9;
        });
    }

    public String getSlideImageUrlPath(String imageSrc) {
        String query;
        try {
            URL url = new URL(imageSrc);
            query = url.getQuery();

        } catch (MalformedURLException e) {
            return null;
        }

        try {
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                String name = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
                if (name.equals("url")) {
                    String imageUrl = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
                    try {
                        URL url = new URL(imageUrl);
                        return url.getPath();
                    } catch (MalformedURLException e) {
                        return null;
                    }

                }
            }
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return null;
    }

    public String getCurrentPageUrl() {

        return driver.getCurrentUrl();

    }

}
