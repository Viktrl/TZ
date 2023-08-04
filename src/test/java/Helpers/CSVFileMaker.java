package Helpers;

import Pages.TransactionsPage;
import com.opencsv.CSVWriter;
import io.qameta.allure.Attachment;
import org.openqa.selenium.WebElement;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileMaker extends Urls {
    private final WebElement dateTimeOfTransaction1;
    private final WebElement amountOfTransaction1;
    private final WebElement typeOfTransaction1;
    private final WebElement dateTimeOfTransaction2;
    private final WebElement amountOfTransaction2;
    private final WebElement typeOfTransaction2;

    public CSVFileMaker(TransactionsPage transactionsPage) {
        this.dateTimeOfTransaction1 = transactionsPage.getDateTimeOfTransaction1();
        this.amountOfTransaction1 = transactionsPage.getAmountOfTransaction1();
        this.typeOfTransaction1 = transactionsPage.getTypeOfTransaction1();
        this.dateTimeOfTransaction2 = transactionsPage.getDateTimeOfTransaction2();
        this.amountOfTransaction2 = transactionsPage.getAmountOfTransaction2();
        this.typeOfTransaction2 = transactionsPage.getTypeOfTransaction2();
    }

    public void initDataForGenerateCSV() {
        String[] header = {"Дата-времяТранзакции", "Сумма", "ТипТранзакции"};
        String[] record1 = {dateTimeOfTransaction1.getText(), amountOfTransaction1.getText(), typeOfTransaction1.getText()};
        String[] record2 = {dateTimeOfTransaction2.getText(), amountOfTransaction2.getText(), typeOfTransaction2.getText()};

        List<String[]> list = new ArrayList<>();
        list.add(header);
        list.add(record1);
        list.add(record2);

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        attachCSV(filePath);
    }

    @Attachment(value = "test", type = "text/csv", fileExtension = ".csv")
    private static byte[] attachCSV(String filePath) {
        try {
            return Files.readAllBytes(Paths.get(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }
}
