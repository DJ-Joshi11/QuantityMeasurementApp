package TEST;

import MAIN.QuantityMeasurementAppMAIN.LengthUnit;
import MAIN.QuantityMeasurementAppMAIN.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    private static final double EPS = 1e-6;

    @Test
    void testTargetFeet() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.FEET
        );
        assertEquals(2.0, r.value, EPS);
    }

    @Test
    void testTargetInch() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.INCH
        );
        assertEquals(24.0, r.value, EPS);
    }

    @Test
    void testTargetYard() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD
        );
        assertEquals(0.666666, r.value, 1e-3);
    }

    @Test
    void testTargetCentimeter() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.INCH),
                LengthUnit.CENTIMETER
        );
        assertEquals(5.08, r.value, 1e-2);
    }

    @Test
    void testSameAsFirst() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARD
        );
        assertEquals(3.0, r.value, EPS);
    }

    @Test
    void testSameAsSecond() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.FEET
        );
        assertEquals(9.0, r.value, EPS);
    }

    @Test
    void testCommutativity() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength r1 = QuantityLength.add(a, b, LengthUnit.YARD);
        QuantityLength r2 = QuantityLength.add(b, a, LengthUnit.YARD);

        assertEquals(r1.value, r2.value, EPS);
    }

    @Test
    void testZero() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCH),
                LengthUnit.YARD
        );
        assertEquals(1.666666, r.value, 1e-3);
    }

    @Test
    void testNegative() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCH
        );
        assertEquals(36.0, r.value, EPS);
    }

    @Test
    void testNullTarget() {
        assertThrows(IllegalArgumentException.class,
                () -> QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCH),
                        null
                ));
    }

    @Test
    void testLargeToSmall() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(1000.0, LengthUnit.FEET),
                new QuantityLength(500.0, LengthUnit.FEET),
                LengthUnit.INCH
        );
        assertEquals(18000.0, r.value, EPS);
    }

    @Test
    void testSmallToLarge() {
        QuantityLength r = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD
        );
        assertEquals(0.666666, r.value, 1e-3);
    }
}