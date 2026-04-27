package MAIN;

enum LengthUnit {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    double convertToBaseUnit(double value) {
        return value * factor;
    }

    double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}

class QuantityLength {
    private final double value;
    private final LengthUnit unit;

    QuantityLength(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    QuantityLength convertTo(LengthUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new QuantityLength(converted, target);
    }

    QuantityLength add(QuantityLength other, LengthUnit target) {
        if (other == null || target == null) throw new IllegalArgumentException();
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sum = base1 + base2;
        double result = target.convertFromBaseUnit(sum);
        return new QuantityLength(result, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityLength other = (QuantityLength) obj;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Double.compare(base1, base2) == 0;
    }
}

enum WeightUnit {
    KILOGRAM(1.0),
    GRAM(1.0 / 1000.0),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    double convertToBaseUnit(double value) {
        return value * factor;
    }

    double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }
}

class QuantityWeight {
    private final double value;
    private final WeightUnit unit;

    QuantityWeight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    QuantityWeight convertTo(WeightUnit target) {
        if (target == null) throw new IllegalArgumentException();
        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new QuantityWeight(converted, target);
    }

    QuantityWeight add(QuantityWeight other, WeightUnit target) {
        if (other == null || target == null) throw new IllegalArgumentException();
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        double sum = base1 + base2;
        double result = target.convertFromBaseUnit(sum);
        return new QuantityWeight(result, target);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        QuantityWeight other = (QuantityWeight) obj;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return Double.compare(base1, base2) == 0;
    }
}

public class QuantityMeasurementAppMAIN {
    public static void main(String[] args) {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        System.out.println(w1.equals(w2));
        System.out.println(w1.convertTo(WeightUnit.GRAM));
        System.out.println(w1.add(w2, WeightUnit.KILOGRAM));
    }
}