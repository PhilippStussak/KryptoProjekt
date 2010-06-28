/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.controller;

import java.util.HashMap;
import kryptoprojekt.Kit;
import kryptoprojekt.Kit.DropTextField;
import kryptoprojekt.model.HammingCode;

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
}
