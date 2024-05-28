package org.example;


import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;

@Feature(("Test all"))
public class TestTest {

    public static LoginPage loginPage;
    public static ProfileLoginPage profileLoginPage;

    public static MainToCatalogPage mainToCatalogPage;
    public static CatalogPage catalogPage;

    public static HistoryPage historyPage;

    public static RewardsPage rewardsPage;

    public static WalletPage walletPage;
    public static WalletOperationPage walletOperationPage;

    public static FinRequestPage finRequestPage;

    public static WebDriver driver;


    @ParameterizedTest
    @CsvSource({"chrome", "firefox", "edge"})
    public void test(String browser) throws InterruptedException {
        setup(browser);
        catalogTest();
//
//        loginTest();
//        rewardsTest1();
//        finRequestTest2();
//        walletTest3();
        tearDown();
    }
    /**
     * Осуществление первоначальной настройки
     */
    public static void setup(String browser) throws InterruptedException {
        //определение пути до драйвера и его настройка
        //создание экземпляра драйвера
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        if(Objects.equals(browser, "firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new FirefoxDriver(options);
        }
        else if(Objects.equals(browser, "edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new EdgeDriver(options);}
        else {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new ChromeDriver(options);}

        loginPage = new LoginPage(driver);
        profileLoginPage = new ProfileLoginPage(driver);

        mainToCatalogPage = new MainToCatalogPage(driver);
        catalogPage = new CatalogPage(driver);

        historyPage = new HistoryPage(driver);

        rewardsPage = new RewardsPage(driver);


        walletOperationPage = new WalletOperationPage(driver);
        walletPage = new WalletPage(driver, walletOperationPage);

        finRequestPage = new FinRequestPage(driver);

        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.get(ConfProperties.getProperty("mainPage"));
        Thread.sleep(300);
        //получение ссылки на страницу входа из файла настроек
    }


    /**
     * тестовый метод для осуществления аутентификации
     */
    @DisplayName("Test Authentication")
    public void loginTest() throws InterruptedException {
        //получение доступа к методам класса org.example.LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин и пароль
        loginPage.clickLoginMenuBtn();
        Thread.sleep(300);
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //получаем отображаемый логин
        Thread.sleep(300);


        String user = profileLoginPage.getUserName();
        System.out.println("Логин после входа = " + user);
        //и сравниваем его с логином из файла настроек

        Assertions.assertEquals(new String(ConfProperties.getProperty("loginCheck").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), user);
        Thread.sleep(400);
    }

//        Assert.assertEquals(ConfProperties.getProperty("loginCheck"), user); }

    @Test
    @Disabled
    public void catalogTest() throws InterruptedException {
        mainToCatalogPage.clickOnPhones();
        catalogPage.clickToCard1();
//        Thread.sleep(400);
//        for (int i = 0; i < 3; i++) {
//            int br = catalogPage.clickBrand();
//            Thread.sleep(300);
//            Assertions.assertEquals(catalogPage.checkClickBrand(br), "true");
//            Thread.sleep(1000);
//            catalogPage.filterClear();
//            Thread.sleep(500);
//        }
//        for (int i = 0; i < 5; i++) {
//            catalogPage.sortListButton();
//            Thread.sleep(1000);
//        }
//        mainToCatalogPage.up();
//        Thread.sleep(700);

    }

    @Disabled
    @DisplayName("Pagination test")
    @Description("pagination test on the order history page")
    public void historyTest() throws InterruptedException {
        mainToCatalogPage.clickOnHistory();
        historyPage.getPagAndCountElements2();
        mainToCatalogPage.up();
        Thread.sleep(500);
//        historyPage.getPagAndCountElements2();
    }

    @DisplayName("Test reward filtering")
    public void rewardsTest1() throws InterruptedException {
        mainToCatalogPage.clickOnRewards();
        rewardsPage.clickPeriod();
        mainToCatalogPage.up();
        Thread.sleep(700);
    }

//    @DisplayName("Testing the addition and scrolling of requests")
//    public void finRequestTest2() throws InterruptedException {
//        mainToCatalogPage.clickOnFinRequest();
//        finRequestPage.setRequestInfo();
//        Thread.sleep(300);
//        finRequestPage.scrollRequests();
//        Thread.sleep(500);
//        finRequestPage.searchRequest();
//        mainToCatalogPage.up();
//        Thread.sleep(700);
//
//    }

    @Disabled
    @DisplayName("Testing the calendar")
    public void walletTest3() throws InterruptedException {
        mainToCatalogPage.clickOnWallet();
        walletPage.clickOperation();
        mainToCatalogPage.up();
        Thread.sleep(1000);
    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        Thread.sleep(2000);
//        profileLoginPage.userLogout();
//        Thread.sleep(1000);
        driver.quit();
        driver.manage().logs().get(LogType.BROWSER).getAll();
    }








}

