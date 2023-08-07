package Pages;

import Helpers.Base;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TransactionsPage extends Base {
    @FindBy(xpath = "//div/div/div[2]/div/div[1]/button[1]")
    private WebElement backButton;
    @FindBy(xpath = "//*[@id=\"start\"]")
    private WebElement dateTimeStart;

    @FindBy(xpath = "//*[@id=\"anchor0\"]/td[1]")
    private WebElement dateTimeOfTransaction1;
    @FindBy(xpath = "//*[@id=\"anchor0\"]/td[2]")
    private WebElement amountOfTransaction1;
    @FindBy(xpath = "//*[@id=\"anchor0\"]/td[3]")
    private WebElement typeOfTransaction1;
    @FindBy(xpath = "//*[@id=\"anchor1\"]/td[1]")
    private WebElement dateTimeOfTransaction2;
    @FindBy(xpath = "//*[@id=\"anchor1\"]/td[2]")
    private WebElement amountOfTransaction2;
    @FindBy(xpath = "//*[@id=\"anchor1\"]/td[3]")
    private WebElement typeOfTransaction2;

    public WebElement getDateTimeOfTransaction1() {
        return dateTimeOfTransaction1;
    }

    public WebElement getAmountOfTransaction1() {
        return amountOfTransaction1;
    }

    public WebElement getTypeOfTransaction1() {
        return typeOfTransaction1;
    }

    public WebElement getDateTimeOfTransaction2() {
        return dateTimeOfTransaction2;
    }

    public WebElement getAmountOfTransaction2() {
        return amountOfTransaction2;
    }

    public WebElement getTypeOfTransaction2() {
        return typeOfTransaction2;
    }

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkTransactionsHasItself() {
        Assertions.assertEquals("Credit", typeOfTransaction1.getText());
        Assertions.assertEquals("Debit", typeOfTransaction2.getText());
    }
}