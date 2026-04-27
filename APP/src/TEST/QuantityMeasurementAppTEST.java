package TEST;

import MAIN.QuantityMeasurementAppMAIN.LengthUnit;
import MAIN.QuantityMeasurementAppMAIN.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    private static final double EPS = 1e-6;

    @Test
    void testFeetToInches() {
        assertEquals(12.0, QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH), EPS);
    }

    @Test
    void testInchesToFeet() {
        assertEquals(2.0, QuantityLength.convert(24.0, LengthUnit.INCH, LengthUnit.FEET), EPS);
    }

    @Test
    void testYardsToInches() {
        assertEquals(36.0, QuantityLength.convert(1.0, LengthUnit.YARD, LengthUnit.INCH), EPS);
    }

    @Test
    void testInchesToYards() {
        assertEquals(2.0, QuantityLength.convert(72.0, LengthUnit.INCH, LengthUnit.YARD), EPS);
    }

    @Test
    void testCentimeterToInch() {
        assertEquals(1.0, QuantityLength.convert(2.54, LengthUnit.CENTIMETER, LengthUnit.INCH), 1e-3);
    }

    @Test
    void testFeetToYard() {
        assertEquals(2.0, QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARD), EPS);
    }

    @Test
    void testRoundTrip() {
        double v = 5.5;
        double result = QuantityLength.convert(
                QuantityLength.convert(v, LengthUnit.FEET, LengthUnit.INCH),
                LengthUnit.INCH,
                LengthUnit.FEET
        );
        assertEquals(v, result, EPS);
    }

    @Test
    void testZero() {
        assertEquals(0.0, QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCH), EPS);
    }

    @Test
    void testNegative() {
        assertEquals(-12.0, QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCH), EPS);
    }

    @Test
    void testSameUnit() {
        assertEquals(5.0, QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET), EPS);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(1.0, null, LengthUnit.FEET));
    }

    @Test
    void testNaNOrInfinite() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCH));

        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCH));
    }
}