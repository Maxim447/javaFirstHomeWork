import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        Scanner scanner = new Scanner(System.in);
        Complex firstNumber = new Complex(1, 1);
        Complex secondNumber = new Complex(5);
        System.out.print("firstNumber + secondNumber = ");
        System.out.println(firstNumber.sum(secondNumber));
        System.out.print("firstNumber - secondNumber = ");
        System.out.println(firstNumber.subtract(secondNumber));
        System.out.print("firstNumber * secondNumber = ");
        System.out.println(firstNumber.multiply(secondNumber));
        System.out.print("abs(secondNumber) = ");
        System.out.println(secondNumber.abs());
        System.out.print("phase(secondNumber) = ");
        System.out.println(secondNumber.phase());
        System.out.print("firstNumber trigFrom = ");
        System.out.println(firstNumber.toTrig());
        System.out.print("firstNumber / secondNumber = ");
        System.out.println(firstNumber.divide(secondNumber));
        try {
            Complex thirdNumber = new Complex(0);
            System.out.print("firstNumber / thirdNumber = ");
            firstNumber.divide(thirdNumber);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        int row = 0;
        int col = 0;
        int[][] firstMatrix = null;
        try {
            System.out.println("Input size of matrix");
            System.out.print("row = ");
            row = scanner.nextInt();
            System.out.print("col = ");
            col = scanner.nextInt();
            firstMatrix = new int[row][col];
            System.out.println("Input elements of matrix");
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    firstMatrix[i][j] = scanner.nextInt();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Input numbers only!");
        }
        Matrix matrix1 = new Matrix(row, col);
        assert firstMatrix != null;
        matrix1.setMatrix(firstMatrix);

        Random random = new Random();
        Complex[][] secondMatrix = new Complex[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                secondMatrix[i][j] = new Complex(random.nextDouble(), random.nextDouble());
            }
        }
        Matrix matrix2 = new Matrix(row, col);
        matrix2.setMatrix(secondMatrix);

        System.out.println("First Matrix:");
        System.out.println(matrix1);

        System.out.println("Second Matrix:");
        System.out.println(matrix2);

        System.out.println("firstMatrix + secondMatrix = ");
        try {
            matrix1.sum(secondMatrix);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("firstMatrix * secondMatrix = ");
        try {
            matrix1.multiply(secondMatrix);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("firstMatrix transpose = ");
        matrix1.transpose();

        System.out.print("firstMatrix determinant = ");
        try {
            System.out.println(matrix1.calculateDeterminant());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

    }
}
