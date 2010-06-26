/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

import junit.framework.TestCase;
import static org.junit.Assert.*;

/**
 *
 * @author phil
 */
public class PrimeFieldElementTest extends TestCase {

    public PrimeFieldElementTest(String name) {
        super(name);
    }

    public void testKonstuctor() {
        PrimeFieldElement pfeINT = new PrimeFieldElement(10, 7);
        assertEquals(pfeINT.getPrimeElemValue(), (new Z(10).mod(new Z(7))));

        PrimeFieldElement pfeZ = new PrimeFieldElement(new Z(10), new Z(7));
        assertEquals(pfeZ.getPrimeElemValue(), new Z(10).mod(new Z(7)));

        PrimeFieldElement pfeString = new PrimeFieldElement("10,7");
        assertEquals(pfeString.getPrimeElemValue(), new Z(10).mod(new Z(7)));

        try {
            PrimeFieldElement pfeIntFail = new PrimeFieldElement(10, 4);
            fail();
        } catch (RuntimeException re) {
            // base is not prime
        }

        try {
            PrimeFieldElement pfeZFail = new PrimeFieldElement(new Z(10), new Z(4));
            fail();
        } catch (RuntimeException re) {
            // base is not prime
        }

         try {
            PrimeFieldElement pfeStringFail = new PrimeFieldElement("10,4");
            fail();
        } catch (RuntimeException re) {
            // base is not prime
        }
    }

    public void testAdd() {
        PrimeFieldElement pfeAddOne = new PrimeFieldElement(10, 3);
        PrimeFieldElement pfeAddTwo = new PrimeFieldElement(12, 3);
        PrimeFieldElement pfeAddThree = new PrimeFieldElement(15, 7);

        assertEquals(pfeAddOne.add(pfeAddTwo), new PrimeFieldElement(1, 3));

        try {
            pfeAddOne.add(pfeAddThree);
            fail();
        } catch (RuntimeException re) {
            // bases do not match
        }
    }

    public void testCompareTo(){
        PrimeFieldElement pfeCompareOne = new PrimeFieldElement(10, 17);
        PrimeFieldElement pfeCompareTwo = new PrimeFieldElement(12, 17);
        PrimeFieldElement pfeCompareThree = new PrimeFieldElement(15, 7);

        assertTrue(pfeCompareOne.compareTo(pfeCompareTwo) < 0 );

        try {
            pfeCompareOne.compareTo(pfeCompareThree);
            fail();
        } catch (RuntimeException re) {
            // bases do not match
        }
    }

    public void testMultiplication(){
        PrimeFieldElement pfeMuleOne = new PrimeFieldElement(10, 17);
        PrimeFieldElement pfeMulTwo = new PrimeFieldElement(12, 17);
        PrimeFieldElement pfeMulThree = new PrimeFieldElement(15, 7);

        assertEquals(pfeMuleOne.multiply(pfeMulTwo), new PrimeFieldElement(1, 17));

        try {
            pfeMuleOne.multiply(pfeMulThree);
            fail();
        } catch (RuntimeException re) {
            // bases do not match
        }
    }

    public void testInverseElementAddition(){
        PrimeFieldElement pfeInvAddOne = new PrimeFieldElement(10, 17);

        assertEquals(pfeInvAddOne.inverseElementAddition(), new PrimeFieldElement(7, 17));
    }

    public void testInverseElementMultiplication(){
        PrimeFieldElement pfeInvMulOne = new PrimeFieldElement(10, 17);

        assertEquals(pfeInvMulOne.inverseElementMultiplication(), new PrimeFieldElement(12, 17));
    }

    public void testDivide(){
        PrimeFieldElement pfeDivOne = new PrimeFieldElement(10, 13);
        PrimeFieldElement pfeDivTwo = new PrimeFieldElement(12, 13);
        PrimeFieldElement pfeDivThree = new PrimeFieldElement(10, 17);

        assertEquals(pfeDivOne.divide(pfeDivTwo), new PrimeFieldElement(3, 13));

        try {
            pfeDivOne.divide(pfeDivThree);
            fail();
        } catch (RuntimeException re) {
            // bases do not match
        }

    }

    public void testSubtract(){
        PrimeFieldElement pfeSubOne = new PrimeFieldElement(10, 13);
        PrimeFieldElement pfeSubTwo = new PrimeFieldElement(12, 13);
        PrimeFieldElement pfeSubThree = new PrimeFieldElement(12, 17);

        assertEquals(pfeSubOne.subtract(pfeSubTwo), new PrimeFieldElement(11, 13));

        try {
            pfeSubOne.subtract(pfeSubThree);
            fail();
        } catch (RuntimeException re) {
            // bases do not match
        }
    }
    
    public void testGetterAndIsX(){
        PrimeFieldElement pfeGetOne = new PrimeFieldElement(1, 13);
        PrimeFieldElement pfeGetTwo = new PrimeFieldElement(0, 13);
        assertEquals(pfeGetOne.getPrimeElemBase(), new Z(13));
        assertEquals(pfeGetOne.getPrimeElemValue(), new Z(1));
        assertTrue(pfeGetOne.isONE());
        assertTrue(pfeGetTwo.isZERO());
    }

    public void testToString(){
        PrimeFieldElement pfeToString = new PrimeFieldElement(10, 13);
        assertEquals(pfeToString.toString(), "[10, 13]");
    }

    public void testAddTable(){
        PrimeFieldElement pfeAddTable = new PrimeFieldElement(1, 3);
        Matrix m = Matrix.valueOf("0,1,2|1,2,0|2,0,1");
        
        KryptoType<PrimeFieldElement>[][] pfeM = new KryptoType[m.getMatrixRowCapacity()][m.getMatrixColumnCapacity()];
        for (int i=0; i< m.getMatrixRowCapacity(); i++)
            for (int j=0; j<m.getMatrixColumnCapacity(); j++)
                pfeM[i][j] = new PrimeFieldElement((Z)m.get(i, j), new Z(3));
        Matrix<PrimeFieldElement> finalMatrix = new Matrix(pfeM);
        
        assertEquals(pfeAddTable.getAddTable(3, 3), finalMatrix);
    }

    public void testMultiplyTable(){
        PrimeFieldElement pfeAddTable = new PrimeFieldElement(1, 3);
        Matrix m = Matrix.valueOf("0,0,0|0,1,2|0,2,1");
        
        KryptoType<PrimeFieldElement>[][] pfeM = new KryptoType[m.getMatrixRowCapacity()][m.getMatrixColumnCapacity()];
        for (int i=0; i< m.getMatrixRowCapacity(); i++)
            for (int j=0; j<m.getMatrixColumnCapacity(); j++)
                pfeM[i][j] = new PrimeFieldElement((Z)m.get(i, j), new Z(3));
        Matrix<PrimeFieldElement> finalMatrix = new Matrix(pfeM);
        
        assertEquals(pfeAddTable.getMultiplyTable(3, 3), finalMatrix);
    }
    
    public void testEquals(){
        PrimeFieldElement pfeEquals = new PrimeFieldElement(14, 17);
        assertTrue(pfeEquals.equals(new PrimeFieldElement(new Z(14), new Z(17))));
        assertFalse(pfeEquals.equals(new PrimeFieldElement(14, 23)));
        assertFalse(pfeEquals.equals(new PrimeFieldElement(11, 17)));
    }
}