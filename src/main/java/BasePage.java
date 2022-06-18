import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.NoSuchElementException;

import java.util.function.Function;

public class BasePage {

    public WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver,this);
    }

    public boolean isElementPresent(final String elementxpath) {
        return new WebDriverWait(driver, java.time.Duration.ofSeconds(10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.findElement(By.xpath(elementxpath)) != null;
            }
        });
    }

    public WebElement waitForElementPresent1(final String elementXPath) {
        return waitForElementPresent1(By.xpath(elementXPath));
    }

    public WebElement waitForElementPresent1(final By selector) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
    }

    public boolean waitForPageUrlContains(String s) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        return wait.until(ExpectedConditions.urlContains(s));
    }


    public WebElement waitForElementPresent(String elementxpath) {

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(java.time.Duration.ofSeconds(10))
                .pollingEvery(java.time.Duration.ofMillis(1000))
                .ignoring(NoSuchElementException.class);

        WebElement existelement = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath(elementxpath));
            }
        });
        return existelement;

    }

}
