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
    private CoderController() {}

    public static HammingCode calculateHammingSyndrom(DropTextField textField1) {
        HammingCode result = (HammingCode)textField1.getResult();
        result.calculateSyndrom();

        return result;
    }

    public static HammingCode encodeHammingCode(DropTextField textField1) {
        return null;
    }

    public static HammingCode initHammingCode(DropTextField textGeneratorMatrix, DropTextField textSourceCodeword, JCheckBox enableMatrix){
         if(enableMatrix.isSelected()){
        textGeneratorMatrix.enable(true);
        HammingCode result = new HammingCode( (Matrix<PrimeFieldElement>)textGeneratorMatrix.getResult(),(String)textSourceCodeword.getResult());

        return result;
        }
        else{
        HammingCode result = new HammingCode((String)textSourceCodeword.getText());

        return result;
        }
    }

    public static Z hammingWeight(DropTextField textVector1){
        Z result = HammingCode.vectorWeight((Matrix<PrimeFieldElement>) textVector1.getResult());
        return result;
    }
}
