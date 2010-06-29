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

/**
 *
 * @author LiTTle
 */
public class CoderController {
    private static HashMap<String, Object> results;

    public String calculateHammingSyndrom(DropTextField textField1) {
        Object result = ((HammingCode)textField1.getResult()).calculateSyndrom();

        results.put(getTitle() + "_syndrom", result);
        return result.toString();
    }

    public static HammingCode initHammingCode(DropTextField textGeneratorMatrix, DropTextField textSourceCodeword, JCheckBox enableMarix){
         if(enableMarix.isSelected()){
        textGeneratorMatrix.enable(true);
        HammingCode result = new HammingCode( (Matrix<PrimeFieldElement>)textGeneratorMatrix.getResult(),(String)textSourceCodeword.getResult());

        return result;
        }
        else{
        HammingCode result = new HammingCode((String)textSourceCodeword.getText());

        return result;
        }
    }
}
