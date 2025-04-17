import org.example.TriangleAreaCalculator;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TriangleAreaCalculatorTest {

    @Test
    public void testCalculateAreaValidTriangle() {
        Assert.assertEquals(TriangleAreaCalculator.calculateArea(3, 4, 5), 6.0, 1e-9);
        Assert.assertEquals(TriangleAreaCalculator.calculateArea(5, 5, 5), 10.825317547305486, 1e-9); // равносторонний треугольник
        Assert.assertEquals(TriangleAreaCalculator.calculateArea(7, 8, 9), 26.832815729997478, 1e-9);
    }

    @Test
    public void testCalculateAreaInvalidTriangle() {
        try {
            TriangleAreaCalculator.calculateArea(1, 2, 3);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Стороны не могут образовать треугольник.");
        }

        try {
            TriangleAreaCalculator.calculateArea(-1, 2, 3);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Стороны не могут образовать треугольник.");
        }

        try {
            TriangleAreaCalculator.calculateArea(0, 2, 3);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Стороны не могут образовать треугольник.");
        }

        try {
            TriangleAreaCalculator.calculateArea(2, 2, 5);
            Assert.fail("Ожидалось исключение IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals(e.getMessage(), "Стороны не могут образовать треугольник.");
        }
    }
}
