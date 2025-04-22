
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


import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Objects;

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
        testAcceptCookies(); // Принимаем куки перед каждым тестом
    }

    @Test
    public void testBlockTitle() {
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        WebElement onlineTopUpBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pay-section")));
        assertTrue(onlineTopUpBlock.isDisplayed(), "Блок 'Онлайн пополнение без комиссии' не отображается");
        verifyBlockTitle(onlineTopUpBlock, expectedTitle);
    }

    @Test
    public void testPaymentLogos() {
        WebElement onlineTopUpBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pay-section")));
        verifyPaymentLogos(onlineTopUpBlock);
    }

    @Test
    public void testMoreAboutServiceLink() {
        WebElement onlineTopUpBlock = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pay-section")));
        clickMoreAboutServiceLink(onlineTopUpBlock);
        driver.navigate().back(); // Возвращаемся назад после проверки
    }

    @Test
    public void testFillServiceForm() {
        fillServiceForm();
    }

    private void testAcceptCookies() {
        try {
            WebElement cookieCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".cookie__cancel")));
            cookieCloseButton.click();
        } catch (Exception e) {
            // Игнорируем, если кнопка не найдена
        }
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

        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String newUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, newUrl, "URL не соответствует ожидаемому после перехода по ссылке!");
    }

    private void fillServiceForm() {
        WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".select__header")));
        dropdownToggle.click();

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


        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
        for (WebElement iframe : iframes) {
            if (Objects.equals(iframe.getAttribute("src"), "https://checkout.bepaid.by/widget_v2/index.html")) { // Замените на ваш src
                driver.switchTo().frame(iframe); // Переключаемся на нужный iframe
                break; // Выходим из цикла, если нашли нужный iframe
            }
        }
        WebElement popupWindow = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[9]")));
        assertTrue(popupWindow.isDisplayed(), "Всплывающее окно не отображается!");
    }
}