package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.PaymentFrame;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import org.openqa.selenium.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;



@Epic("Проверки онлайн-платежей МТС")
@Feature("Основной функционал оплаты")
public class MtsByTest {
    private WebDriver driver;
    private MainPage mainPage;
    private PaymentFrame paymentFrame;

    @BeforeEach
    @Step("Настройка окружения и открытие сайта")
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
    @DisplayName("Проверка плейсхолдеров для оплаты")
    @Story("Проверка корректности плейсхолдеров")
    @Description("Тест проверяет корректность отображения плейсхолдеров для различных услуг")
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
    @Story("Проверка перехода на ссылку 'Подробнее о сервисе'")
    @Description("Тест проверяет переходит на на другую ссылку url")
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
    @Story("Проверка надписи в заголовке блока")
    @Description("Тест проверяет текст надписи в заголовке блока")
    void testBlockTitle() {
        String expected = "Онлайн пополнение без комиссии";
        String actual = mainPage.getBlockTitle();
        assertEquals(expected, actual);
    }



    @Test
    @DisplayName("Проверка логотипов платёжных систем")
    @Story("Проверка наличия логотипов платёжных систем")
    @Description("Тест проверяет кол-во логотипов платёжных систем")
    void testPaymentLogos() {
        List<WebElement> logos = mainPage.getPaymentLogos();

        // Проверка наличия логотипов
        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены!");

        // Проверка количества
        assertEquals(5, logos.size(), "Количество логотипов не соответствует ожидаемому!");

        // Проверка видимости каждого логотипа
        logos.forEach(logo -> {
            assertTrue(logo.isDisplayed(),
                    "Логотип не отображается: " + logo.findElement(By.tagName("img")).getAttribute("src"));
        });
    }

    @Test
    @DisplayName("Проверка формы оплаты")
    @Story("Проверка сценария оплаты")
    @Description("Тест проверяет что данные отображаются при открытии вспомогательного фрейма")

    void testPaymentForm() {
        String testPhone = "297777777";
        String testAmount = "10";

        mainPage.fillForm(testPhone, testAmount);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Переключение на фрейм оплаты
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentFrame.PAYMENT_IFRAME));
        driver.switchTo().frame(frame);

        try {
            // Проверка номера телефона
            String expectedFullPhone = "375" + testPhone;
            WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentFrame.PHONE_DISPLAY));
            String actualPhone = phoneElement.getText().replaceAll("[^0-9]", "");
            assertEquals(expectedFullPhone, actualPhone, "Номер телефона не совпадает");

            // Проверка суммы
            WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentFrame.AMOUNT_DISPLAY));
            String actualAmountText = amountElement.getText().trim();
            assertTrue(actualAmountText.contains(testAmount),
                    "Сумма оплаты не совпадает. Ожидалось: " + testAmount + ", Фактически: " + actualAmountText);

            // Проверка полей карты
            validateLabel(wait, PaymentFrame.CARD_NUMBER_LABEL, "Номер карты");
            validatePlaceholder(wait, PaymentFrame.EXPIRY_INPUT, "MM / YY");
            validateLabel(wait, PaymentFrame.CVC_LABEL, "CVC");

            // Проверка иконок платежных систем
            List<WebElement> icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PaymentFrame.PAYMENT_ICONS));
            List<String> expectedIcons = List.of(
                    "visa-system.svg",
                    "mastercard-system.svg",
                    "belkart-system.svg",
                    "mir-system-ru.svg"
            );

            List<String> actualIcons = icons.stream()
                    .map(icon -> icon.getAttribute("src"))
                    .filter(Objects::nonNull)
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());

            expectedIcons.forEach(expectedIcon ->
                    assertTrue(actualIcons.stream().anyMatch(src -> src.contains(expectedIcon)),
                            "Отсутствует иконка: " + expectedIcon)
            );
        } finally {
            driver.switchTo().defaultContent();
        }

        paymentFrame.closePaymentFrame();
        assertTrue(driver.getCurrentUrl().contains("mts.by"));
    }

    private void validateLabel(WebDriverWait wait, By locator, String expectedText) {
        WebElement label = wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            return elements.stream()
                    .filter(e -> e.getText().trim().equals(expectedText))
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Лейбл '" + expectedText + "' не найден"));
        });
        assertTrue(label.isDisplayed(), "Лейбл '" + expectedText + "' не отображается");
    }

    private void validatePlaceholder(WebDriverWait wait, By locator, String expectedPlaceholder) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        assertEquals(expectedPlaceholder, field.getAttribute("placeholder"),
                "Неверный плейсхолдер для поля " + locator);
    }



    @AfterEach
    @Step("Завершение теста и закрытие браузера")
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}