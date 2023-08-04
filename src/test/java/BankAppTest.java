import Helpers.CSVFileMaker;
import Helpers.Urls;
import Pages.CustomerAccountPage;
import Pages.CustomerChoicePage;
import Pages.HomePage;
import Pages.TransactionsPage;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BankAppTest extends Urls {
    private WebDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
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