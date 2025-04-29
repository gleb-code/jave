package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;


public class PaymentFrame {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration WAIT_TIMEOUT = Duration.ofSeconds(15);

    // Локаторы
    public static final By PAYMENT_IFRAME = By.cssSelector("iframe.bepaid-iframe");
    public static final By PHONE_DISPLAY = By.xpath("//span[contains(., 'Оплата: Услуги связи')]");
    public static final By AMOUNT_DISPLAY = By.cssSelector("div.pay-description__cost > span");
    public static final By CARD_NUMBER_LABEL = By.xpath(".//input[@formcontrolname='creditCard']/ancestor::div[contains(@class,'content')]//label");
    public static final By EXPIRY_INPUT = By.cssSelector("input[formcontrolname='expirationDate']");
    public static final By CVC_LABEL = By.xpath(".//input[@formcontrolname='cvc']/ancestor::div[contains(@class,'content')]//label");
    public static final By PAYMENT_ICONS = By.cssSelector(".cards-brands img[src*='system']");
    private final By CLOSE_BUTTON = By.cssSelector(".header__close-button");


    public PaymentFrame(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, WAIT_TIMEOUT);
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