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

    public void testHammingCodeConstructor() {
        Coder c = new HammingCode("01");
        Matrix m = Matrix.valueOf("1,1,1|1,1,1|1,0,0|0,1,0|0,0,1");
        assertEquals(m, c.generateControlMatrix());
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
        Coder c = new HammingCode("011");

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