package Pages;

import Helpers.Base;
import Helpers.FibonacciNumber;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CustomerAccountPage extends Base {
    @FindBy(xpath = "//div/div/div[2]/div/div[3]/button[1]")
    private WebElement buttonForOpenTransactionsPage;
    @FindBy(xpath = "//div/div/div[2]/div/div[3]/button[2]")
    private WebElement expandDepositForm;
    @FindBy(xpath = "//div/div/div[2]/div/div[3]/button[3]")
    private WebElement expandWithdrawForm;
    @FindBy(css = "form[role = 'form']")
    private WebElement form;
    @FindBy(css = "input[type = 'number']")
    private WebElement amountField;
    @FindBy(xpath = "//div/div/div[2]/div/div[4]/div/form/button")
    private WebElement makeOperationWithMoney;
    @FindBy(xpath = "//div/div/div[2]/div/div[2]/strong[2]")
    private WebElement accountBalance;

    public CustomerAccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    String fibonacciString = String.valueOf(FibonacciNumber.fibonacci(day));

    public void makeDeposit() {
        expandDepositForm.click();
        Boolean isDepositForm = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(form,"ng-submit","deposit()"));
        if (isDepositForm) {
            amountField.sendKeys(fibonacciString);
        }
        makeOperationWithMoney.click();
    }

    public void makeWithdraw() {
        expandWithdrawForm.click();
        Boolean isWithdrawForm = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.attributeToBe(form,"ng-submit","withdrawl()"));
        if (isWithdrawForm) {
            amountField.sendKeys(fibonacciString);
        }
        makeOperationWithMoney.click();
    }

    public void checkThatBalanceOfAccountIsZero(String expectedNumber) {
        Assertions.assertEquals(expectedNumber, accountBalance.getText());
    }

    public void openTransactionsPage() {
        buttonForOpenTransactionsPage.click();
    }
}