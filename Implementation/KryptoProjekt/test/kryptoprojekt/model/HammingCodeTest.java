/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.Hashtable;
import junit.framework.TestCase;

/**
 *
 * @author LiTTle
 */
public class HammingCodeTest extends TestCase{


    public HammingCodeTest(String name) {
        super(name);
    }

    public void testGenerateGeneratorMatrix() {
        HammingCode c = new HammingCode("110");
        Matrix m = Matrix.valueOf("1,0,0,0,1,1,1|0,1,0,1,0,1,1|0,0,1,1,1,0,1");
        KryptoType<PrimeFieldElement>[][] t = new KryptoType[m.getMatrixRowCapacity()][m.getMatrixColumnCapacity()];
        for (int i=0; i< m.getMatrixRowCapacity(); i++)
            for (int j=0; j<m.getMatrixColumnCapacity(); j++)
                t[i][j] = new PrimeFieldElement((Z)m.get(i, j), new Z(2));
        Matrix<PrimeFieldElement> fin = new Matrix(t);
        assertEquals(fin, c.generateGeneratorMatrix());
    }

    public void testGenerateControlMatrix() {
        HammingCode c = new HammingCode("110");
        Matrix cMatrix = Matrix.valueOf("0,1,1,1|1,0,1,1|1,1,0,1|1,0,0,0|0,1,0,0|0,0,1,0|0,0,0,1");
        KryptoType<PrimeFieldElement>[][] t = new KryptoType[cMatrix.getMatrixRowCapacity()][cMatrix.getMatrixColumnCapacity()];
        for (int i=0; i< cMatrix.getMatrixRowCapacity(); i++)
            for (int j=0; j<cMatrix.getMatrixColumnCapacity(); j++)
                t[i][j] = new PrimeFieldElement((Z)cMatrix.get(i, j), new Z(2));
        Matrix<PrimeFieldElement> fin = new Matrix(t);
        assertEquals(fin, c.generateControlMatrix());
    }

    public void testEncode() {
        Coder c = new HammingCode("110");
        assertEquals("1101100", c.encode());
    }

    public void testCalculateSyndrom(){
        Coder c = new HammingCode("110");
        c.encode();
        assertEquals("0000", c.calculateSyndrom());

    }

    public void testDetectError(){
        Coder c = new HammingCode("011");

    }

    public void testDecode(){
        HammingCode c = new HammingCode("110");
        c.encode();
        assertEquals("110", c.decode());
    }

    public void testHammingDistance() {
        Coder c = new HammingCode("011");
    }

    public void testVectorWeight() {
        KryptoType<PrimeFieldElement>[][] t = new KryptoType[1][3];
        t[0][0] = new PrimeFieldElement(0, 2);
        t[0][1] = new PrimeFieldElement(1, 2);
        t[0][2] = new PrimeFieldElement(1, 2);
        assertEquals(2,HammingCode.vectorWeight(new Matrix(t)));
    }
}