package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.*;


public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By COOKIE_BANNER = By.cssSelector(".cookie__cancel");


    private final By PAY_SECTION = By.id("pay-section");
    private final By BLOCK_TITLE = By.cssSelector("#pay-section h2");
    private final By PHONE_INPUT = By.id("connection-phone");
    private final By AMOUNT_INPUT = By.id("connection-sum");
    private final By PHONE_INPUT_HOME = By.id("internet-phone");
    private final By SUM_INTERNET = By.id("internet-sum");
    private final By NUMBER_INSTALMENT = By.id("score-instalment");
    private final By SUM_INSTALMENT = By.id("instalment-sum");
    private final By CONTRACT_INPUT = By.id("score-arrears");
    private final By DEBT_AMOUNT_INPUT = By.id("arrears-sum");
    private final By CONTINUE_BTN = By.xpath("//button[contains(., 'Продолжить')]");
    private final By DETAILS_LINK = By.linkText("Подробнее о сервисе");
    private final By PAYMENT_LOGOS = By.cssSelector("#pay-section .pay__partners li");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void selectService(String serviceName) {

        WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.select__header")
        ));
        dropdownTrigger.click();

        // 2. Ждем появления списка опций
        WebElement optionsList = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("ul.select__list")
        ));

        // 3. Ищем нужную опцию с учетом структуры DOM
        String xpath = String.format(
                ".//li[contains(@class, 'select__item')]/p[normalize-space()='%s']",
                serviceName
        );

        // 4. Прокрутка и клик через JS
        WebElement option = optionsList.findElement(By.xpath(xpath));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});",
                option
        );
        option.click();
    }

    public Map<String, String> getCurrentPlaceholders(String serviceName) {
        Map<String, String> placeholders = new HashMap<>();

        switch(serviceName) {
            case "Услуги связи":
                placeholders.put("identifier", getPlaceholder(PHONE_INPUT));
                placeholders.put("amount", getPlaceholder(AMOUNT_INPUT));
                break;
            case "Домашний интернет":
                placeholders.put("identifier", getPlaceholder(PHONE_INPUT_HOME));
                placeholders.put("amount", getPlaceholder(SUM_INTERNET));
                break;
            case "Рассрочка":
                placeholders.put("identifier", getPlaceholder(NUMBER_INSTALMENT));
                placeholders.put("amount", getPlaceholder(SUM_INSTALMENT));
                break;
            case "Задолженность":
                placeholders.put("identifier", getPlaceholder(CONTRACT_INPUT));
                placeholders.put("amount", getPlaceholder(DEBT_AMOUNT_INPUT));
                break;
            default:
                throw new IllegalArgumentException("Неизвестная услуга: " + serviceName);
        }

        return placeholders;
    }

    private String getPlaceholder(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .getAttribute("placeholder");
        } catch (TimeoutException e) {
            return "Поле не найдено";
        }
    }
    public void closeCookies() {
        try {
            WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(COOKIE_BANNER));
            closeBtn.click();
        } catch (Exception e) {
            System.out.println("Cookie banner не найден");
        }
    }






    public void clickDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(DETAILS_LINK));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", link);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public List<WebElement> getPaymentLogos() {
        WebElement paySection = wait.until(ExpectedConditions.visibilityOfElementLocated(PAY_SECTION));
        return paySection.findElements(PAYMENT_LOGOS);
    }


    public String getBlockTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(BLOCK_TITLE));
        return title.getText().replace("\n", " ").trim();
    }

    public void fillForm(String phone, String amount) {
        // Ввод телефона
        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT));
        phoneField.clear();
        phoneField.sendKeys(phone);

        // Ввод суммы
        WebElement amountField = driver.findElement(AMOUNT_INPUT);
        amountField.clear();
        amountField.sendKeys(amount);

        // Подтверждение
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(CONTINUE_BTN));
        continueButton.click();
    }
}