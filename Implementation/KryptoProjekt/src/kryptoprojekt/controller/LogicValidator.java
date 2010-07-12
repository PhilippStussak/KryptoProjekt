/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.controller;

import java.util.regex.Pattern;

/**
 *
 * @author LiTTle
 */
public class LogicValidator {

    //double regex not completely right, values beginning with zero are not considered
    //private static String doubleString = "[+-]?[1-9][0-9]*(?:[.,]?[0-9]+|[0-9]*)";
    private static String intString = "([-+]?[1-9][0-9]*)|0";
    private static String binaryString = "[01]+";
    private static String posintString = "([1-9][0-9]*)|0";
    private static String termMultiString = "(([0]|((?!0)[1-9]{1}([0-9]{0,}+)))[\\^|\\*]?)*+";
                                          //"(([0]|((?!0)[1-9]{1}((?!0)[0-9]{0,}+)))[\\^|\\*]?)*+"

    private LogicValidator() {
    }

    public static boolean isInteger(String value) {
        return Pattern.matches(intString, value);
    }

    public static boolean isPosInteger(String value) {
        return Pattern.matches(posintString, value);
    }

//    public static boolean isDouble(String value) {
//        return Pattern.matches(doubleString, value);
//    }
    public static boolean isBinaryString(String value) {
        return Pattern.matches(binaryString, value);
    }

    public static boolean isTermMultiplication(String value){
        return Pattern.matches(termMultiString, value);
    }
}
