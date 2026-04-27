package TEST;

import MAIN.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testAdd() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(a.add(b).equals(new Quantity<>(2.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtract() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);
        assertTrue(a.subtract(b).equals(new Quantity<>(9.5, LengthUnit.FEET)));
    }

    @Test
    void testDivide() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, a.divide(b));
    }

    @Test
    void testCrossCategory() {
        Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> l.add((Quantity) w));
    }

    @Test
    void testNullOperand() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void testDivisionByZero() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> a.divide(b));
    }
}