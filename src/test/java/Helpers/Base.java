package Helpers;

import org.openqa.selenium.WebDriver;

import java.time.LocalDate;

public class Base {
    protected WebDriver driver;

    public String url = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login";
    public String gridUrl = "http://localhost:4445";
    public String filePathForMakeCSV = "test.csv";

    public LocalDate localDate = LocalDate.now();
    protected int day = localDate.getDayOfMonth() + 1;
}