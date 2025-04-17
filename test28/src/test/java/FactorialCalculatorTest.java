import org.testng.Assert;
import org.testng.annotations.Test;
import org.example.FactorialCalculator;

public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        Assert.assertEquals(FactorialCalculator.factorial(0), 1);
    }

    @Test
    public void testFactorialOfOne() {
        Assert.assertEquals(FactorialCalculator.factorial(1), 1);
    }

    @Test
    public void testFactorialOfTwo() {
        Assert.assertEquals(FactorialCalculator.factorial(2), 2);
    }

    @Test
    public void testFactorialOfThree() {
        Assert.assertEquals(FactorialCalculator.factorial(3), 6);
    }

    @Test
    public void testFactorialOfFive() {
        Assert.assertEquals(FactorialCalculator.factorial(5), 120);
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        Exception exception = Assert.expectThrows(IllegalArgumentException.class, () -> {
            FactorialCalculator.factorial(-1);
        });
        Assert.assertEquals(exception.getMessage(), "Факториал не определен для отрицательных чисел");
    }
}
