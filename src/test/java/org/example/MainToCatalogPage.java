package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainToCatalogPage {

    public WebDriver driver;
    public MainToCatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[contains(@class, 'nav-link dropdown-toggle ps-lg-0 ')]")
    public WebElement catalogMenu;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[1]/li/div/div[1]/div[3]/div/a")
    public WebElement phoneCatalogMenu;


    @FindBy(xpath = "//*[contains(@id, 'navbarDropdownMenuLink-20')]")
    public WebElement cabinetMenu;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[16]")
    public WebElement historyMenu;


     public void clickOnHistory() throws InterruptedException {
        cabinetMenu.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", historyMenu);
        Thread.sleep(100);

        historyMenu.click();
    }







    public void clickOnPhones() {
        catalogMenu.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        phoneCatalogMenu.click();
    }

}
