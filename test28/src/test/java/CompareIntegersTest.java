import org.example.CompareIntegers;


import org.testng.Assert;
import org.testng.annotations.Test;

public class CompareIntegersTest {

    @Test
    public void testCompareGreater() {
        String result = CompareIntegers.compare(5, 3);
        Assert.assertEquals(result, "5 больше, чем 3");
    }

    @Test
    public void testCompareLesser() {
        String result = CompareIntegers.compare(2, 4);
        Assert.assertEquals(result, "2 меньше, чем 4");
    }

    @Test
    public void testCompareEqual() {
        String result = CompareIntegers.compare(7, 7);
        Assert.assertEquals(result, "7 равно 7");
    }

    @Test
    public void testCompareNegative() {
        String result = CompareIntegers.compare(-1, -5);
        Assert.assertEquals(result, "-1 больше, чем -5");
    }

    @Test
    public void testCompareWithZero() {
        String result1 = CompareIntegers.compare(0, 0);
        Assert.assertEquals(result1, "0 равно 0");

        String result2 = CompareIntegers.compare(0, 5);
        Assert.assertEquals(result2, "0 меньше, чем 5");

        String result3 = CompareIntegers.compare(5, 0);
        Assert.assertEquals(result3, "5 больше, чем 0");
    }
}
