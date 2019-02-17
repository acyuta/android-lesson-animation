package tsu.android.animated.formulas;

/**
 * Линейная формула y = ax + b
 */

public class LinearFormula implements Formula {

    final float a, b;

    /**
     * Инициализация y = x
     */
    public LinearFormula() {
        this.a = 1;
        this.b = 0;
    }

    public LinearFormula(float a, float b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public String name() {
        return "Линейная";
    }

    @Override
    public float calculate(float value) {
        return value;
    }
}
