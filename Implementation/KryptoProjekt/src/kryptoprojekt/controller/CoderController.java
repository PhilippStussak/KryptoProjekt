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
    private CoderController() {}

    public static HammingCode calculateHammingSyndrom(DropTextField textField1) {
        HammingCode result = (HammingCode)textField1.getResult();
        result.calculateSyndrom();

        return result;
    }

    public static HammingCode encodeHammingCode(DropTextField textField1) {
        return null;
    }
}
