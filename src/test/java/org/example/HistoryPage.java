package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;
import java.util.List;

public class HistoryPage {


    public WebDriver driver;

    public HistoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    int time = 550;

//    @FindBy(xpath = "//*[contains(@id, 'navbarDropdownMenuLink-4')]")
//    private WebElement logoutMenu1;

    public void getPagAndCountElements() throws InterruptedException {
        WebElement endImage = driver.findElement(By.xpath("//div/section/nav/ul/li[last()-1]"));

        int historySize = driver.findElements(By.xpath("//*[contains(@class, 'card z-depth-1 mb-3')]")).size();
        int countPage = Integer.parseInt(endImage.getText());
        int sum = 0;
        StringBuilder textFile = new StringBuilder();
        for (int i = 1; i < countPage; i++){
            textFile.append(i).append(") кол-во элементов на странице = ").append(historySize).append("\n");
            sum += historySize;
        }

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", endImage);
        Thread.sleep(time);
        endImage.click();
        historySize = driver.findElements(By.xpath("//*[contains(@class, 'card z-depth-1 mb-3')]")).size();
        textFile.append(countPage).append(") кол-во элементов на странице = ").append(historySize).append("\n");
        sum += historySize;
        textFile.append("Общее количество заказов = ").append(sum);
        TestTools.writeFile(textFile.toString(), "task2_2.txt");

        Thread.sleep(time);
    }

    public void getPagAndCountElements2() throws InterruptedException {
        WebElement endImage = driver.findElement(By.xpath("//div/section/nav/ul/li[last()-1]"));
        WebElement strImage;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int historySize = 0;
        int countPage = Integer.parseInt(endImage.getText());
        int sum = 0;
        StringBuilder textFile = new StringBuilder();
        for (int i = 1; i < countPage + 1; i++){
            historySize = driver.findElements(By.xpath("//*[contains(@class, 'card z-depth-1 mb-3')]")).size();
            textFile.append(i).append(") кол-во элементов на странице = ").append(historySize).append("\n");
            sum += historySize;

            strImage = driver.findElement(By.xpath("//div/section/nav/ul/li[last()]"));
            js.executeScript("arguments[0].scrollIntoView();", strImage);
            Thread.sleep(time);
            strImage.click();
        }

        textFile.append("Общее количество заказов = ").append(sum);
        TestTools.writeFile(textFile.toString());

        Thread.sleep(time);
    }
}
