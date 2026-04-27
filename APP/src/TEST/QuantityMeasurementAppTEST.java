package TEST;

import MAIN.QuantityMeasurementAppMAIN.LengthUnit;
import MAIN.QuantityMeasurementAppMAIN.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testYard_SameValue() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(1.0, LengthUnit.YARD)));
    }

    @Test
    void testYard_DifferentValue() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(2.0, LengthUnit.YARD)));
    }

    @Test
    void testYard_ToFeet() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(3.0, LengthUnit.FEET)));
    }

    @Test
    void testFeet_ToYard() {
        assertTrue(new QuantityLength(3.0, LengthUnit.FEET)
                .equals(new QuantityLength(1.0, LengthUnit.YARD)));
    }

    @Test
    void testYard_ToInch() {
        assertTrue(new QuantityLength(1.0, LengthUnit.YARD)
                .equals(new QuantityLength(36.0, LengthUnit.INCH)));
    }

    @Test
    void testInch_ToYard() {
        assertTrue(new QuantityLength(36.0, LengthUnit.INCH)
                .equals(new QuantityLength(1.0, LengthUnit.YARD)));
    }

    @Test
    void testCentimeter_ToInch() {
        assertTrue(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(0.393701, LengthUnit.INCH)));
    }

    @Test
    void testCentimeter_ToFeet_NotEqual() {
        assertFalse(new QuantityLength(1.0, LengthUnit.CENTIMETER)
                .equals(new QuantityLength(1.0, LengthUnit.FEET)));
    }

    @Test
    void testTransitive() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(36.0, LengthUnit.INCH);
        assertTrue(a.equals(b) && b.equals(c) && a.equals(c));
    }

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityLength(1.0, null));
    }

    @Test
    void testSameReference() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.YARD);
        assertTrue(q.equals(q));
    }

    @Test
    void testNullComparison() {
        assertFalse(new QuantityLength(1.0, LengthUnit.YARD).equals(null));
    }

    @Test
    void testComplexAllUnits() {
        QuantityLength a = new QuantityLength(2.0, LengthUnit.YARD);
        QuantityLength b = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength c = new QuantityLength(72.0, LengthUnit.INCH);
        assertTrue(a.equals(b) && b.equals(c) && a.equals(c));
    }
}