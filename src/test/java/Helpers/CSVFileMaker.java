package Helpers;

import com.opencsv.CSVWriter;
import io.qameta.allure.Attachment;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CSVFileMaker {
    private final String dateTimeOfTransaction1;
    private final String amountOfTransaction1;
    private final String typeOfTransaction1;
    private final String dateTimeOfTransaction2;
    private final String amountOfTransaction2;
    private final String typeOfTransaction2;
    private final String filePath;

    public CSVFileMaker(String dateTimeOfTransaction1, String amountOfTransaction1,
                        String typeOfTransaction1, String dateTimeOfTransaction2, String amountOfTransaction2,
                        String typeOfTransaction2, String filePath) {
        this.dateTimeOfTransaction1 = dateTimeOfTransaction1;
        this.amountOfTransaction1 = amountOfTransaction1;
        this.typeOfTransaction1 = typeOfTransaction1;
        this.dateTimeOfTransaction2 = dateTimeOfTransaction2;
        this.amountOfTransaction2 = amountOfTransaction2;
        this.typeOfTransaction2 = typeOfTransaction2;
        this.filePath = filePath;
    }

    public void initDataForGenerateCSV() {
        String[] header = {"Дата-времяТранзакции", "Сумма", "ТипТранзакции"};
        String[] record1 = {dateTimeOfTransaction1, amountOfTransaction1, typeOfTransaction1};
        String[] record2 = {dateTimeOfTransaction2, amountOfTransaction2, typeOfTransaction2};

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