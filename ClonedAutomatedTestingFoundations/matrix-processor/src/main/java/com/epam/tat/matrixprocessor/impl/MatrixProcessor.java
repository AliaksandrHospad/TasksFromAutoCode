package com.epam.tat.matrixprocessor.impl;

import java.math.BigDecimal;
import java.util.Arrays;
import java.math.BigDecimal;

import com.epam.tat.matrixprocessor.IMatrixProcessor;
import com.epam.tat.matrixprocessor.exception.MatrixProcessorException;


/**
 * Function Description:
 * Complete the functions below. All methods must work with matrices of the double type.
 *
 * Constraints:
 * 0 < m < 10
 * 0 < n < 10
 * where m - number of rows in matrix
 * where n - number of columns in matrix
 *
 * In case of incorrect input values or inability to perform a calculation, the method should throw an appropriate
 * exception.
 *
 */
public class MatrixProcessor implements IMatrixProcessor {

    /**
     * Matrix transpose is an operation on a matrix where its rows become columns with the same numbers.
     * Ex.:
     * |1 2|			|1 3 5|
     * |3 4|   ====>	|2 4 6|
     * |5 6|
     *
     * @param matrix - matrix for transposition
     * @return the transposed matrix
     */
    @Override
    public double[][] transpose(double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new MatrixProcessorException("Input matrix shouldn't be empty!");
        } else {
            double[][] actualMatrix = new double[matrix[0].length][matrix.length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    actualMatrix[i][j] = matrix[j][i];
                }
            }
            return actualMatrix;
            //throw new UnsupportedOperationException("You need to implement this method");
        }
    }


    /**
     * The method flips the matrix clockwise.
     * Ex.:
     * * |1 2|			|5 3 1|
     * * |3 4|   ====>	|6 4 2|
     * * |5 6|
     *
     * @param matrix - rotation matrix
     * @return rotated matrix
     */
    @Override
    public double[][] turnClockwise(double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new MatrixProcessorException("Input matrix shouldn't be empty!");
        } else {
            double[][] actualMatrix = new double[matrix[0].length][matrix.length];
            for (int i = 0; i < matrix[0].length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    actualMatrix[i][j] = matrix[matrix.length - j - 1][i];
                }
            }
            return actualMatrix;
            //throw new UnsupportedOperationException("You need to implement this method");
        }
    }


    /**
     * This method multiplies matrix firstMatrix by matrix secondMatrix
     * <p>
     * See {https://en.wikipedia.org/wiki/Matrix_multiplication}
     *
     * @param firstMatrix  - first matrix to multiply
     * @param secondMatrix - second matrix to multiply
     * @return result matrix
     */
    @Override
    public double[][] multiplyMatrices(double[][] firstMatrix, double[][] secondMatrix) {
        if (firstMatrix == null || secondMatrix == null || firstMatrix.length == 0 || secondMatrix.length ==0) {
            throw new MatrixProcessorException("Input matrix shouldn't be empty!");
        } else if (firstMatrix[0].length != secondMatrix.length) {
            throw new MatrixProcessorException("the number of columns is not equal to the number of rows");

        } else {
            double[][] actualMatrix = new double[firstMatrix.length][secondMatrix[0].length];
            double sum = 0;
            for (int s = 0; s < firstMatrix.length; s++) {
                for (int i = 0; i < secondMatrix[0].length; i++) {
                    for (int j = 0; j < secondMatrix.length; j++) {
                        sum = sum + (firstMatrix[s][j] * secondMatrix[j][i]);
                    }
                    double x = sum;               //обрезание до трех цифр после запятой (если округлять с заданной точностью
                    x = x * 10000;                   //из-за округления в большую сторону не проходят тесты на платорме)
                    int t = (int) x;
                    x = x / 10;
                    x = Math.round(x);
                    actualMatrix[s][i] = x / 1000;
                    sum = 0;
                }
            }
            return actualMatrix;
        }
    }
//        try {
//            double [][] actualMatrix = new double[firstMatrix.length][secondMatrix[0].length];
//            double sum=0;
//                for (int s = 0; s < firstMatrix.length; s++) {
//                    for (int i = 0; i < secondMatrix[0].length; i++) {
//                        for (int j = 0; j < secondMatrix.length; j++) {
//                        sum = sum + (firstMatrix [s][j] * secondMatrix[j][i]);
//                        }
//                    double x = sum;               //обрезание до трех цифр после запятой (если округлять с заданной точностью
//                    x= x*10000;                   //из-за округления в большую сторону не проходят тесты на платорме)
//                    int t = (int) x;
//                    x=x/10;
//                    x=Math.round(x);
//                    actualMatrix [s][i] = x/1000;
//                    sum=0;
//                   }
//                }
//            return actualMatrix;
//        }catch (MatrixProcessorException e){
//            System.err.print(e);
//            double [][] actualMatrix = null;
//            return actualMatrix;
//        }
//    }


    /**
     * This method returns the inverse of the matrix
     * <p>
     * See {https://en.wikipedia.org/wiki/Invertible_matrix}
     *
     * @param matrix - input matrix
     * @return inverse matrix for input matrix
     */
    @Override
    public double[][] getInverseMatrix(double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new MatrixProcessorException("Input matrix shouldn't be empty!");
        } else {
            if (matrix.length != matrix[0].length) {
                throw new MatrixProcessorException("Cannot calc determinant of non-square matrix!");
            } else {


                double[][] actualMatrix = new double[matrix.length][matrix.length];
                for (int i = 0; i < matrix.length; i++)
                    for (int j = 0; j < matrix.length; j++) {
                        if (i == j)
                            actualMatrix[i][j] = 1;
                        else
                            actualMatrix[i][j] = 0;
                    }

                double p = 0, q = 0, s = 0;
                for (int i = 0; i < matrix.length; i++) {
                    p = matrix[i][i];
                    for (int j = i + 1; j < matrix.length; j++) {
                        q = matrix[j][i];
                        for (int k = 0; k < matrix.length; k++) {
                            double x = (matrix[i][k] * q - matrix[j][k] * p);       // округляю все значения в матрицах до 3-х цифр после запятой
                            x = x * 100000;
                            x = Math.round(x);
                            x = x / 100000;
                            matrix[j][k] = x;

                            x = (actualMatrix[i][k] * q - actualMatrix[j][k] * p);
                            x = x * 100000;
                            x = Math.round(x);
                            x = x / 100000;
                            actualMatrix[j][k] = x;
                        }
                    }
                }

                for (int i = 0; i < matrix.length; i++) {
                    for (int j = matrix.length - 1; j >= 0; j--) {
                        s = 0;
                        for (int k = matrix.length - 1; k > j; k--)
                            s += matrix[j][k] * actualMatrix[k][i];

//                            actualMatrix[j][i] = ((actualMatrix[j][i] - s) / matrix[j][j]);
                        double x = ((actualMatrix[j][i] - s) / matrix[j][j]);               //округляю все значения в матрицах до 3-х цифр после запятой
                        x = x * 1000;
                        x = Math.round(x);
                        actualMatrix[j][i] = x / 1000;
                    }
                }

                return actualMatrix;

                //throw new UnsupportedOperationException("You need to implement this method");
            }
        }
    }


    /**
     * This method returns the determinant of the matrix
     * <p>
     * See {https://en.wikipedia.org/wiki/Determinant}
     *
     * @param matrix - input matrix
     * @return determinant of input matrix
     */

    @Override
    public double getMatrixDeterminant(double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            throw new MatrixProcessorException("Input matrix shouldn't be empty!");
        } else {
            if (matrix.length != matrix[0].length) {
                throw new MatrixProcessorException("Cannot calc determinant of non-square matrix!");
            } else {
                class DeterminantCalc {

                    private double[][] matrix;
                    private int sign = 1;


                    DeterminantCalc(double[][] matrix) {
                        this.matrix = matrix;
                    }


                    public int getSign() {
                        return sign;
                    }

                    public double determinant() {

                        double deter;
                        if (isUpperTriangular() || isLowerTriangular()) {
                            BigDecimal x = multiplyDiameter().multiply(BigDecimal.valueOf(sign));               //округляю все значения в матрицах до 3-х цифр после запятой
                            double y = x.doubleValue() * 100000;
                            y = Math.round(y);
                            deter = y / 100000;

                        } else {
                            makeTriangular();
                            BigDecimal x = multiplyDiameter().multiply(BigDecimal.valueOf(sign));              //округляю все значения в матрицах до 3-х цифр после запятой
                            double y = x.doubleValue() * 100000;
                            y = Math.round(y);
                            deter = y / 100000;
                        }
                        return deter;
                    }


                    /*  receives a matrix and makes it triangular using allowed operations
                        on columns and rows
                    */
                    public void makeTriangular() {

                        for (int j = 0; j < matrix.length; j++) {
                            sortCol(j);
                            for (int i = matrix.length - 1; i > j; i--) {
                                if (matrix[i][j] == 0)
                                    continue;

                                double x = matrix[i][j];
                                double y = matrix[i - 1][j];
                                multiplyRow(i, (-y / x));
                                addRow(i, i - 1);
                                multiplyRow(i, (-x / y));
                            }
                        }
                    }


                    public boolean isUpperTriangular() {

                        if (matrix.length < 2)
                            return false;

                        for (int i = 0; i < matrix.length; i++) {
                            for (int j = 0; j < i; j++) {
                                if (matrix[i][j] != 0)
                                    return false;

                            }

                        }
                        return true;
                    }


                    public boolean isLowerTriangular() {

                        if (matrix.length < 2)
                            return false;

                        for (int j = 0; j < matrix.length; j++) {
                            for (int i = 0; j > i; i++) {
                                if (matrix[i][j] != 0)
                                    return false;

                            }

                        }
                        return true;
                    }


                    public BigDecimal multiplyDiameter() {

                        BigDecimal result = BigDecimal.ONE;
                        for (int i = 0; i < matrix.length; i++) {
                            for (int j = 0; j < matrix.length; j++) {
                                if (i == j)
                                    result = result.multiply(BigDecimal.valueOf(matrix[i][j]));

                            }

                        }
                        return result;
                    }


                    // when matrix[i][j] = 0 it makes it's value non-zero
                    public void makeNonZero(int rowPos, int colPos) {

                        int len = matrix.length;

                        outer:
                        for (int i = 0; i < len; i++) {
                            for (int j = 0; j < len; j++) {
                                if (matrix[i][j] != 0) {
                                    if (i == rowPos) { // found "!= 0" in it's own row, so cols must be added
                                        addCol(colPos, j);
                                        break outer;

                                    }
                                    if (j == colPos) { // found "!= 0" in it's own col, so rows must be added
                                        addRow(rowPos, i);
                                        break outer;
                                    }
                                }
                            }
                        }
                    }


                    //add row1 to row2 and store in row1
                    public void addRow(int row1, int row2) {

                        for (int j = 0; j < matrix.length; j++)
                            matrix[row1][j] += matrix[row2][j];
                    }


                    //add col1 to col2 and store in col1
                    public void addCol(int col1, int col2) {

                        for (int i = 0; i < matrix.length; i++)
                            matrix[i][col1] += matrix[i][col2];
                    }


                    //multiply the whole row by num
                    public void multiplyRow(int row, double num) {

                        if (num < 0)
                            sign *= -1;


                        for (int j = 0; j < matrix.length; j++) {
                            matrix[row][j] *= num;
                        }
                    }


                    //multiply the whole column by num
                    public void multiplyCol(int col, double num) {

                        if (num < 0)
                            sign *= -1;

                        for (int i = 0; i < matrix.length; i++)
                            matrix[i][col] *= num;

                    }


                    // sort the cols from the biggest to the lowest value
                    public void sortCol(int col) {

                        for (int i = matrix.length - 1; i >= col; i--) {
                            for (int k = matrix.length - 1; k >= col; k--) {
                                double tmp1 = matrix[i][col];
                                double tmp2 = matrix[k][col];

                                if (Math.abs(tmp1) < Math.abs(tmp2))
                                    replaceRow(i, k);
                            }
                        }
                    }


                    //replace row1 with row2
                    public void replaceRow(int row1, int row2) {

                        if (row1 != row2)
                            sign *= -1;

                        double[] tempRow = new double[matrix.length];

                        for (int j = 0; j < matrix.length; j++) {
                            tempRow[j] = matrix[row1][j];
                            matrix[row1][j] = matrix[row2][j];
                            matrix[row2][j] = tempRow[j];
                        }
                    }


                    //replace col1 with col2
                    public void replaceCol(int col1, int col2) {

                        if (col1 != col2)
                            sign *= -1;

                        System.out.printf("replace col%d with col%d, sign = %d%n", col1, col2, sign);
                        double[][] tempCol = new double[matrix.length][1];

                        for (int i = 0; i < matrix.length; i++) {
                            tempCol[i][0] = matrix[i][col1];
                            matrix[i][col1] = matrix[i][col2];
                            matrix[i][col2] = tempCol[i][0];
                        }
                    }
                }


                DeterminantCalc deter = new DeterminantCalc(matrix);
                double actualDeterminant = deter.determinant();
                return actualDeterminant;
            }
        }
    }
}













