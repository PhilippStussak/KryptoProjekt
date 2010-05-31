/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.Hashtable;

/**
 *
 * @author LiTTle, Mario
 */
public abstract class Coder {
    public String codeWord;
    public abstract String encode();
    public abstract void calculateSyndrom();
    public abstract Hashtable detectError();
    public abstract String printStatistics();
    public abstract String decode();
}
