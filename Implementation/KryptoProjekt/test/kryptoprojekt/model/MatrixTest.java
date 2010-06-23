/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

import junit.framework.TestCase;
import static org.junit.Assert.*;

/**
 *
 * @author Stefan
 */
public class MatrixTest extends TestCase {

    public MatrixTest(String name) {
        super(name);
    }

    /*
     * Tests the standard constructor
     */
    public void testKonstructor() {
        Z[][] params = {{new Z(1), new Z(2)}, {new Z(3), new Z(4)}};
        Matrix<Z> m = new Matrix<Z>(params);
        testParams(m, params);
    }

    /*
     * Tests if the valueOf-method returns the right Matrix.
     */
    public void testValueOf() {
        Z[][] params = {{new Z(47), new Z(11), new Z(100)}, {new Z(8), new Z(15), new Z(123)}};
        Matrix<Z> m = Matrix.valueOf("47,11,100|8,15,123");
        testParams(m, params);
    }

    /*
     * Tests if the equals-method of Matrix is correct.
     */
    public void testEquals() {
        assertEquals(Matrix.valueOf("24,35|57,89"), Matrix.valueOf("24,35|57,89"));
        assertNotSame(Matrix.valueOf("25,35|57,89"), Matrix.valueOf("24,35|57,89"));
        assertNotSame(Matrix.valueOf("24,35|57,89"), Matrix.valueOf("24|57"));
    }

    /*
     * Tests if a Matrix, and the same as Z-field have the same parameters
     */
    private void testParams(Matrix<Z> m, Z[][] params) {
        assertEquals(m.get(0, 0), params[0][0]);
        assertEquals(m.get(0, 1), params[0][1]);
        assertEquals(m.get(1, 0), params[1][0]);
        assertEquals(m.get(1, 1), params[1][1]);
        try {
            m.get(0, params[0].length);
            fail();
        } catch (IndexOutOfBoundsException ioobe) {
        }
        assertEquals(m.getMatrixRowCapacity(), params.length);
        assertEquals(m.getMatrixColumnCapacity(), params[0].length);
    }

    /*
     * Tests the add-method of Matrix.
     */
    public void testAdd() {
        Matrix<Z> m1 = Matrix.valueOf("1,2|3,4");
        Matrix<Z> m2 = Matrix.valueOf("4,3|2,1");
        assertEquals(m1.add(m2), Matrix.valueOf("5,5|5,5"));
        assertEquals(m2.add(m1), Matrix.valueOf("5,5|5,5"));
    }

    /*
     * Tests the multiply-method of Matrix.
     */
    public void testMultiply() {
        Matrix<Z> m1 = Matrix.valueOf("4,3|2,1");
        Matrix<Z> m2 = Matrix.valueOf("13,8|7,17");
        assertEquals(m1.multiply(m2), Matrix.valueOf("73,83|33,33"));
        m1 = Matrix.valueOf("1,2");
        assertEquals(m1.multiply(m2), Matrix.valueOf("27,42"));
    }
}
