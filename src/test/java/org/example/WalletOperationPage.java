package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WalletOperationPage {

    public WebDriver driver;
    public WalletOperationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[contains(@id, 'date_from')]")
    public WebElement date_from;

    @FindBy(xpath = "//*[contains(@id, 'date_to')]")
    public WebElement date_to;

    @FindBy(xpath = "//*[contains(@class, 'btn btn-primary btn-lg BtnCabinet ShowBtn')]")
    public WebElement date_button;


    public void setCalendar() throws InterruptedException {
        new Actions(driver).scrollToElement(date_from).perform();
        date_from.sendKeys("23.04.2015");
        date_to.sendKeys(new SimpleDateFormat("dd.MM.yyyy").format(new Date()));
        date_button.click();
        Thread.sleep(300);
        new Actions(driver).scrollToElement(date_from).perform();
        Thread.sleep(2000);
    }
}
