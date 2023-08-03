import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BankAppTest {
    WebDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(BrowserFactory), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void allTest() {
        HomePage homePage = new HomePage(driver);
        homePage.clickButton();

        CustomerChoicePage customerChoicePage = new CustomerChoicePage(driver);
        customerChoicePage.userSelect();
        customerChoicePage.loginButton();

        CustomerAccountPage customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.makeDeposit();
        customerAccountPage.makeWithdraw();
        customerAccountPage.checkBalance();
        customerAccountPage.openTransactionsPage();

        TransactionsPage transactionsPage = new TransactionsPage(driver);
        transactionsPage.checkTransactionsHasItself();

        CSVFileMaker csvFileMaker = new CSVFileMaker(transactionsPage);
        csvFileMaker.initDataForGenerateCSV();
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }
}