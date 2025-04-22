import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

public class MtsByTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.mts.by");
    }

    @Test
    public void testAcceptCookies() {
        // Закрываем всплывающее окно с куками, если оно есть
        try {
            WebElement cookieCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cookie__cancel")));
            cookieCloseButton.click();
        } catch (Exception e) {
            // Если кнопка закрытия не найдена, ничего не делаем
        }
    }


    @ParameterizedTest
    @ValueSource(strings = {"Онлайн пополнение\nбез комиссии"})
    public void testMoreAboutServiceLink(String expectedTitle) {
        // Вызываем метод для принятия куков
        testAcceptCookies();

        // Находим блок "Онлайн пополнение без комиссии"
        WebElement onlineTopUpBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pay-section")));
        assertTrue(onlineTopUpBlock.isDisplayed(), "Блок 'Онлайн пополнение без комиссии' не отображается");

        // Проверяем заголовок блока
        verifyBlockTitle(onlineTopUpBlock, expectedTitle);

        // Проверяем логотипы платёжных систем
        verifyPaymentLogos(onlineTopUpBlock);

        // Переход по ссылке «Подробнее о сервисе»
        clickMoreAboutServiceLink(onlineTopUpBlock);

        // Возвращаемся на предыдущую страницу
        driver.navigate().back();

        // Заполняем форму
        fillServiceForm();
    }

    private void verifyBlockTitle(WebElement block, String expectedTitle) {
        WebElement blockTitle = block.findElement(By.tagName("h2"));
        String actualTitle = blockTitle.getText().trim(); // Убираем лишние пробелы и переносы
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
    }

    private void verifyPaymentLogos(WebElement block) {
        List<WebElement> logos = block.findElements(By.cssSelector(".pay__partners li"));
        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены!");
        assertEquals(5, logos.size(), "Количество логотипов не соответствует ожидаемому!");
        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Логотип не отображается: " + logo.getAttribute("src"));
        }
    }

    private void clickMoreAboutServiceLink(WebElement block) {
        WebElement moreAboutServiceLink = block.findElement(By.linkText("Подробнее о сервисе"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", moreAboutServiceLink);

        // Проверяем, что URL изменился и соответствует ожидаемому
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String newUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, newUrl, "URL не соответствует ожидаемому после перехода по ссылке!");
    }

    private void fillServiceForm() {
        // Раскрываем выпадающее меню
        WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select__header")));
        dropdownToggle.click();
        // Выбираем вариант «Услуги связи»
        WebElement servicesOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select__option")));
        servicesOption.click();

        // Заполняем номер телефона
        WebElement phoneNumberInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
                phoneNumberInput.clear();
                phoneNumberInput.sendKeys("297777777");

        // Заполняем поле "Сумма"
        WebElement amountInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
                amountInput.clear();
                amountInput.sendKeys("100"); // Укажите нужную сумму

        // Нажимаем кнопку «Продолжить»
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'Продолжить')]")));
                continueButton.click();

        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[9]")));
        assertTrue(popupWindow.isDisplayed(), "Всплывающее окно не отображается!");

    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}