/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author Stefan
 */
public class Matrix<E extends KryptoType<E>> {

    private int x, y;
    private E[][] matrix;

    public Matrix(E[][] matrix) {
        this.x = matrix.length;
        this.y = matrix[0].length;
        for (int i = 0; i < x; i++) {
            if (matrix[i].length != y) {
                throw new IllegalArgumentException();
            }
        }
        this.matrix = matrix;
    }

    @SuppressWarnings("unchecked")
    private Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        matrix = (E[][]) new KryptoType[x][y];
    }

    public int getMatrixColumnCapacity() {
        return this.x;
    }

    public Matrix<E> add(Matrix<E> other) {
        if (x != other.x || y != other.y) {
            throw new IllegalArgumentException();
        }
        Matrix<E> result = new Matrix<E>(x, y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result.matrix[i][j] = matrix[i][j].add(other.matrix[i][j]);
            }
        }
        return result;
    }

    public Matrix<E> multiply(Matrix<E> other) {
        if (x != other.y) {
            throw new IllegalArgumentException();
        }
        Matrix<E> result = new Matrix<E>(x, other.y);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < other.y; j++) {
                for (int k = 0; k < y; k++) {
                    result.matrix[i][j] =
                            (result.matrix[i][j] == null)
                            ? matrix[i][k].multiply(other.matrix[k][j])
                            : result.matrix[i][j].add(matrix[i][k].multiply(other.matrix[k][j]));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result += matrix[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}
