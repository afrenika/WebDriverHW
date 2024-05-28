package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class FinRequestPage {

    public WebDriver driver;
    public FinRequestPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[contains(@class, 'btn btn-primary btn-lg BtnCabinet ShowBtn')]")
    public WebElement requestButton;

    @FindBy(xpath = "//*[contains(@id, 'Amount')]")
    public WebElement amountTF;

    @FindBy(xpath = "//*[contains(@id, 'FinOrdersOperTypes')]")
    public WebElement operationType;

    @FindBy(xpath = "//*[@id=\"Comment\"]")
    public WebElement comment;

    @FindBy(xpath = "//*[@id=\"Inn\"]")
    public WebElement inn;

    @FindBy(xpath = "//*[@id=\"Kpp\"]")
    public WebElement kpp;

    @FindBy(xpath = "//*[@id=\"Bik\"]")
    public WebElement bik;

    @FindBy(xpath = "//*[@id=\"Bank\"]")
    public WebElement bank;

    @FindBy(xpath = "//*[@id=\"Rs\"]")
    public WebElement rs;

    @FindBy(xpath = "//*[@id=\"Ks\"]")
    public WebElement ks;

    @FindBy(xpath = "//*[contains(@class, 'w-100 btn btn-primary BtnCabinet ModalButtonColor')]")
    public WebElement okRequestButton;

    @FindBy(xpath = "//*[contains(@class, 'w-100 btn btn-secondary BtnCabinet ModalButtonColor')]")
    public WebElement cancelRequestButton;

    public void setRequestInfo() throws InterruptedException {
        requestButton.click();

        Random random = new Random();
        amountTF.sendKeys(String.valueOf(random.nextInt(10000)+10));

        operationType.click();
        Select select = new Select(operationType);
        select.selectByIndex(random.nextInt(3));

        comment.sendKeys("testComment");

        inn.sendKeys("1111111111");
        kpp.sendKeys("111111111");
        bik.sendKeys("043601607");
        bank.sendKeys("Сбербанк");
        rs.sendKeys("11111111111111111111");
        ks.sendKeys("11111111111111111111");

        Thread.sleep(300);
        okRequestButton.click();
        Thread.sleep(300);


    }

    public void setRequestInfo(String sum, int type) throws InterruptedException {
        requestButton.click();
        Thread.sleep(300);
        amountTF.clear();
        amountTF.sendKeys(String.valueOf(sum));

        operationType.click();
        Select select = new Select(operationType);
        select.selectByIndex(type - 1);

        Thread.sleep(300);
        okRequestButton.click();
        Thread.sleep(300);


    }

    public void searchRequest() throws InterruptedException {
        WebElement iframe = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[1]"));
                new Actions(driver)
                            .scrollToElement(iframe)
                            .perform();
                Thread.sleep(600);
    }

    public void scrollRequests() throws InterruptedException {
        WebElement iframe = driver.findElement(By.xpath("//*[@id=\"wrapper\"]/div[1]/section/div/div[3]/section/div[last()]"));
//        new Actions(driver)
//                    .scrollToElement(iframe)
//                    .perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", iframe);
        Thread.sleep(900);

    }




}
