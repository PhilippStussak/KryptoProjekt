/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.controller;

import kryptoprojekt.model.Validator;

/**
 *
 * @author Stefan
 */
public class LogicValidator {

    public static boolean isInteger(String value) {
        return Validator.isInteger(value);
    }

    public static boolean isPosInteger(String value) {
        return Validator.isPosInteger(value);
    }

    public static boolean isBinaryString(String value) {
        return Validator.isBinaryString(value);
    }
}
