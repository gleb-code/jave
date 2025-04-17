import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.example.ArithmeticOperations;


public class ArithmeticOperationsTest {

    @Test
    public void testAdd() {
        // Проверка сложения
        assertEquals(5, ArithmeticOperations.add(2, 3), "2 + 3 должно быть 5");
        assertEquals(-1, ArithmeticOperations.add(2, -3), "2 + -3 должно быть -1");
        assertEquals(0, ArithmeticOperations.add(0, 0), "0 + 0 должно быть 0");
        assertEquals(10, ArithmeticOperations.add(7, 3), "7 + 3 должно быть 10");
    }

    @Test
    public void testSubtract() {
        // Проверка вычитания
        assertEquals(-1, ArithmeticOperations.subtract(2, 3), "2 - 3 должно быть -1");
        assertEquals(5, ArithmeticOperations.subtract(2, -3), "2 - -3 должно быть 5");
        assertEquals(0, ArithmeticOperations.subtract(0, 0), "0 - 0 должно быть 0");
        assertEquals(4, ArithmeticOperations.subtract(10, 6), "10 - 6 должно быть 4");
    }

    @Test
    public void testMultiply() {
        // Проверка умножения
        assertEquals(6, ArithmeticOperations.multiply(2, 3), "2 * 3 должно быть 6");
        assertEquals(-6, ArithmeticOperations.multiply(2, -3), "2 * -3 должно быть -6");
        assertEquals(0, ArithmeticOperations.multiply(0, 5), "0 * 5 должно быть 0");
        assertEquals(15, ArithmeticOperations.multiply(5, 3), "5 * 3 должно быть 15");
    }

    @Test
    public void testDivide() {
        // Проверка деления
        assertEquals(2.0, ArithmeticOperations.divide(6, 3), "6 / 3 должно быть 2.0");
        assertEquals(-2.0, ArithmeticOperations.divide(6, -3), "6 / -3 должно быть -2.0");
        assertEquals(0.0, ArithmeticOperations.divide(0, 5), "0 / 5 должно быть 0.0");

        // Проверка деления на ноль
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ArithmeticOperations.divide(1, 0);
        });
        assertEquals("Деление на ноль невозможно.", exception.getMessage());
    }
}
