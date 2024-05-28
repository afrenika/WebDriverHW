package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CardPage {
    public WebDriver driver;

    public CardPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[@id=\"totalQuantity\"]")
    public WebElement totalQuantity;

    @FindBy(xpath = "//*[@id=\"totalAmountStartPriceSum\"]")
    public WebElement totalAmountStartPriceSum;

    @FindBy(xpath = "//*[@id=\"wholeTotalFirst\"]")
    public WebElement wholeTotalFirst;

    @FindBy(xpath = "//*[contains(@id, 'BtnOrderCreate')]")
    public WebElement orderButton;

    @FindBy(xpath = "//*[contains(@id, 'deliveryInput_1')]")
    public WebElement deliveryPlace;

    @FindBy(xpath = "//*[contains(@id, 'Country')]")
    public WebElement deliveryCountry;


    @FindBy(xpath = "//*[contains(@class, 'btn btn-danger btn-sm px-1')]")
    public WebElement cancelOrder;

    @FindBy(xpath = "//*[contains(@class, 'w-100 btn btn-primary ModalButtonColor')]")
    public WebElement cancelOrderOk;


    @FindBy(xpath = "//*[contains(@id, 'deliveryAddressInput_3494')]")
    public WebElement deliveryAdres;









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


    public void createOrder() throws InterruptedException {
        scrollAndClick(orderButton);
        Thread.sleep(200);
        scrollAndClick(deliveryPlace);
        Thread.sleep(200);
        scrollAndClick(deliveryCountry);
        Thread.sleep(200);
        Select select = new Select(deliveryCountry);
        select.selectByIndex(1);
        Thread.sleep(6000);
//        scrollAndClick(deliveryAdres);
//        Thread.sleep(200);
        scrollAndClick(orderButton);
        Thread.sleep(1000);
    }

    public void cancelOrderClick() throws InterruptedException {
        Thread.sleep(7000);
        scrollAndClick(cancelOrder);
//        cancelOrderOk.click();
    }
}
