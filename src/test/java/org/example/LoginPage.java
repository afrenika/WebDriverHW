package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public WebDriver driver;
    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }

//@FindBy(xpath = "//*[@id="root"]/div/div/div[2]/div/div/div[3]/div[2]/div/div/div[1]/form/div[1]/div[1]/label")


    @FindBy(xpath = "//*[contains(@class, 'navbar-tool ms-1 ms-lg-0 me-n1 me-lg-2')]")
    private WebElement menuLogin;

    @FindBy(xpath = "//*[contains(@id, 'ModalLoginUserName')]")
    private WebElement loginField;

    @FindBy(xpath = "//*[contains(@id, 'ModalLoginPassword')]")
    private WebElement passwdField;

    @FindBy(xpath = "//*[contains(@class, 'LoginBtn w-100 btn btn-primary mb-3')]")
    private WebElement loginBtn;


    public void inputLogin(String login) {
        loginField.sendKeys(login); }

    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd); }

    public void clickLoginMenuBtn() {
        menuLogin.click(); }

    public void clickLoginBtn() {
        loginBtn.click(); }
}
