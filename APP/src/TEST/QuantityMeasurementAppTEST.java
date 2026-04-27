package TEST;

import MAIN.QuantityMeasurementAppMAIN.LengthUnit;
import MAIN.QuantityMeasurementAppMAIN.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.INCH)));
    }

    @Test
    void testEquality_FeetToInch_Equivalent() {
        assertTrue(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(12.0, LengthUnit.INCH)));
    }

    @Test
    void testEquality_InchToFeet_Equivalent() {
        assertTrue(new QuantityLength(12.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_Feet_Different() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET)
                .equals(new QuantityLength(2.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_Inch_Different() {
        assertFalse(new QuantityLength(1.0, LengthUnit.INCH)
                .equals(new QuantityLength(2.0, LengthUnit.INCH)));
    }

    @Test
    void testEquality_NullComparison() {
        assertFalse(new QuantityLength(1.0, LengthUnit.FEET).equals(null));
    }

    @Test
    void testEquality_InvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertTrue(q.equals(q));
    }

    @Test
    void testEquality_DifferentType() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        assertFalse(q.equals("test"));
    }
}