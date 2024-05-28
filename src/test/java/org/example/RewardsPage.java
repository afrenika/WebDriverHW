package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RewardsPage {

    public WebDriver driver;
    public RewardsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }


    @FindBy(xpath = "/html/body/form/div/section/div[1]/div[2]/table[1]/tbody/tr/td/table[1]/tbody/tr[2]/td/div/table/tbody/tr/td[2]/table/tbody/tr/td[2]/img")
    public WebElement periodButton;

    @FindBy(xpath = "//*[@id=\"pivotGrid_DXHFP_HFL_LBSACB_S_D\"]")
    public WebElement periodAllButton;

    @FindBy(xpath = "//*[@id=\"pivotGrid_DXHFP_HFL_0_D\"]")
    public WebElement periodM1Button;
    @FindBy(xpath = "//*[@id=\"pivotGrid_DXHFP_HFL_1_D\"]")
    public WebElement periodM2Button;
    @FindBy(xpath = "//*[@id=\"pivotGrid_DXHFP_TPCFCm1_O\"]")
    public WebElement periodOKButton;

    @FindBy(xpath = "//*[@id=\"pivotGrid_sortedpgHeader4F\"]/img")
    public WebElement periodDataButton;

    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_LBSACB_S_D')]")
    public WebElement periodDataAllButton;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_0_D')]")
    public WebElement periodDataM1Button;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_1_D')]")
    public WebElement periodDataM2Button;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_TPCFCm1_O')]")
    public WebElement periodDataOKButton;



    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_sortedpgHeader4T')]")
    public WebElement periodDataOrderButton;

    @FindBy(xpath = "/html/body/div[2]/form/div/section/div[1]/div[2]/table[1]/tbody/tr/td/table[1]/tbody/tr[3]/td/div[2]/table/tbody/tr[2]/td[1]/table/tbody/tr/td/table/tbody/tr/td[3]/img")
    public WebElement periodBonusButton;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_LBSACB_S_D')]")
    public WebElement periodBonusAllButton;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_0_D')]")
    public WebElement periodBonusM1Button;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_1_D')]")
    public WebElement periodBonusM2Button;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_2_D')]")
    public WebElement periodBonusM3Button;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_HFL_3_D')]")
    public WebElement periodBonusM4Button;
    @FindBy(xpath = "//*[contains(@id, 'pivotGrid_DXHFP_TPCFCm1_O')]")
    public WebElement periodBonusOKButton;



    public void swipeToDiagram() throws InterruptedException {
//        WebElement diagram = driver.findElement(By.xpath("//*[contains(@id, 'webChart_IMG')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        new Actions(driver).scrollToElement(periodButton).perform();
        Thread.sleep(100);
    }

    public void clickPeriod() throws InterruptedException {
        int delayTime = 1500;
        swipeToDiagram();
        periodButton.click();
        Thread.sleep(delayTime);

        periodAllButton.click();
        periodM1Button.click();
        periodOKButton.click();
        Thread.sleep(delayTime);

        periodButton.click();
        Thread.sleep(500);
        periodAllButton.click();
        periodAllButton.click();
        periodM2Button.click();
        periodOKButton.click();
        Thread.sleep(delayTime);

        periodDataOrderButton.click();
        Thread.sleep(delayTime);
        periodDataOrderButton.click();
//        Thread.sleep(delayTime);

//        periodDataButton.click();
//        Thread.sleep(500);
//        periodDataAllButton.click();
//        periodDataM1Button.click();
//        periodDataOKButton.click();
//        Thread.sleep(delayTime);
//
//        periodDataButton.click();
//        Thread.sleep(500);
//        periodDataAllButton.click();
//        periodDataAllButton.click();
//        periodDataM2Button.click();
//        periodDataOKButton.click();
//        Thread.sleep(delayTime);


//        periodBonusButton.click();
//        Thread.sleep(500);
//        periodBonusAllButton.click();
//        periodBonusM1Button.click();
//        periodBonusOKButton.click();
//        Thread.sleep(delayTime);
//
//        periodBonusButton.click();
//        Thread.sleep(500);
//        periodBonusAllButton.click();
//        periodBonusAllButton.click();
//        periodBonusM2Button.click();
//        periodBonusOKButton.click();
//        Thread.sleep(delayTime);
//
//        periodBonusButton.click();
//        Thread.sleep(500);
//        periodBonusAllButton.click();
//        periodBonusAllButton.click();
//        periodBonusM3Button.click();
//        periodBonusOKButton.click();
//        Thread.sleep(delayTime);
//
//        periodBonusButton.click();
//        Thread.sleep(500);
//        periodBonusAllButton.click();
//        periodBonusAllButton.click();
//        periodBonusM4Button.click();
//        periodBonusOKButton.click();
//        Thread.sleep(delayTime);








    }


}
