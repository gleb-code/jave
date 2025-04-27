package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;
import pages.PaymentFrame;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class MtsByTest {
    private WebDriver driver;
    private MainPage mainPage;
    private PaymentFrame paymentFrame;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mainPage = new MainPage(driver);
        paymentFrame = new PaymentFrame(driver);
        driver.get("https://www.mts.by");
        mainPage.closeCookies();
    }

    @ParameterizedTest
    @MethodSource("serviceProvider")
    @DisplayName("Проверка плейсхолдеров для услуги")
    void testServicePlaceholders(String serviceName, Map<String, String> expected) {
        mainPage.selectService(serviceName);
        Map<String, String> actual = mainPage.getCurrentPlaceholders(serviceName);

        assertAll(
                () -> assertEquals(expected.get("identifier"), actual.get("identifier"),
                        "Неверный плейсхолдер идентификатора для " + serviceName),
                () -> assertEquals(expected.get("amount"), actual.get("amount"),
                        "Неверный плейсхолдер суммы для " + serviceName)
        );
    }

    private static Stream<Arguments> serviceProvider() {
        return Stream.of(
                Arguments.of("Услуги связи", Map.of(
                        "identifier", "Номер телефона",
                        "amount", "Сумма"
                )),
                Arguments.of("Домашний интернет", Map.of(
                        "identifier", "Номер абонента",
                        "amount", "Сумма"
                )),
                Arguments.of("Рассрочка", Map.of(
                        "identifier", "Номер счета на 44",
                        "amount", "Сумма"
                )),
                Arguments.of("Задолженность", Map.of(
                        "identifier", "Номер счета на 2073",
                        "amount", "Сумма"
                ))
        );
    }
    @Test
    @DisplayName("Проверка ссылки 'Подробнее о сервисе'")
    void testServiceDetailsLink() {
        // Кликаем по ссылке
        mainPage.clickDetailsLink();

        // Проверяем URL
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String actualUrl = mainPage.getCurrentUrl();

        assertEquals(expectedUrl, actualUrl, "Неверный URL после клика");

        // Возвращаемся назад и проверяем главную страницу
        driver.navigate().back();
        assertTrue(mainPage.getBlockTitle().contains("Онлайн пополнение без комиссии"));
    }

    @Test
    @DisplayName("Проверка заголовка блока")
    void testBlockTitle() {
        String expected = "Онлайн пополнение без комиссии";
        String actual = mainPage.getBlockTitle();
        assertEquals(expected, actual);
    }



    @Test
    @DisplayName("Проверка логотипов платёжных систем")
    void testPaymentLogos() {
        mainPage.verifyPaymentLogos();
    }

    @Test
    @DisplayName("Проверка формы оплаты")
    void testPaymentForm() {
        String testPhone = "297777777";
        String testAmount = "10";

        mainPage.fillForm(testPhone, testAmount);

        // Добавляем проверки в платёжном фрейме
        paymentFrame.validatePaymentDetails(testPhone, testAmount);

        paymentFrame.closePaymentFrame();
        assertTrue(driver.getCurrentUrl().contains("mts.by"));
    }



    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}