package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    private WebDriver driver;
    private static String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";

    @FindBy(xpath = "//div/div/div[2]/div/div[1]/div[1]/button")
    private WebElement customerLogin;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public void clickButton() {
        customerLogin.click();
    }
}
