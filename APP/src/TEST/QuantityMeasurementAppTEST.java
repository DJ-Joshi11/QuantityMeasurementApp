package TEST;

import MAIN.QuantityMeasurementAppMAIN.Feet;
import MAIN.QuantityMeasurementAppMAIN.Inches;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testFeetEquality_SameValue() {
        assertTrue(new Feet(1.0).equals(new Feet(1.0)));
    }

    @Test
    void testFeetEquality_DifferentValue() {
        assertFalse(new Feet(1.0).equals(new Feet(2.0)));
    }

    @Test
    void testFeetEquality_NullComparison() {
        assertFalse(new Feet(1.0).equals(null));
    }

    @Test
    void testFeetEquality_NonNumericInput() {
        assertFalse(new Feet(1.0).equals("test"));
    }

    @Test
    void testFeetEquality_SameReference() {
        Feet f = new Feet(1.0);
        assertTrue(f.equals(f));
    }

    @Test
    void testInchesEquality_SameValue() {
        assertTrue(new Inches(1.0).equals(new Inches(1.0)));
    }

    @Test
    void testInchesEquality_DifferentValue() {
        assertFalse(new Inches(1.0).equals(new Inches(2.0)));
    }

    @Test
    void testInchesEquality_NullComparison() {
        assertFalse(new Inches(1.0).equals(null));
    }

    @Test
    void testInchesEquality_NonNumericInput() {
        assertFalse(new Inches(1.0).equals("test"));
    }

    @Test
    void testInchesEquality_SameReference() {
        Inches i = new Inches(1.0);
        assertTrue(i.equals(i));
    }
}