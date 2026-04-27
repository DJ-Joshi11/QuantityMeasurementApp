package MAIN;

interface IMeasurable {
    double getConversionFactor();
    double convertToBaseUnit(double value);
    double convertFromBaseUnit(double baseValue);
    String getUnitName();
}

enum LengthUnit implements IMeasurable {
    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double factor;

    LengthUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(1.0 / 1000.0),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() {
        return factor;
    }

    public double convertToBaseUnit(double value) {
        return value * factor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / factor;
    }

    public String getUnitName() {
        return name();
    }
}

class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    Quantity<U> convertTo(U target) {
        if (target == null) throw new IllegalArgumentException();
        double base = unit.convertToBaseUnit(value);
        double converted = target.convertFromBaseUnit(base);
        return new Quantity<>(round(converted), target);
    }

    Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    Quantity<U> add(Quantity<U> other, U target) {
        if (other == null || target == null) throw new IllegalArgumentException();
        validateCategory(other);
        double sum = unit.convertToBaseUnit(value) + other.unit.convertToBaseUnit(other.value);
        double result = target.convertFromBaseUnit(sum);
        return new Quantity<>(round(result), target);
    }

    Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    Quantity<U> subtract(Quantity<U> other, U target) {
        if (other == null || target == null) throw new IllegalArgumentException();
        validateCategory(other);
        double diff = unit.convertToBaseUnit(value) - other.unit.convertToBaseUnit(other.value);
        double result = target.convertFromBaseUnit(diff);
        return new Quantity<>(round(result), target);
    }

    double divide(Quantity<U> other) {
        if (other == null) throw new IllegalArgumentException();
        validateCategory(other);
        double baseOther = other.unit.convertToBaseUnit(other.value);
        if (baseOther == 0.0) throw new ArithmeticException();
        double baseThis = unit.convertToBaseUnit(value);
        return baseThis / baseOther;
    }

    private void validateCategory(Quantity<?> other) {
        if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException();
    }

    private double round(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Quantity<?> other = (Quantity<?>) obj;
        if (!unit.getClass().equals(other.unit.getClass())) return false;
        double base1 = unit.convertToBaseUnit(value);
        double base2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);
        return Double.compare(base1, base2) == 0;
    }

    @Override
    public int hashCode() {
        double base = unit.convertToBaseUnit(value);
        return Double.hashCode(base);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}

public class QuantityMeasurementAppMAIN {
    public static void main(String[] args) {
        Quantity<LengthUnit> l1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> l2 = new Quantity<>(6.0, LengthUnit.INCHES);
        System.out.println(l1.subtract(l2));
        System.out.println(l1.subtract(l2, LengthUnit.INCHES));
        System.out.println(l1.divide(new Quantity<>(2.0, LengthUnit.FEET)));
    }
}