package tsu.android.animated.formulas;

/**
 * Интерфейс для различных вычислений значений анимации
 */
public interface Formula {
    /**
     * Название формулы
     * @return строку с именем
     */
    String name();

    /**
     * Значение формулы при входных данных
     * @param value дробное число входного аргумента
     * @return результат вычисления формулы
     */
    float calculate(float value);
}
