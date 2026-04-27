package TEST;

import MAIN.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testVolumeEquality_LitreToMillilitre() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertTrue(a.equals(b));
    }

    @Test
    void testVolumeEquality_LitreToGallon() {
        Quantity<VolumeUnit> a = new Quantity<>(3.78541, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(1.0, VolumeUnit.GALLON);
        assertTrue(a.equals(b));
    }

    @Test
    void testVolumeConversion() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> result = a.convertTo(VolumeUnit.MILLILITRE);
        assertTrue(result.equals(new Quantity<>(1000.0, VolumeUnit.MILLILITRE)));
    }

    @Test
    void testVolumeAddition() {
        Quantity<VolumeUnit> a = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(500.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = a.add(b, VolumeUnit.LITRE);
        assertTrue(result.equals(new Quantity<>(1.5, VolumeUnit.LITRE)));
    }

    @Test
    void testVolumeNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(1.0, null));
    }

    @Test
    void testVolumeInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new Quantity<>(Double.NaN, VolumeUnit.LITRE));
    }

    @Test
    void testCrossCategorySafety() {
        Quantity<VolumeUnit> v = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> l = new Quantity<>(1.0, LengthUnit.FEET);
        assertFalse(v.equals(l));
    }
}