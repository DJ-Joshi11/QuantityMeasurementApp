package MAIN;

import java.util.function.DoubleBinaryOperator;

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

    public double getConversionFactor() { return factor; }
    public double convertToBaseUnit(double value) { return value * factor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
    public String getUnitName() { return name(); }
}

enum WeightUnit implements IMeasurable {
    KILOGRAM(1.0),
    GRAM(1.0 / 1000.0),
    POUND(0.453592);

    private final double factor;

    WeightUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }
    public double convertToBaseUnit(double value) { return value * factor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
    public String getUnitName() { return name(); }
}

enum VolumeUnit implements IMeasurable {
    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double factor;

    VolumeUnit(double factor) {
        this.factor = factor;
    }

    public double getConversionFactor() { return factor; }
    public double convertToBaseUnit(double value) { return value * factor; }
    public double convertFromBaseUnit(double baseValue) { return baseValue / factor; }
    public String getUnitName() { return name(); }
}

class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
        this.value = value;
        this.unit = unit;
    }

    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) throw new ArithmeticException();
            return a / b;
        });

        private final DoubleBinaryOperator op;

        ArithmeticOperation(DoubleBinaryOperator op) {
            this.op = op;
        }

        double compute(double a, double b) {
            return op.applyAsDouble(a, b);
        }
    }

    private void validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetRequired) {
        if (other == null) throw new IllegalArgumentException();
        if (!unit.getClass().equals(other.unit.getClass())) throw new IllegalArgumentException();
        if (!Double.isFinite(value) || !Double.isFinite(other.value)) throw new IllegalArgumentException();
        if (targetRequired && targetUnit == null) throw new IllegalArgumentException();
    }

    private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {
        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);
        return operation.compute(base1, base2);
    }

    Quantity<U> convertTo(U target) {
        if (target == null) throw new IllegalArgumentException();
        double base = unit.convertToBaseUnit(value);
        return new Quantity<>(round(target.convertFromBaseUnit(base)), target);
    }

    Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    Quantity<U> add(Quantity<U> other, U target) {
        validateArithmeticOperands(other, target, true);
        double base = performBaseArithmetic(other, ArithmeticOperation.ADD);
        return new Quantity<>(round(target.convertFromBaseUnit(base)), target);
    }

    Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    Quantity<U> subtract(Quantity<U> other, U target) {
        validateArithmeticOperands(other, target, true);
        double base = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        return new Quantity<>(round(target.convertFromBaseUnit(base)), target);
    }

    double divide(Quantity<U> other) {
        validateArithmeticOperands(other, null, false);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
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
        return Double.hashCode(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}

public class QuantityMeasurementAppMAIN {
    public static void main(String[] args) {
        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(6.0, LengthUnit.INCHES);

        System.out.println(a.add(b));
        System.out.println(a.subtract(b));
        System.out.println(a.divide(new Quantity<>(2.0, LengthUnit.FEET)));
    }
}