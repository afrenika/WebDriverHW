package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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


    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-20\"]")
    public WebElement cabinetMenu;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[16]")
    public WebElement historyMenu;

    @FindBy(xpath = "/html/body/header/nav[3]/div/div/ul[2]/li[5]/div/a[2]")
    public WebElement rewardsMenu;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[7]")
    public WebElement walletMenu;

    @FindBy(xpath = "//*[@id=\"navbarCollapse\"]/ul[2]/li[5]/div/a[8]")
    public WebElement finRequestMenu;

    @FindBy(xpath = "/html/body/header/nav[2]/div/div[2]/div[2]/a")
    public WebElement card;


    public void up() throws InterruptedException {
        WebElement webElement = driver.findElement(By.xpath("//*[contains(@class, 'navbar-brand d-none d-sm-block flex-shrink-0 mt-1')]"));
        new Actions(driver).scrollToElement(webElement).perform();
        Thread.sleep(100);
    }



     public void clickOnHistory() throws InterruptedException {
        cabinetMenu.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", historyMenu);
        Thread.sleep(100);

        historyMenu.click();
    }

    public void clickOnRewards() throws InterruptedException {
        cabinetMenu.click();
        Thread.sleep(100);
        rewardsMenu.click();
    }

    public void clickOnWallet() throws InterruptedException {
        cabinetMenu.click();
        Thread.sleep(100);
        walletMenu.click();
    }


    public void clickOnFinRequest() throws InterruptedException {
        cabinetMenu.click();
        Thread.sleep(100);
        finRequestMenu.click();
    }







    public void clickOnPhones() {
        catalogMenu.click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        phoneCatalogMenu.click();
    }

}
