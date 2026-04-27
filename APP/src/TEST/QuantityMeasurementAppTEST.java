package TEST;

import MAIN.QuantityMeasurementAppMAIN.LengthUnit;
import MAIN.QuantityMeasurementAppMAIN.QuantityLength;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    private static final double EPS = 1e-6;

    @Test
    void testFeetPlusFeet() {
        QuantityLength r = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(2.0, LengthUnit.FEET));
        assertEquals(3.0, r.convertTo(LengthUnit.FEET).value, EPS);
    }

    @Test
    void testInchPlusInch() {
        QuantityLength r = new QuantityLength(6.0, LengthUnit.INCH)
                .add(new QuantityLength(6.0, LengthUnit.INCH));
        assertEquals(12.0, r.convertTo(LengthUnit.INCH).value, EPS);
    }

    @Test
    void testFeetPlusInch() {
        QuantityLength r = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCH));
        assertEquals(2.0, r.convertTo(LengthUnit.FEET).value, EPS);
    }

    @Test
    void testInchPlusFeet() {
        QuantityLength r = new QuantityLength(12.0, LengthUnit.INCH)
                .add(new QuantityLength(1.0, LengthUnit.FEET));
        assertEquals(24.0, r.convertTo(LengthUnit.INCH).value, EPS);
    }

    @Test
    void testYardPlusFeet() {
        QuantityLength r = new QuantityLength(1.0, LengthUnit.YARD)
                .add(new QuantityLength(3.0, LengthUnit.FEET));
        assertEquals(2.0, r.convertTo(LengthUnit.YARD).value, EPS);
    }

    @Test
    void testCentimeterPlusInch() {
        QuantityLength r = new QuantityLength(2.54, LengthUnit.CENTIMETER)
                .add(new QuantityLength(1.0, LengthUnit.INCH));
        assertEquals(5.08, r.convertTo(LengthUnit.CENTIMETER).value, 1e-2);
    }

    @Test
    void testCommutative() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);

        QuantityLength r1 = QuantityLength.add(a, b, LengthUnit.FEET);
        QuantityLength r2 = QuantityLength.add(b, a, LengthUnit.FEET);

        assertEquals(r1.convertTo(LengthUnit.FEET).value,
                r2.convertTo(LengthUnit.FEET).value, EPS);
    }

    @Test
    void testWithZero() {
        QuantityLength r = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(0.0, LengthUnit.INCH));
        assertEquals(5.0, r.convertTo(LengthUnit.FEET).value, EPS);
    }

    @Test
    void testNegative() {
        QuantityLength r = new QuantityLength(5.0, LengthUnit.FEET)
                .add(new QuantityLength(-2.0, LengthUnit.FEET));
        assertEquals(3.0, r.convertTo(LengthUnit.FEET).value, EPS);
    }

    @Test
    void testNullOperand() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void testLargeValues() {
        QuantityLength r = new QuantityLength(1e6, LengthUnit.FEET)
                .add(new QuantityLength(1e6, LengthUnit.FEET));
        assertEquals(2e6, r.convertTo(LengthUnit.FEET).value, EPS);
    }

    @Test
    void testSmallValues() {
        QuantityLength r = new QuantityLength(0.001, LengthUnit.FEET)
                .add(new QuantityLength(0.002, LengthUnit.FEET));
        assertEquals(0.003, r.convertTo(LengthUnit.FEET).value, 1e-9);
    }
}