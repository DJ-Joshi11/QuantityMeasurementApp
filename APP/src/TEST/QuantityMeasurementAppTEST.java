package TEST;

import MAIN.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testSubtraction_SameUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);
        assertTrue(a.subtract(b).equals(new Quantity<>(5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_CrossUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);
        assertTrue(a.subtract(b).equals(new Quantity<>(9.5, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_ExplicitTarget() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);
        assertTrue(a.subtract(b, LengthUnit.INCHES).equals(new Quantity<>(114.0, LengthUnit.INCHES)));
    }

    @Test
    void testSubtraction_Negative() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);
        assertTrue(a.subtract(b).equals(new Quantity<>(-5.0, LengthUnit.FEET)));
    }

    @Test
    void testSubtraction_Zero() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(120.0, LengthUnit.INCHES);
        assertTrue(a.subtract(b).equals(new Quantity<>(0.0, LengthUnit.FEET)));
    }

    @Test
    void testDivision_SameUnit() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(5.0, a.divide(b));
    }

    @Test
    void testDivision_CrossUnit() {
        Quantity<LengthUnit> a = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        assertEquals(1.0, a.divide(b));
    }

    @Test
    void testDivision_LessThanOne() {
        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);
        assertEquals(0.5, a.divide(b));
    }

    @Test
    void testDivision_ByZero() {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.FEET);
        assertThrows(ArithmeticException.class, () -> a.divide(b));
    }

    @Test
    void testCrossCategory_Subtraction() {
        Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> l.subtract((Quantity) w));
    }

    @Test
    void testCrossCategory_Division() {
        Quantity<LengthUnit> l = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> w = new Quantity<>(5.0, WeightUnit.KILOGRAM);
        assertThrows(IllegalArgumentException.class, () -> l.divide((Quantity) w));
    }
}