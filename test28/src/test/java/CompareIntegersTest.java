import org.example.CompareIntegers;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CompareIntegersTest {

    @Test
    public void testCompareGreater() {
        // Тестирование, когда первое число больше второго
        assertEquals(CompareIntegers.compare(5, 3), "5 больше, чем 3");
        assertEquals(CompareIntegers.compare(10, 7), "10 больше, чем 7");
    }

    @Test
    public void testCompareLess() {
        // Тестирование, когда первое число меньше второго
        assertEquals(CompareIntegers.compare(3, 5), "3 меньше, чем 5");
        assertEquals(CompareIntegers.compare(7, 10), "7 меньше, чем 10");
    }

    @Test
    public void testCompareEqual() {
        // Тестирование, когда числа равны
        assertEquals(CompareIntegers.compare(4, 4), "4 равно 4");
        assertEquals(CompareIntegers.compare(-1, -1), "-1 равно -1");
    }
}
