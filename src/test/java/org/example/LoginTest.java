package org.example;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;
public class LoginTest {

    public static LoginPage loginPage;
    public static ProfileLoginPage profileLoginPage;

    public static MainToCatalogPage mainToCatalogPage;
    public static CatalogPage catalogPage;

    public static HistoryPage historyPage;

    public static WebDriver driver;

    /**
     * Осуществление первоначальной настройки
     */
    @BeforeClass
    public static void setup() throws InterruptedException {
        //определение пути до драйвера и его настройка
        //создание экземпляра драйвера
        driver = new ChromeDriver();

        loginPage = new LoginPage(driver);
        profileLoginPage = new ProfileLoginPage(driver);

        mainToCatalogPage = new MainToCatalogPage(driver);
        catalogPage = new CatalogPage(driver);

        historyPage = new HistoryPage(driver);


        //окно разворачивается на полный экран
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.get(ConfProperties.getProperty("mainPage"));
        Thread.sleep(100);
        //получение ссылки на страницу входа из файла настроек
    }

    /**
     * тестовый метод для осуществления аутентификации
     */
    @Test
    public void loginTest() throws InterruptedException {
        //получение доступа к методам класса org.example.LoginPage для взаимодействия с элементами страницы
        //значение login/password берутся из файла настроек по аналогии с chromedriver
        //и loginpage
        //вводим логин и пароль
        loginPage.clickLoginMenuBtn();
        Thread.sleep(200);
        loginPage.inputLogin(ConfProperties.getProperty("login"));
        loginPage.inputPasswd(ConfProperties.getProperty("password"));
        //нажимаем кнопку входа
        loginPage.clickLoginBtn();
        //получаем отображаемый логин
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);

        String[] user = profileLoginPage.getUserName().split("\n");
        System.out.println("Логин после входа = " + user[1]);
        //и сравниваем его с логином из файла настроек

        Assert.assertEquals(new String(ConfProperties.getProperty("loginCheck").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), user[1]);
        Thread.sleep(400);
    }

//        Assert.assertEquals(ConfProperties.getProperty("loginCheck"), user); }

    @Ignore
    @Test
    public void catalogTest() throws InterruptedException {
        mainToCatalogPage.clickOnPhones();
        Thread.sleep(400);
        for (int i = 0; i < 3; i++) {
            int br = catalogPage.clickBrand();
            Thread.sleep(300);
            Assert.assertEquals(catalogPage.checkClickBrand(br), "true");
            Thread.sleep(1000);
            catalogPage.filterClear();
            Thread.sleep(500);
        }
        for (int i = 0; i < 5; i++) {
            catalogPage.sortListButton();
            Thread.sleep(1000);
        }

    }

    @Test
    public void historyTest() throws InterruptedException {
        mainToCatalogPage.clickOnHistory();
        historyPage.getPagAndCountElements();
//        historyPage.getPagAndCountElements2();
    }


    /*
     * Осуществление выхода из аккаунта с последующим закрытием окна браузера
     */
    @AfterClass
    public static void tearDown() throws InterruptedException {
//        Thread.sleep(1000);
//        profileLoginPage.userLogout();
//        Thread.sleep(1000);
        driver.quit(); }

}