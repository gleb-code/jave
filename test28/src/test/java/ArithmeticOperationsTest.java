

import org.example.ArithmeticOperations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ArithmeticOperationsTest {
    @BeforeClass
    public void setUp() {
        // Здесь можно выполнить инициализацию, если это необходимо
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(ArithmeticOperations.add(2, 3), 5);
        Assert.assertEquals(ArithmeticOperations.add(-1, 1), 0);
        Assert.assertEquals(ArithmeticOperations.add(-1, -1), -2);
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(ArithmeticOperations.subtract(5, 3), 2);
        Assert.assertEquals(ArithmeticOperations.subtract(0, 1), -1);
        Assert.assertEquals(ArithmeticOperations.subtract(-1, -1), 0);
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(ArithmeticOperations.multiply(2, 3), 6);
        Assert.assertEquals(ArithmeticOperations.multiply(-1, 1), -1);
        Assert.assertEquals(ArithmeticOperations.multiply(-1, -1), 1);
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(ArithmeticOperations.divide(6, 3), 2.0);
        Assert.assertEquals(ArithmeticOperations.divide(5, 2), 2.5);

        // Проверка на исключение при делении на ноль
        try {
            ArithmeticOperations.divide(1, 0);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Деление на ноль невозможно.");
        }
    }
}
