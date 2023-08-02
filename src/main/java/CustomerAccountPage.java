import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;


public class CustomerAccountPage {
    private WebDriver driver;

    @FindBy(xpath = "//div/div/div[2]/div/div[3]/button[1]")
    private WebElement transactionsButton;
    @FindBy(xpath = "//div/div/div[2]/div/div[3]/button[2]")
    private WebElement depositButton;
    @FindBy(xpath = "//div/div/div[2]/div/div[3]/button[3]")
    private WebElement withdrawButton;
    @FindBy(css = "form[role = 'form']")
    private WebElement form;
    @FindBy(css = "input[type = 'number']")
    private WebElement amountField;
    @FindBy(xpath = "//div/div/div[2]/div/div[4]/div/form/button")
    private WebElement depositWithdrawButton;
    @FindBy(xpath = "//div/div/div[2]/div/div[2]/strong[2]")
    private WebElement balanceAmount;

    public CustomerAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    LocalDate localDate = LocalDate.now();
    int day = localDate.getDayOfMonth();

    int fibonacci(int n) {
        if (n <= 1) {
            return n;
        }
        else {
            return fibonacci(n-1) + fibonacci(n-2);
        }
    }

    String fibonacciString = String.valueOf(fibonacci(day + 1));

    public void makeDeposit() {
        depositButton.click();
        Boolean firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(form,"ng-submit","deposit()"));
        if (firstResult) {
            amountField.sendKeys(fibonacciString);
            System.out.println("deposit amount has sent");
        } else System.out.println("deposit ng-submit != deposit()");
        depositWithdrawButton.click();
    }

    public void makeWithdraw() {
        withdrawButton.click();
        Boolean firstResult = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(form,"ng-submit","withdrawl()"));
        if (firstResult) {
            amountField.sendKeys(fibonacciString);
            System.out.println("withdraw amount has sent");
        } else System.out.println("withdrawl ng-submit != withdrawl()");
        depositWithdrawButton.click();
    }

    public void checkBalance() {
        balanceAmount.getText().toString().contains("0");
        System.out.println(balanceAmount.getText());
    }

    public void openTransactionsPage() {
        transactionsButton.click();
    }
}
