package org.example;//package org.example;
//
//import io.qameta.allure.Allure;
//import io.qameta.allure.Description;
//import io.qameta.allure.Feature;
//import org.junit.*;
//import org.junit.jupiter.api.DisplayName;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import java.nio.charset.StandardCharsets;
//import java.util.concurrent.TimeUnit;
//@Feature(("Test all"))
//public class LoginTest {
//
//    public static LoginPage loginPage;
//    public static ProfileLoginPage profileLoginPage;
//
//    public static MainToCatalogPage mainToCatalogPage;
//    public static CatalogPage catalogPage;
//
//    public static HistoryPage historyPage;
//
//    public static RewardsPage rewardsPage;
//
//    public static WalletPage walletPage;
//    public static WalletOperationPage walletOperationPage;
//
//    public static FinRequestPage finRequestPage;
//
//    public static WebDriver driver;
//
//    /**
//     * Осуществление первоначальной настройки
//     */
//    @BeforeClass
//    public static void setup() throws InterruptedException {
//        //определение пути до драйвера и его настройка
//        //создание экземпляра драйвера
//        driver = switch (ConfProperties.getProperty("browser")) {
//          case "firefox" -> new FirefoxDriver();
//          case "edge" -> new EdgeDriver();
//          default -> new ChromeDriver();
//};
//
//
//        loginPage = new LoginPage(driver);
//        profileLoginPage = new ProfileLoginPage(driver);
//
//        mainToCatalogPage = new MainToCatalogPage(driver);
//        catalogPage = new CatalogPage(driver);
//
//        historyPage = new HistoryPage(driver);
//
//        rewardsPage = new RewardsPage(driver);
//
//
//        walletOperationPage = new WalletOperationPage(driver);
//        walletPage = new WalletPage(driver, walletOperationPage);
//
//        finRequestPage = new FinRequestPage(driver);
//
//        //окно разворачивается на полный экран
//        driver.manage().window().maximize();
//        //задержка на выполнение теста = 10 сек.
//        driver.get(ConfProperties.getProperty("mainPage"));
//        Thread.sleep(300);
//        //получение ссылки на страницу входа из файла настроек
//    }
//
//
//    /**
//     * тестовый метод для осуществления аутентификации
//     */
//    @Test
//    @DisplayName("Test Authentication")
//    public void loginTest() throws InterruptedException {
//        //получение доступа к методам класса org.example.LoginPage для взаимодействия с элементами страницы
//        //значение login/password берутся из файла настроек по аналогии с chromedriver
//        //и loginpage
//        //вводим логин и пароль
//        loginPage.clickLoginMenuBtn();
//        Thread.sleep(300);
//        loginPage.inputLogin(ConfProperties.getProperty("login"));
//        loginPage.inputPasswd(ConfProperties.getProperty("password"));
//        //нажимаем кнопку входа
//        loginPage.clickLoginBtn();
//        //получаем отображаемый логин
//        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
//
//        String[] user = profileLoginPage.getUserName().split("\n");
//        System.out.println("Логин после входа = " + user[1]);
//        //и сравниваем его с логином из файла настроек
//
//        Assert.assertEquals(new String(ConfProperties.getProperty("loginCheck").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8), user[1]);
//        Thread.sleep(400);
//    }
//
////        Assert.assertEquals(ConfProperties.getProperty("loginCheck"), user); }
//
//    @Test
//    @Ignore
//    public void catalogTest() throws InterruptedException {
//        mainToCatalogPage.clickOnPhones();
//        Thread.sleep(400);
//        for (int i = 0; i < 3; i++) {
//            int br = catalogPage.clickBrand();
//            Thread.sleep(300);
//            Assert.assertEquals(catalogPage.checkClickBrand(br), "true");
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
//
//    }
//
//    @Test
//    @Ignore
//    @DisplayName("Pagination test")
//    @Description("pagination test on the order history page")
//    public void historyTest() throws InterruptedException {
//        mainToCatalogPage.clickOnHistory();
//        historyPage.getPagAndCountElements2();
//        mainToCatalogPage.up();
//        Thread.sleep(500);
////        historyPage.getPagAndCountElements2();
//    }
//
//    @Test
//    @DisplayName("Test reward filtering")
//    public void rewardsTest1() throws InterruptedException {
//        mainToCatalogPage.clickOnRewards();
//        rewardsPage.clickPeriod();
//        mainToCatalogPage.up();
//        Thread.sleep(700);
//    }
//
//    @Test
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
//
//    @Test
//    @DisplayName("Testing the calendar")
//    public void walletTest3() throws InterruptedException {
//        mainToCatalogPage.clickOnWallet();
//        walletPage.clickOperation();
//        mainToCatalogPage.up();
//        Thread.sleep(1000);
//    }
//
//
//
//
//
//    /*
//     * Осуществление выхода из аккаунта с последующим закрытием окна браузера
//     */
//    @AfterClass
//    public static void tearDown() throws InterruptedException {
//        Thread.sleep(2000);
//        profileLoginPage.userLogout();
//        Thread.sleep(1000);
//        driver.quit();
//    }
//
//}