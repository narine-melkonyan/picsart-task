import io.github.bonigarcia.wdm.config.DriverManagerType;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public WebDriver driver;

    protected static final Logger logger = LogManager.getLogger();

    @BeforeClass
    public void setup() {

        ChromeDriverManager.getInstance(DriverManagerType.CHROME).setup();
        driver = new ChromeDriver();
        driver.get("https://picsart.com/");
        driver.manage().window().maximize();
        logger.info("Setup");
    }


    @AfterClass
    public void tearDown() {

        driver.quit();
        logger.info("Driver quit");

    }


}
