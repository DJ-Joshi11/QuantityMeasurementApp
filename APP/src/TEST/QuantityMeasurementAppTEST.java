package TEST;

import MAIN.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testConvertToBaseUnit() {
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0));
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0));
    }

    @Test
    void testConvertFromBaseUnit() {
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0));
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0));
    }

    @Test
    void testEqualityCrossUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);
        assertTrue(a.equals(b));
    }

    @Test
    void testConvertTo() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = a.convertTo(LengthUnit.INCHES);
        assertTrue(result.equals(new QuantityLength(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testAdditionWithTargetUnit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = a.add(b, LengthUnit.FEET);
        assertTrue(result.equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityLength(Double.NaN, LengthUnit.FEET));
    }
}