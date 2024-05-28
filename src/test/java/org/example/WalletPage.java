package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WalletPage {
    public WebDriver driver;
    public WalletOperationPage walletOperationPage;
    public WalletPage(WebDriver driver, WalletOperationPage walletOperationPage) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.walletOperationPage = walletOperationPage;
    }

    @FindBy(xpath = "//*[@id=\"Accounts2052\"]/div/div[2]/button")
    public WebElement operation;



    public void clickOperation() throws InterruptedException {
        new Actions(driver).scrollToElement(operation).perform();
        Thread.sleep(100);
        operation.click();
        walletOperationPage.setCalendar();
    }


}
