package kryptoprojekt.model;

import java.util.Arrays;

/**
 * This Class is for easier handling with matrizes and operations on them.
 *
 * @author Stefan
 */
public class Matrix<E extends KryptoType<E>> {

    private int x, y;
    private E[][] matrix;

    /**
     * Creates an instance of Matrix and sets its value to the parameter.
     *
     * @param matrix the value of the new instance of Matrix.
     */
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
    /**
     * This Constructor is for internal use,
     * if arithmetic instructions are used.
     * Creates a new instance with an empty new array.
     *
     * @param x number of rows of the new Matrix.
     * @param y number of columns of the new Matrix.
     */
    private Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        matrix = (E[][]) new KryptoType[x][y];
    }

    /**
     * Returns the value of a specific element of this Matrix.
     *
     * @param x specifies the row.
     * @param y specifies the column.
     * @return {@code this[x][y]}
     */
    public E get(int x, int y) {
        return matrix[x][y];
    }

    /**
     * Sets the value of a specific element of this Matrix.
     *
     * @param x specifies the row.
     * @param y specifies the column.
     * @param value the new value of the specific element.
     */
    public void set(int x, int y, E value) {
        matrix[x][y] = value;
    }

    /**
     * Returns the "height" of this Matrix.
     *
     * @return the number of rows of this Matrix.
     */
    public int getMatrixRowCapacity() {
        return this.x;
    }

    /**
     * Returns the "width" of this Matrix.
     *
     * @return the number of columns of this Matrix.
     */
    public int getMatrixColumnCapacity() {
        return this.y;
    }

    /**
     * Returns a new Matrix whose value is {@code this + other}
     *
     * @param other value to be added to this Matrix.
     * @return {@code this + other}
     */
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

    /**
     * Returns a new Matrix whose value is {@code this * other}
     *
     * @param other value to be multiplied with this Matrix.
     * @return {@code this * other}
     */
    public Matrix<E> multiply(Matrix<E> other) {
        if (y != other.x) {
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

    /**
     * Translates a String-representation of a matrix
     * into a new instance of type Matrix<Z>.
     * The format of the String must be:
     * column elements seperated by a ',';
     * rows seperated by a '|';
     * example: "1,2|3,4".
     * The number of elements of each row must be the same.
     *
     * @param matrix String representation of the new Matrix.
     * @return
     */
    public static Matrix<Z> valueOf(String matrix) {
        String[] s = matrix.replace(" ", "").split("[|]+");
        String[][] s2 = new String[s.length][];
        for (int i = 0; i < s.length; i++) {
            s2[i] = s[i].split(",");
        }
        Z[][] field = new Z[s2.length][];
        for (int i = 0; i < s2.length; i++) {
            field[i] = new Z[s2[i].length];
            for (int j = 0; j < s2[i].length; j++) {
                field[i][j] = new Z((s2[i][j]));
            }
        }
        return new Matrix<Z>(field);
    }

    /**
     * Generates the hashcode for this Matrix.
     *
     * @return hashcode of this Matrix.
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.x;
        hash = 71 * hash + this.y;
        hash = 71 * hash + Arrays.deepHashCode(this.matrix);
        return hash;
    }

    /**
     * Tests if this Matrix has the same parameters like another Matrix.
     *
     * @param o other object to be compared for equality with this Matrix.
     * @return returns false if, and only if, o is an instance of Matrix
     *  and both have the same parameters.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof Matrix) {
            try {
                Matrix<E> m = (Matrix<E>) o;
                if (this.x != m.x || this.y != m.y) {
                    return false;
                }
                for (int i = 0; i < x; i++) {
                    for (int j = 0; j < y; j++) {
                        if (!this.matrix[i][j].equals(m.matrix[i][j])) {
                            return false;
                        }
                    }
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        Tuple<Integer, String[][]> tuple = stringFormat();
        int maxLength = tuple.first();
        String[][] string = tuple.second();
        String result = "";
        for (int i = 0; i < string.length; i++) {
            for (int j = 0; j < string[i].length; j++) {
                String s = string[i][j];
                for (int k = 0; k < maxLength + 1 - string[i][j].length(); k++) {
                    s = " " + s;
                }
                result += s;
            }
            result += "\n";
        }
        return result;
    }

    /**
     * toString-method to get a String-representation of this Matrix
     * with indices.
     *
     * @return String-representation of this Matrix with indices.
     */
    public String toStringWithIndex() {
        Tuple<Integer, String[][]> tuple = stringFormat();
        int maxLength = tuple.first();
        String[][] string = tuple.second();
        String result = "";
        String seperate = "";
        for (int i = 0; i < String.valueOf(string.length - 1).length() - 1; i++) {
            result += " ";
            seperate += "-";
        }
        result += "#";
        seperate += "-";
        for (int i = 0; i < string[0].length; i++) {
            String s = String.valueOf(i);
            for (int j = 0; j < maxLength + 1 - String.valueOf(i).length(); j++) {
                s = " " + s;
            }
            result += "|" + s;
            seperate += "+";
            for (int j = 0; j < maxLength + 1; j++)
                seperate += "-";
        }
        result += "\n";
        seperate += "\n";
        for (int i = 0; i < string.length; i++) {
            result += seperate;
            for (int j = 0; j < String.valueOf(string.length - 1).length() - String.valueOf(i).length(); j++) {
                result += " ";
            }
            result += i;
            for (int j = 0; j < string[i].length; j++) {
                String s = string[i][j];
                for (int k = 0; k < maxLength + 1 - string[i][j].length(); k++) {
                    s = " " + s;
                }
                result += "|" + s;
            }
            result += "\n";
        }
        return result;
    }

    /**
     * For internal use.
     *
     * @return the maximum length of digits of the elements,
     * and this Matrix formatted as String[][].
     * {@code Tuple(max, String[][])}
     */
    private Tuple<Integer, String[][]> stringFormat() {
        int maxLength = 0;
        String[][] string = new String[matrix.length][];
        for (int i = 0; i < matrix.length; i++) {
            string[i] = new String[matrix[i].length];
            for (int j = 0; j < matrix[i].length; j++) {
                String s = matrix[i][j].toString();
                maxLength = Math.max(maxLength, s.length());
                string[i][j] = s;
            }
        }
        return new Tuple<Integer, String[][]>(maxLength, string);
    }
}
