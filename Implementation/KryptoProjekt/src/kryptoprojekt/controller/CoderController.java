/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.controller;

import kryptoprojekt.model.HammingCode;
import kryptoprojekt.model.Matrix;
import kryptoprojekt.model.PrimeFieldElement;
import kryptoprojekt.model.Z;

/**
 *
 * @author LiTTle, Mario
 */
public class CoderController {

    private CoderController() {
    }

    public static HammingCode calculateHammingSyndrom(HammingCode hc) {
        hc.calculateSyndrom();

        return hc;
    }

    public static HammingCode decodeHammingCode(HammingCode hc) throws RuntimeException{
        hc.calculateSyndrom();
        hc.decode();
        hc.detectError();
        

        return hc;
    }

    public static HammingCode encodeHammingCode(HammingCode hc) {
        hc.encode();

        return hc;
    }

    public static HammingCode createHammingError(HammingCode hc, String probability) {
        hc.generateBitError(Double.parseDouble(probability));
        return hc;
    }

    public static HammingCode initHammingCode(boolean enableMatrix, Matrix<PrimeFieldElement> generatorM, String sourceCodeWord) {
        HammingCode result = null;
        if (enableMatrix && generatorM != null) {
            result = new HammingCode(generatorM, sourceCodeWord);

        } else {
            result = new HammingCode(sourceCodeWord);
        }
        return result;
    }

    public static Z calculateHammingDistance(Matrix<PrimeFieldElement> vector1, Matrix<PrimeFieldElement> vector2) {
        Z result = HammingCode.hammingDistance(vector1, vector2);

        return result;
    }

    public static Z hammingWeight(Matrix<PrimeFieldElement> elem){
        Z result = HammingCode.vectorWeight(elem);
        return result;
    }
}
