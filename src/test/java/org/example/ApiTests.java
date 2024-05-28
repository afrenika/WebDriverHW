package org.example;

import com.github.dzieciou.testing.curl.CurlLoggingRestAssuredConfigFactory;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.internal.shadowed.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.codehaus.plexus.util.FileUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Feature(("Test all"))
public class ApiTests {

    public static CardPage cardPage;
    public static LoginPage loginPage;
    public static WebDriver driver;
    public static ProfileLoginPage profileLoginPage;
    public static CatalogPage catalogPage;
    public static MainToCatalogPage mainToCatalogPage;
     public static FinRequestPage finRequestPage;
     public static String cookies_s = "";

    public static void setup(String browser) throws InterruptedException, IOException {
        FileUtils.deleteDirectory(new File("allure-results"));
        //определение пути до драйвера и его настройка
        //создание экземпляра драйвера
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);

        if (Objects.equals(browser, "firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new FirefoxDriver(options);
        } else if (Objects.equals(browser, "edge")) {
            EdgeOptions options = new EdgeOptions();
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new EdgeDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.setCapability("goog:loggingPrefs", logPrefs);
            driver = new ChromeDriver(options);
        }


        cardPage = new CardPage(driver);
        loginPage = new LoginPage(driver);
        catalogPage = new CatalogPage(driver);
        profileLoginPage = new ProfileLoginPage(driver);
        mainToCatalogPage = new MainToCatalogPage(driver);
        finRequestPage = new FinRequestPage(driver);

        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.get(ConfProperties.getProperty("mainPage"));

        Thread.sleep(300);
        //получение ссылки на страницу входа из файла настроек
    }


    @DisplayName("Test Authentication")
    public static void loginTest() throws InterruptedException {
        //получение доступа к методам класса org.example.LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин и пароль
        loginPage.clickLoginMenuBtn();
        Thread.sleep(600);
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();

        //получаем отображаемый логин
        Thread.sleep(700);





        String user = profileLoginPage.getUserName();
        System.out.println("Логин после входа = " + user);
        //и сравниваем его с логином из файла настроек

        Assertions.assertEquals(new String(ConfProperties.getProperty("loginCheck").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), user);
        Thread.sleep(400);
        getCookie();
        screenshot();
    }

    @Epic("Chrome")
    @Feature("Fronted")
    @BeforeAll
    public static void loginingTest() throws InterruptedException, IOException {
        setup("Chrome");
        loginTest();

    }

    static void getCookie(){
        cookies_s = "";
        Set<Cookie> cookies = driver.manage().getCookies();

        for( Cookie cookie: cookies){
            cookies_s = cookies_s + (cookie.getName() + "=" + cookie.getValue()) + "; ";
        }
    }

    @Epic("Chrome")
    @Feature("Api")
    @Test
    @DisplayName("Test Card")
    void cardTest() throws JsonProcessingException, InterruptedException {
        int count = driver.manage().logs().get(LogType.BROWSER).getAll().size();
        try{
        driver.get(ConfProperties.getProperty("mainPage"));
        Thread.sleep(100);
//        mainToCatalogPage.clickOnPhones();
        mainToCatalogPage.clickOnPhones();
        catalogPage.clickToCard2();
        getCookie();
        RestAssured.baseURI = "https://demo.oksoft.ru";


       Response response =
           given()
                   .header("cookie", cookies_s)
                   .body("")
           .when()
              .post("/JsonAndSettingsController/CartOrderedInfoGet")
           .then()
              .extract().response();

        Assertions.assertEquals(response.getStatusCode(), 200);
        // Проверяем поля в ответе API
        response
                .then()
                .body("totalQty", equalTo(Integer.parseInt(cardPage.totalQuantity.getText())))
                .body("totalDiscount", equalTo(cardPage.totalAmountStartPriceSum.getText().replace(" ", new String(new byte[]{-62, -96}))))
                .body("totalSum", equalTo(cardPage.wholeTotalFirst.getText().replace(" ", new String(new byte[]{-62, -96}))));

        StringBuilder logg = new StringBuilder();
        var t = driver.manage().logs().get(LogType.BROWSER).getAll();
        for(int i = count; i < t.size(); i++){
            logg.append(t.get(i)).append("\n");
        }
        Allure.addAttachment("log", logg.toString());
        screenshot();
        System.out.println("Ok");
        }
        catch (Exception e){
            StringBuilder logg = new StringBuilder();
            var t = driver.manage().logs().get(LogType.BROWSER).getAll();
            for(int i = count; i < t.size(); i++){
                logg.append(t.get(i)).append("\n");
            }
            Allure.addAttachment("log", logg.toString());
            screenshot();}

    }


    Response finResponse(String sum, int type ) {
        String requestBody = "Comment=&Inn=&Kpp=&Bank=&Rs=&Ks=&Bik=";

        RestAssured.baseURI = "https://demo.oksoft.ru";
        System.out.println("coockie = " + cookies_s);
       Response response =
           given()
                   .queryParam("Amount", sum)
                   .queryParam("FinOrdersOperTypes", type)
                   .header("cookie", cookies_s)
                   .body(requestBody)
           .when()
              .post("/User/FinOrdersCreate");


       return response.then().extract().response();
    }

    @Test
    @Epic("Chrome")
    @Feature("Api")
    @DisplayName("Testing the addition(pos, neg)")
    void RT() throws InterruptedException {
        int count = driver.manage().logs().get(LogType.BROWSER).getAll().size();
        try{
        driver.get(ConfProperties.getProperty("mainPage"));
        Thread.sleep(100);
        mainToCatalogPage.clickOnFinRequest();
        finRequestPage.setRequestInfo("aa", 2);
        var wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement toastE = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > div.toast-message")));
        String toast = toastE.getText();
        toastE.click();
        Response response =  finResponse("aa", 2);

        Assertions.assertEquals(200, response.getStatusCode());

        response.then()
                .body("resultText", equalTo("Неверно указана сумма"))
                .body("resultText", equalTo(toast))
                .body("resultCode", equalTo(-1));

        Thread.sleep(100);
        finRequestPage.cancelRequestButton.click();
        finRequestPage.setRequestInfo("-10", 2);
        toastE = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > div.toast-message")));
        toast = toastE.getText();
        toastE.click();
        response =  finResponse("-10", 2);
        Assertions.assertEquals(response.getStatusCode(), 200);

        response.then()
                .body("resultText", equalTo("Указана отрицательная сумма"))
                .body("resultText", equalTo(toast))
                .body("resultCode", equalTo(-2));
        Thread.sleep(300);

        finRequestPage.cancelRequestButton.click();
        finRequestPage.setRequestInfo("0", 2);
        toastE = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > div.toast-message")));
        toast = toastE.getText();
        toastE.click();
        Thread.sleep(100);
        response =  finResponse("0", 2);
        Assertions.assertEquals(response.getStatusCode(), 200);

        response.then()
                .body("resultText", equalTo("Сумма не указана"))
                .body("resultText", equalTo(toast))
                .body("resultCode", equalTo(-2));

        Thread.sleep(300);

        finRequestPage.cancelRequestButton.click();
        finRequestPage.setRequestInfo("1000", 1);
        toastE = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div > div.toast-message")));
        toast = toastE.getText();
        toastE.click();
        response =  finResponse("1000", 1);
        Assertions.assertEquals(response.getStatusCode(), 200);

        response.then()
                .body("resultText", equalTo("Недостаточно средств на счете. Доступный остаток: 0.00"))
                .body("resultText", equalTo(toast))
                .body("resultCode", equalTo(-7));

        Thread.sleep(300);

        finRequestPage.cancelRequestButton.click();
        finRequestPage.setRequestInfo("10", 2);
        response =  finResponse("10", 2);
        Assertions.assertEquals(response.getStatusCode(), 200);

        response.then()
                .body("resultText", matchesPattern("Документ [0-9]* успешно создан"))
                .body("resultCode", equalTo(1));
        Thread.sleep(300);

        StringBuilder logg = new StringBuilder();
        var t = driver.manage().logs().get(LogType.BROWSER).getAll();
        for(int i = count; i < t.size(); i++){
            logg.append(t.get(i)).append("\n");
        }
        Allure.addAttachment("log", logg.toString());
        screenshot();}
    catch (Exception e){
            StringBuilder logg = new StringBuilder();
            var t = driver.manage().logs().get(LogType.BROWSER).getAll();
            for(int i = count; i < t.size(); i++){
                logg.append(t.get(i)).append("\n");
            }
            Allure.addAttachment("log", logg.toString());
            screenshot();}

    }

    @Test
    @Epic("Chrome")
    @Feature("Api")
    @DisplayName("Test OrderCancel")
    void orderHistoryTest() throws InterruptedException {
        int count = driver.manage().logs().get(LogType.BROWSER).getAll().size();
        try{
        driver.get(ConfProperties.getProperty("mainPage"));
        Thread.sleep(100);
        mainToCatalogPage.clickOnPhones();
        catalogPage.clickToCard2();
        getCookie();
        cardPage.createOrder();
        Thread.sleep(700);
        cardPage.cancelOrderClick();

        var orderID = cardPage.cancelOrderOk.getAttribute("onclick");
        orderID = orderID.substring(orderID.indexOf("(") + 2, orderID.indexOf(")") - 1);
        cardPage.cancelOrderOk.click();
        Thread.sleep(300);
        Response response =
                given()
                        .queryParam("SalesGuid", orderID)
                        .header("cookie", cookies_s)
                        .body("")
                .when()
                .post("/User/SalesDelete")
           .then()
              .extract().response();

        Assertions.assertEquals(response.getStatusCode(), 200);
        response
                .then()
                .body("resultCode", equalTo(-1))
                .body("resultText", equalTo("Документ удален."));

        Thread.sleep(200);

        StringBuilder logg = new StringBuilder();
        var t = driver.manage().logs().get(LogType.BROWSER).getAll();
        for(int i = count; i < t.size(); i++){
            logg.append(t.get(i)).append("\n");
        }
        Allure.addAttachment("log", logg.toString());
        screenshot();}
        catch (Exception e){
            StringBuilder logg = new StringBuilder();
            var t = driver.manage().logs().get(LogType.BROWSER).getAll();
            for(int i = count; i < t.size(); i++){
                logg.append(t.get(i)).append("\n");
            }
            Allure.addAttachment("log", logg.toString());
            screenshot();}





    }

    @AfterAll
    public static void tearDown() throws InterruptedException {
        profileLoginPage.userLogout();
        Thread.sleep(1000);
//        driver.manage().logs().get(LogType.BROWSER).getAll();
        driver.quit();
    }


    public static void screenshot() {
        if (driver == null) {
            System.out.println("Driver for screenshot not found");
            return;
        }
        Allure.addAttachment("page_screen", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }
}
