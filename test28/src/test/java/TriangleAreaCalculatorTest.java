import static org.junit.jupiter.api.Assertions.*;

import org.example.TriangleAreaCalculator;

import org.junit.jupiter.api.Test;

public class TriangleAreaCalculatorTest {

    @Test
    public void testAreaOfValidTriangle() {
        assertEquals(6.0, TriangleAreaCalculator.calculateArea(3, 4, 5), 0.0001);
        assertEquals(14.6969, TriangleAreaCalculator.calculateArea(5, 6, 7), 0.0001); // Исправлено
        assertEquals(24.0, TriangleAreaCalculator.calculateArea(6, 8, 10), 0.0001); // Исправлено
    }

    @Test
    public void testAreaOfTriangleWithInvalidSides() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(1, 1, 3);
        });
        assertEquals("Стороны не могут образовать треугольник.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(0, 1, 1);
        });
        assertEquals("Стороны не могут образовать треугольник.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            TriangleAreaCalculator.calculateArea(-1, 1, 1);
        });
        assertEquals("Стороны не могут образовать треугольник.", exception.getMessage());
    }

    @Test
    public void testAreaOfTriangleWithEqualSides() {
        assertEquals(3.897114317, TriangleAreaCalculator.calculateArea(3, 3, 3), 0.0001); // Равносторонний треугольник
    }
}
