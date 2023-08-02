import org.junit.jupiter.api.*;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BankAppTest {
    WebDriver driver;

    @BeforeEach
    public void setup() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        //capabilities.setPlatform(Platform.WIN10);
        driver = new RemoteWebDriver(new URL("http://192.168.56.1:4444"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        transactionsPage.initDataForGenerateCSV();
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }
}
