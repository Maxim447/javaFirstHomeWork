
public class Complex {
    private double real;
    private double imag;

    /**
     * Конструктор для создания комплексного чискла с реальной и мнимой частью
     *
     * @param real (реальная часть)
     * @param imag (мнимая часть)
     */
    public Complex(double real, double imag) {
        this.real = real;
        this.imag = imag;
    }

    /**
     * Конструктор для создания комплексного чискла с реальной частью
     *
     * @param real (реаьная часть)
     */
    public Complex(double real) {
        this.real = real;
    }

    /**
     * Пустой конструктор
     */
    public Complex() {
    }

    /**
     * Метод для подсчета суммы
     *
     * @param value - (число которое прибовляем)
     * @return (измененное коплесное число)
     */
    public Complex sum(Complex value) {
        Complex a = this;
        a.real = a.real + value.real;
        a.imag = a.imag + value.imag;
        return a;
    }

    /**
     * Статический метод для подсчета суммы (он используется только в классе матриц)
     *
     * @param value1 (первое комплексное число)
     * @param value2 (второе комплексное число)
     * @return (сумма value1 и value2)
     */
    public static Complex sum(Complex value1, Complex value2) {
        return new Complex(value1.real + value2.real, value1.imag + value2.imag);
    }

    /**
     * Метод для подсчета разницы
     *
     * @param value (число которое вычитаем)
     * @return (измененное комплексное число)
     */
    public Complex subtract(Complex value) {
        Complex a = this;
        a.real = a.real - value.real;
        a.imag = a.imag - value.imag;
        return a;
    }

    /**
     * Статический метод для подсчета разницы (он используется только в классе матриц)
     *
     * @param value1 (первое комплексное число)
     * @param value2 (второе комплексное число)
     * @return (разница value1 и value2)
     */
    public static Complex subtract(Complex value1, Complex value2) {
        return new Complex(value1.real - value2.real, value1.imag - value2.imag);
    }

    /**
     * Метод для умножения на комплексное число
     *
     * @param value (комплексное число)
     * @return (измененное комлексное число)
     */
    public Complex multiply(Complex value) {
        Complex a = this;
        a.real = a.real * value.real - a.imag * value.imag;
        a.imag = a.real * value.imag + a.imag * value.real;
        return a;
    }

    /**
     * Статический метод для умножкния комлексных чиел (он используется только в классе матриц)
     *
     * @param value1 (первое комплексное число)
     * @param value2 (второе комплексное число)
     * @return (результат умножения value1 на value2)
     */
    public static Complex multiply(Complex value1, Complex value2) {
        return new Complex(value1.real * value2.real - value1.imag * value2.imag,
                value1.real * value2.imag + value1.imag * value2.real);
    }

    /**
     * Метод для деления на комплексное число
     *
     * @param value (комплексное число)
     * @return (измененное комлексное число)
     */
    public Complex divide(Complex value) {
        Complex a = this;
        Complex numerator = Complex.multiply(a, new Complex(value.real, -1 * value.imag));
        double denominator = Complex.multiply(value, new Complex(value.real, -1 * value.imag)).real;
        if (denominator == 0) {
            throw new ArithmeticException("division by zero");
        }
        a.real = numerator.real / denominator;
        a.imag = numerator.imag / denominator;
        return a;
    }

    /**
     * Статический метод для деления комлексных чиел (он используется только в классе матриц)
     *
     * @param value1 (первое комплексное число)
     * @param value2 (второе комплексное число)
     * @return (результат деления value1 на value2)
     */
    public static Complex divide(Complex value1, Complex value2) {
        Complex numerator = value1.multiply(new Complex(value2.real, -1 * value2.imag));
        double denominator = value2.multiply(new Complex(value2.real, -1 * value2.imag)).real;
        if (denominator == 0) {
            throw new ArithmeticException("division by zero");
        }
        return new Complex(numerator.real / denominator, numerator.imag / denominator);
    }

    /**
     * @return (возвращает абсолютное значение комлексного числа)
     */
    public double abs() {
        return Math.sqrt(Math.pow(this.real, 2) + Math.pow(this.imag, 2));
    }

    /**
     * @return (возвращает фазу комплексного числа)
     */
    public double phase() {
        return Math.atan2(this.imag, this.real);
    }

    /**
     * Метод для перевода представления комплексного числа в тригонометрической форме
     */
    public String toTrig() {
        Complex number = this;
        if (number.getImag() == 0) {
            return number.abs() + String.format(" * cos(%.2f)", number.phase());
        }
        return number.abs() + String.format(" * cos(%.2f)", number.phase()) + String.format(" + sin(%.2f) * ", number.phase()) + number.getImag() + "i";
    }

    public double getReal() {
        return real;
    }

    public double getImag() {
        return imag;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImag(double imag) {
        this.imag = imag;
    }

    /**
     * @return (превычное для глаз представление комплексного числа)
     */
    @Override
    public String toString() {
        if (imag == 0) {
            return real + "";
        }
        if (real == 0) {
            return imag + "i";
        }
        if (imag < 0) {
            return real + " - " + (-imag) + "i";
        }
        return real + " + " + imag + "i";
    }
}
