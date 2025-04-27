package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentFrame {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    // Локаторы
    private final By PAYMENT_IFRAME = By.cssSelector("iframe.bepaid-iframe");
    private final By CLOSE_BUTTON = By.cssSelector(".header__close-button");
    private final By PHONE_DISPLAY = By.xpath("//span[contains(., 'Оплата: Услуги связи')]");


    private final By CARD_NUMBER_LABEL = By.xpath(
            ".//input[@formcontrolname='creditCard']/ancestor::div[contains(@class,'content')]//label"
    );

    private final By CVC_LABEL = By.xpath(
            ".//input[@formcontrolname='cvc']/ancestor::div[contains(@class,'content')]//label"
    );

    private final By EXPIRY_INPUT = By.cssSelector("input[formcontrolname='expirationDate']");



    private final By PAYMENT_ICONS = By.cssSelector(".cards-brands img[src*='system']");
    private final By AMOUNT_DISPLAY = By.cssSelector("div.pay-description__cost > span");

    public PaymentFrame(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
    }

    public void validatePaymentDetails(String expectedPhoneShort, String expectedAmount) {
        WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_IFRAME));
        driver.switchTo().frame(frame);

        try {
            validatePhoneNumber(expectedPhoneShort);
            validateAmount(expectedAmount);
            validateCardFields();
            validatePaymentIcons();
        } finally {
            driver.switchTo().defaultContent();
        }
    }

    private void validatePhoneNumber(String expectedPhoneShort) {
        String expectedFullPhone = "375" + expectedPhoneShort;
        WebElement phoneElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_DISPLAY));
        String actualPhone = phoneElement.getText().replaceAll("[^0-9]", "");
        assertEquals(expectedFullPhone, actualPhone, "Номер телефона не совпадает");
    }

    private void validateAmount(String expectedAmount) {
        WebElement amountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(AMOUNT_DISPLAY));
        String actualAmount = amountElement.getText().trim();
        assertTrue(actualAmount.contains(expectedAmount),
                "Сумма оплаты не совпадает. Ожидалось: " + expectedAmount + ", Фактически: " + actualAmount);
    }

    private void validateCardFields() {
        validateLabel(CARD_NUMBER_LABEL, "Номер карты");
        validatePlaceholder(EXPIRY_INPUT);
        validateLabel(CVC_LABEL, "CVC");
    }

    private void validateLabel(By locator, String expected) {
        WebElement label = wait.until(driver -> {
            List<WebElement> elements = driver.findElements(locator);
            return elements.stream()
                    .filter(e -> e.getText().trim().equals(expected))
                    .findFirst()
                    .orElse(null);
        });
        assertTrue(label.isDisplayed(), "Лейбл '" + expected + "' не отображается");
    }

    private void validatePlaceholder(By locator) {
        WebElement field = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        assertEquals("MM / YY", field.getAttribute("placeholder"));
    }



    private void validatePaymentIcons() {
        List<WebElement> icons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(PAYMENT_ICONS));
        List<String> expectedIcons = List.of(
                "visa-system.svg",
                "mastercard-system.svg",
                "belkart-system.svg",
                "mir-system-ru.svg"
        );

        List<String> actualIcons = icons.stream()
                .map(icon -> icon.getAttribute("src"))
                .filter(src -> src != null)
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        expectedIcons.forEach(expected -> {
            assertTrue(actualIcons.stream().anyMatch(src -> src.contains(expected)),
                    "Отсутствует иконка: " + expected);
        });
    }

    public void closePaymentFrame() {
        try {
            driver.switchTo().defaultContent();
            WebElement frame = wait.until(ExpectedConditions.visibilityOfElementLocated(PAYMENT_IFRAME));
            driver.switchTo().frame(frame);

            WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(CLOSE_BUTTON));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", closeButton);
            closeButton.click();

            wait.until(ExpectedConditions.invisibilityOf(frame));
        } catch (TimeoutException e) {
            throw new RuntimeException("Ошибка при закрытии фрейма: " + e.getMessage(), e);
        } finally {
            driver.switchTo().defaultContent();
        }
    }
}