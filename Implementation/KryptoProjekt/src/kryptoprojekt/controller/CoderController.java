/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.controller;

import java.util.HashMap;
import javax.swing.JCheckBox;
import kryptoprojekt.Kit;
import kryptoprojekt.Kit.DropTextField;
import kryptoprojekt.model.HammingCode;
import kryptoprojekt.model.Matrix;
import kryptoprojekt.model.PrimeFieldElement;
import kryptoprojekt.model.Z;

/**
 *
 * @author LiTTle, Mario
 */
public class CoderController {

<<<<<<< HEAD
    private CoderController() {
    }

    public static HammingCode calculateHammingSyndrom(HammingCode hc) {
        hc.calculateSyndrom();

        return hc;
=======
    public static HammingCode calculateHammingSyndrom(DropTextField textField1) {
        HammingCode result = (HammingCode)textField1.getResult();
        result.calculateSyndrom();
        return result;
>>>>>>> e347deec9f9bfcf6524f54857189bcf46bc5417e
    }

    public static HammingCode encodeHammingCode(HammingCode hc) {
        hc.encode();

        return hc;
    }

<<<<<<< HEAD
    public static HammingCode initHammingCode(boolean enableMatrix, Matrix<PrimeFieldElement> generatorM, String sourceCodeWord) {
        HammingCode result = null;
        if (enableMatrix && generatorM != null) {
            result = new HammingCode(generatorM, sourceCodeWord);
=======
    public static HammingCode initHammingCode(DropTextField textGeneratorMatrix, DropTextField textSourceCodeword, JCheckBox enableMatrix){
         if(enableMatrix.isSelected()){
        textGeneratorMatrix.enable(true);
        HammingCode result = new HammingCode( (Matrix<PrimeFieldElement>)textGeneratorMatrix.getResult(),(String)textSourceCodeword.getResult());
>>>>>>> e347deec9f9bfcf6524f54857189bcf46bc5417e

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
