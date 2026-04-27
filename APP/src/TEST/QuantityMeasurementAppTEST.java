package TEST;

import MAIN.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testLengthEquality_Generic() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    void testWeightEquality_Generic() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(1000.0, WeightUnit.GRAM);
        assertTrue(a.equals(b));
    }

    @Test
    void testConversion_Generic() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> result = a.convertTo(LengthUnit.INCHES);
        assertTrue(result.equals(new Quantity<>(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testAddition_Generic() {
        Quantity<WeightUnit> a = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> b = new Quantity<>(500.0, WeightUnit.GRAM);
        Quantity<WeightUnit> result = a.add(b, WeightUnit.KILOGRAM);
        assertTrue(result.equals(new Quantity<>(1.5, WeightUnit.KILOGRAM)));
    }

    @Test
    void testCrossCategoryComparison() {
        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<WeightUnit> b = new Quantity<>(1.0, WeightUnit.KILOGRAM);
        assertFalse(a.equals(b));
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }
}