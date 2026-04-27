package TEST;

import MAIN.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTEST {

    @Test
    void testWeightEquality_KgToGram() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertTrue(a.equals(b));
    }

    @Test
    void testWeightEquality_KgToPound() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(2.20462, WeightUnit.POUND);
        assertTrue(a.equals(b));
    }

    @Test
    void testWeightConversion() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight result = a.convertTo(WeightUnit.GRAM);
        assertTrue(result.equals(new QuantityWeight(1000.0, WeightUnit.GRAM)));
    }

    @Test
    void testWeightAddition() {
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(500.0, WeightUnit.GRAM);
        QuantityWeight result = a.add(b, WeightUnit.KILOGRAM);
        assertTrue(result.equals(new QuantityWeight(1.5, WeightUnit.KILOGRAM)));
    }

    @Test
    void testWeightNullUnit() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testWeightInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }
}