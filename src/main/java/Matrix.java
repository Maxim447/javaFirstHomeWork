
public class Matrix {
    private final int row;
    private final int col;
    private final Complex[][] matrix;


    /**
     * Конструктор для создания объекта Matrix
     *
     * @param row (количество строк матрицы)
     * @param col (количестов столбцов матрицы)
     */
    public Matrix(int row, int col) {
        this.row = row;
        this.col = col;
        matrix = new Complex[row][col];
    }

    public void setMatrix(Complex[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            System.arraycopy(matrix[i], 0, this.matrix[i], 0, matrix[i].length);
        }
    }

    public void setMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                this.matrix[i][j] = new Complex(matrix[i][j]);
            }
        }
    }

    public void setMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                this.matrix[i][j] = new Complex(matrix[i][j]);
            }
        }
    }

    /**
     * метод для суммирования матриц
     *
     * @param matrix (матрица комплексных чисел)
     */
    public void sum(Complex[][] matrix) throws ArrayIndexOutOfBoundsException {
        if (row == matrix.length && col == matrix[0].length){
            Complex[][] resultMatrix = new Complex[row][col];
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[i].length; ++j) {
                    resultMatrix[i][j] = Complex.sum(this.matrix[i][j], matrix[i][j]);
                }
            }
            setMatrix(resultMatrix);
            System.out.println(this);
        }
        throw new RuntimeException("Матрицы должны быть одинакового размера!");
    }

    public void subtract(Complex[][] matrix) throws ArrayIndexOutOfBoundsException {
        if (row == matrix.length && col == matrix[0].length){
            Complex[][] resultMatrix = new Complex[row][col];
            for (int i = 0; i < matrix.length; ++i) {
                for (int j = 0; j < matrix[i].length; ++j) {
                    resultMatrix[i][j] = Complex.subtract(this.matrix[i][j], matrix[i][j]);
                }
            }
            setMatrix(resultMatrix);
            System.out.println(this);
        }
        throw new RuntimeException("Матрицы должны быть одинакового размера!");
    }
    /**
     * Метод для умножения матриц
     *
     * @param matrix (матрица комплексных чисел)
     */
    public void multiply(Complex[][] matrix) throws ArrayIndexOutOfBoundsException {
        if (row == matrix.length && col == matrix[0].length){
            Complex[][] resultMatrix = new Complex[row][col];
            for (int i = 0; i < row; ++i) {
                for (int j = 0; j < col; ++j) {
                    resultMatrix[i][j] = new Complex(0);
                    for (int e = 0; e < col; e++) {
                        resultMatrix[i][j] = Complex.sum(resultMatrix[i][j], Complex.multiply(this.matrix[i][e], matrix[e][j]));
                    }
                }
            }
            setMatrix(resultMatrix);
            System.out.println(this);
        }
        throw new RuntimeException("Матрицы должны быть одинакового размера!");
    }

    /**
     * Метод для транспонирования матрицы
     */
    public void transpose() throws ArrayIndexOutOfBoundsException {
        Complex[][] resultMatrix = new Complex[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                resultMatrix[i][j] = matrix[j][i];
            }
        }
        setMatrix(resultMatrix);
        System.out.println(this);
    }

    /**
     * Метод для подсчета детерминанта
     *
     * @param matrix (матрица комлексных чисел)
     * @return (значение детерминанта)
     */
    private Complex calculateDeterminant(Complex[][] matrix) {
        if (col == row) {
            Complex result = new Complex(0);
            if (matrix.length == 1) {
                return matrix[0][0];
            }
            if (matrix.length == 2) {
                result = Complex.subtract(Complex.multiply(matrix[0][0], matrix[1][1]), Complex.multiply(matrix[0][1], matrix[1][0]));
                return result;
            }
            for (int i = 0; i < matrix[0].length; i++) {
                Complex[][] temp = new Complex[matrix.length - 1][matrix[0].length - 1];
                for (int j = 1; j < matrix.length; j++) {
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (k < i) {
                            temp[j - 1][k] = matrix[j][k];
                        } else if (k > i) {
                            temp[j - 1][k - 1] = matrix[j][k];
                        }
                    }
                }
                Complex minor = Complex.multiply(Complex.multiply(matrix[0][i], new Complex(Math.pow(-1, i))), calculateDeterminant(temp));
                result = Complex.sum(result, minor);
            }
            return result;
        }
        throw new RuntimeException("Не возможно посчитать детерминант");
    }

    public Complex calculateDeterminant() throws ArrayIndexOutOfBoundsException {
        return this.calculateDeterminant(matrix);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    /**
     * @return (представления матрицы в удобной форме)
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (Complex[] complexNumbers : matrix) {
            for (int j = 0; j < col; ++j) {
                output.append("{ ").append(complexNumbers[j]).append(" } ");
            }
            output.append("\n");
        }
        return output.toString();
    }
}
