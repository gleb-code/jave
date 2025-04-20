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

import java.sql.Wrapper;
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
    public void testMoreAboutServiceLink() {
        // Закрываем всплывающее окно с куками, если оно есть
        try {
            WebElement cookieCloseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.layout.layout--default > main > div > div.cookie > div > div.cookie__buttons > button.btn.btn_gray.cookie__cancel")));
            cookieCloseButton.click();
        } catch (Exception e) {
            // Если кнопка закрытия не найдена, ничего не делаем
        }




        // Находим блок "Онлайн пополнение без комиссии"
        WebElement onlineTopUpBlock = driver.findElement(By.id("pay-section"));

        // Проверяем, что блок отображается на странице
        assertTrue(onlineTopUpBlock.isDisplayed(), "Блок 'Онлайн пополнение без комиссии' не отображается");

        // Проверяем название блока
        WebElement blockTitle = onlineTopUpBlock.findElement(By.tagName("h2"));
        String expectedTitle = "Онлайн пополнение\nбез комиссии";
        String actualTitle = blockTitle.getText().trim(); // Убираем лишние пробелы и переносы
        assertEquals(expectedTitle, actualTitle, "Название блока не соответствует ожидаемому");
        // Находим логотипы платёжных систем внутри блока
        List<WebElement> logos = onlineTopUpBlock.findElements(By.cssSelector(".homepage .pay__partners li"));


        // Проверяем, что логотипы присутствуют
        assertFalse(logos.isEmpty(), "Логотипы платёжных систем не найдены!");
        // Проверяем, что количество логотипов равно 5
        assertEquals(5, logos.size(), "Количество логотипов не соответствует ожидаемому!");

        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Логотип не отображается: " + logo.getAttribute("src"));
        }





        // Находим ссылку «Подробнее о сервисе»
        WebElement moreAboutServiceLink = onlineTopUpBlock.findElement(By.linkText("Подробнее о сервисе")); //

        // Сохраняем текущий URL перед переходом по ссылке
        String currentUrl = driver.getCurrentUrl();

        // Используем JavaScript для клика по ссылке
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", moreAboutServiceLink);



        // Проверяем, что URL изменился и соответствует ожидаемому
        String expectedUrl = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String newUrl = driver.getCurrentUrl();
        assertEquals(expectedUrl, newUrl, "URL не соответствует ожидаемому после перехода по ссылке!");

        // Вернуться на предыдущую страницу, если необходимо
        driver.navigate().back();

        WebElement dropdownToggle = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button")));
        dropdownToggle.click();
        // Выбираем вариант «Услуги связи»
        WebElement servicesOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/div[1]/div[1]/div[2]/ul/li[1]")));
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
