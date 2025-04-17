
import org.example.FactorialCalculator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialZero() {
        Assert.assertEquals(FactorialCalculator.factorial(0), 1);
    }

    @Test
    public void testFactorialOne() {
        Assert.assertEquals(FactorialCalculator.factorial(1), 1);
    }

    @Test
    public void testFactorialTwo() {
        Assert.assertEquals(FactorialCalculator.factorial(2), 2);
    }

    @Test
    public void testFactorialThree() {
        Assert.assertEquals(FactorialCalculator.factorial(3), 6);
    }

    @Test
    public void testFactorialFour() {
        Assert.assertEquals(FactorialCalculator.factorial(4), 24);
    }

    @Test
    public void testFactorialFive() {
        Assert.assertEquals(FactorialCalculator.factorial(5), 120);
    }

    @Test
    public void testFactorialNegative() {
        try {
            FactorialCalculator.factorial(-1);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Факториал не определен для отрицательных чисел");
        }
    }
}
