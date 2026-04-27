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

        public QuantityLength convertTo(LengthUnit target) {
            if (target == null) throw new IllegalArgumentException();
            double base = toFeet();
            double converted = target.fromBase(base);
            return new QuantityLength(converted, target);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (source == null || target == null || !Double.isFinite(value)) throw new IllegalArgumentException();
            double base = source.toBase(value);
            return target.fromBase(base);
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

    public static double demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        return QuantityLength.convert(value, from, to);
    }

    public static double demonstrateLengthConversion(QuantityLength q, LengthUnit to) {
        return q.convertTo(to).value;
    }

    public static boolean demonstrateLengthEquality(QuantityLength a, QuantityLength b) {
        return a.equals(b);
    }

    public static void main(String[] args) {
        System.out.println(QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCH));
        System.out.println(QuantityLength.convert(3.0, LengthUnit.YARD, LengthUnit.FEET));
    }
}