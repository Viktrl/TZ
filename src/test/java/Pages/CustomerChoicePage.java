package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CustomerChoicePage {
    private WebDriver driver;

    @FindBy(id = "userSelect")
    private WebElement userSelect;
    @FindBy(xpath = "//*[@id=\"userSelect\"]/option[3]")
    private WebElement userHarryPotter;
    @FindBy(xpath = "//div/div/div[2]/div/form/button")
    private WebElement loginButton;

    public CustomerChoicePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void userSelect() {
        userSelect.click();
        userHarryPotter.click();
    }

    public void loginButton() {
        loginButton.click();
    }
}