import com.opencsv.CSVWriter;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TransactionsPage {
    private WebDriver driver;

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

    public TransactionsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkTransactionsHasItself() {
        //dateTimeStart.click();
        //dateTimeStart.sendKeys("0");
        Assertions.assertEquals("Credit", typeOfTransaction1.getText());
        Assertions.assertEquals("Debit", typeOfTransaction2.getText());
    }

    public void initDataForGenerateCSV() {
        String filePath = "c:\\test\\test.csv";

        String[] header = {"Дата-времяТранзакции", "Сумма", "ТипТранзакции"};
        String[] record1 = {dateTimeOfTransaction1.getText(), amountOfTransaction1.getText(), typeOfTransaction1.getText()};
        String[] record2 = {dateTimeOfTransaction2.getText(), amountOfTransaction2.getText(), typeOfTransaction2.getText()};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);

        try (
                CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(list);
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }

        attachCSV(filePath);
    }

    @Attachment(value = "test", type = "text/csv", fileExtension = ".csv")
    static byte[] attachCSV(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
