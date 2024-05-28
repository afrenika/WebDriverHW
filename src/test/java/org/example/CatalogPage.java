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

public class CatalogPage {

    Random random = new Random();

    public WebDriver driver;
    public CatalogPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

    @FindBy(xpath = "//*[@id=\"FilterBrands\"]/h5/strong/a")
    public WebElement brandButton;

    @FindBy(xpath = "//*[contains(@class, 'btn btn-sm btn-secondary waves-effect waves-light ClearFilter')]")
    public WebElement filterClearButton;

    @FindBy(xpath = "//*[contains(@id, 'SortOrder')]")
    public WebElement filterButton;

    @FindBy(xpath = "//*[@id=\"FilterPrice\"]/h5/strong/a")
    public WebElement filterCostButton;


    @FindBy(xpath = "//*[@id=\"CatalogueSection\"]/section/div[2]/div[2]/div/div[3]/div/button[1]")
            public WebElement buy2;
    @FindBy(xpath = "//*[@id=\"CatalogueSection\"]/section/div[2]/div[3]/div/div[3]/div/button[1]")
            public WebElement buy3;

    public String[] brIdList = new String[] {"BrandCheckbox23","BrandCheckbox31","BrandCheckbox6", "BrandCheckbox7","BrandCheckbox20"};

    public void sortListButton(){
        filterButton.click();
        Select select = new Select(filterButton);
        select.selectByIndex(random.nextInt(6));
    }

    @FindBy(xpath = "//*[contains(@class, 'navbar-tool-icon-box bg-light ')]")
            public WebElement card;



    public void scroll(WebElement webElement) throws InterruptedException {
        new Actions(driver)
                            .scrollToElement(webElement)
                            .perform();
    }
    public void scrollAndClick(WebElement webElement) throws InterruptedException {
        new Actions(driver)
                            .scrollToElement(webElement)
                            .perform();

        Thread.sleep(400);
        webElement.click();
    }
    public void clickToCard1() throws InterruptedException {
        card.click();
        Thread.sleep(500);
    }

    public void clickToCard2() throws InterruptedException {
        System.out.println("ok");
        scrollAndClick(buy2);
        Thread.sleep(500);
        scrollAndClick(card);
//        scroll(card);
//        card.click();
//        Thread.sleep(500);
    }

    public int clickBrand() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(500);
        brandButton.click();
        Thread.sleep(300);
        int i = random.nextInt(brIdList.length);
//        scroll(buy1);
        Thread.sleep(400);
        WebElement brandButton = driver.findElement(By.xpath("//*[contains(@id, '" + brIdList[i]+"')]"));
//        System.out.println(brIdList[i]);
        brandButton.click();
        return i;
    }

    public String checkClickBrand(int i) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(500);
        brandButton.click();
        Thread.sleep(300);
        WebElement brandButton = driver.findElement(By.xpath("//*[contains(@id, '" + brIdList[i]+"')]"));
        js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(300);
        return brandButton.getAttribute("checked");
    }

    public void filterClear() throws InterruptedException {
////        System.out.println(filterButton.getText());
        JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,300)");
        Thread.sleep(400);
        filterClearButton.click();
    }
}
