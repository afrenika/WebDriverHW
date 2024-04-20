package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfileLoginPage {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;

    public ProfileLoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//*[contains(@id, 'navbarDropdownMenuLink-4')]")
    private WebElement logoutMenu1;

    @FindBy(xpath = "//*[contains(@class, ' dropdown-item pb-1')]")
    private WebElement logoutMenu2;


    @FindBy(xpath = "//*[@id=\"navbarDropdownMenuLink-4\"]/div[2]")
    private WebElement userName;


    public String getUserName() {

        return userName.getText();
    }

    /**
     * метод для нажатия кнопки выхода из аккаунта
     */
    public void userLogout() throws InterruptedException {
        logoutMenu1.click();
        Thread.sleep(300);
        logoutMenu2.click();
    }
}