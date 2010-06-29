/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author LiTTle, Mario
 */
public abstract class Coder {
    protected String codeWord;
    public abstract String encode();
    public abstract String calculateSyndrom();
    public abstract void detectError();
    public abstract String decode();
}
