package MAIN;

public class QuantityMeasurementAppMAIN {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

        private final double toFeet;

        LengthUnit(double toFeet) {
            this.toFeet = toFeet;
        }

        public double toBase(double value) {
            return value * toFeet;
        }

        public double fromBase(double value) {
            return value / toFeet;
        }
    }

    public static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {
            if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            this.value = value;
            this.unit = unit;
        }

        private double toFeet() {
            return unit.toBase(value);
        }

        private static QuantityLength addInternal(QuantityLength a, QuantityLength b, LengthUnit target) {
            double sumBase = a.toFeet() + b.toFeet();
            double result = target.fromBase(sumBase);
            return new QuantityLength(result, target);
        }

        public QuantityLength add(QuantityLength other) {
            if (other == null) throw new IllegalArgumentException();
            return addInternal(this, other, this.unit);
        }

        public static QuantityLength add(QuantityLength a, QuantityLength b, LengthUnit target) {
            if (a == null || b == null || target == null) throw new IllegalArgumentException();
            return addInternal(a, b, target);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return value + " " + unit;
        }
    }

    public static void main(String[] args) {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCH);
        System.out.println(QuantityLength.add(a, b, LengthUnit.YARD));
    }
}