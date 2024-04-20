package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.awt.SystemColor.window;

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

    public String[] brIdList = new String[] {"BrandCheckbox23","BrandCheckbox31","BrandCheckbox6", "BrandCheckbox7","BrandCheckbox20"};

    public void sortListButton(){
        filterButton.click();
        Select select = new Select(filterButton);
        select.selectByIndex(random.nextInt(6));
    }


    public int clickBrand() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver; js.executeScript("window.scrollBy(0,200)");
        Thread.sleep(500);
        brandButton.click();
        Thread.sleep(300);
        int i = random.nextInt(brIdList.length);
        js.executeScript("window.scrollBy(0,300)");
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
