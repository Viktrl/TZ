import Helpers.CSVFileMaker;
import Helpers.Base;
import Pages.CustomerAccountPage;
import Pages.CustomerChoicePage;
import Pages.HomePage;
import Pages.TransactionsPage;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankAppTest extends Base {

    @BeforeAll
    public void setup() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(gridUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterAll
    public void exit() {
        driver.quit();
    }

    @Test
    @Order(1)
    @Step
    @Description("Открываем страницу выбора клиента")
    public void openCustomerChoicePage() {
        HomePage homePage = new HomePage(driver);
        homePage.customerLoginButton();
    }

    @Test
    @Order(2)
    @Step
    @Description("Авторизуемся в клиента")
    public void loginIntoCustomer() {
        CustomerChoicePage customerChoicePage = new CustomerChoicePage(driver);
        customerChoicePage.customerSelect();
        customerChoicePage.loginInCustomer();
    }

    @Test
    @Order(3)
    @Step
    @Description("Осуществляем операции с деньгами и открываем страницу транзакций")
    public void operationsWithMoney() {
        CustomerAccountPage customerAccountPage = new CustomerAccountPage(driver);
        customerAccountPage.makeDeposit();
        customerAccountPage.makeWithdraw();
        customerAccountPage.checkThatBalanceOfAccountIsZero("0");
        customerAccountPage.openTransactionsPage();
    }

    @Test
    @Order(4)
    @Step
    @Description("Проверяем что транзакции отражаются и выгружаем их в csv файл")
    public void checkTransactions() {
        TransactionsPage transactionsPage = new TransactionsPage(driver);
        transactionsPage.checkTransactionsHasItself();

        CSVFileMaker csvFileMaker = new CSVFileMaker(filePathForMakeCSV, transactionsPage.getDateTimeOfTransaction1().getText(),
                transactionsPage.getAmountOfTransaction1().getText(), transactionsPage.getTypeOfTransaction1().getText(),
                transactionsPage.getDateTimeOfTransaction2().getText(), transactionsPage.getAmountOfTransaction2().getText(),
                transactionsPage.getTypeOfTransaction2().getText());
        csvFileMaker.initDataForGenerateCSV();
    }
}