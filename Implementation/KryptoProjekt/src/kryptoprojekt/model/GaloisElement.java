/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author phil
 */
public class GaloisElement implements KryptoType<GaloisElement>{

    private Z value;
    private Z base;

    public GaloisElement(Z value, Z base) {
        this.base = base;
        this.value = value.mod(base);
    }

    public GaloisElement add(GaloisElement other) {
        if(this.base.equals(other.base))
            return new GaloisElement((this.value.add(other.value)).mod(base), this.base);
        else
            // Wrong handling of Base missmatch, corrected soon
            return new GaloisElement(base, value);
    }

    public int compareTo(GaloisElement other) {
        return value.compareTo(other.value);
    }

    public GaloisElement divide(GaloisElement other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean isONE() {
        return value.isONE();
    }

    public boolean isZERO() {
        return value.isZERO();
    }

    public GaloisElement mod(GaloisElement other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public GaloisElement multiply (GaloisElement other){
        if(this.base.equals(other.base))
            return new GaloisElement((this.value.multiply(other.value)).mod(base), this.base);
        else
            // Wrong handling of Base missmatch, corrected soon
            return new GaloisElement(base, value);
    }

    public GaloisElement subtract(GaloisElement other) {
        if(this.base.equals(other.base))
            return new GaloisElement((this.value.add(this.base.subtract(other.value))).mod(base), this.base);
        else
            // Wrong handling of Base missmatch, corrected soon
            return new GaloisElement(base, value);
    }

    public String toBinaryString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Z getBase() {
        return base;
    }

    public void setBase(Z base) {
        this.base = base;
    }

    public Z getValue() {
        return value;
    }

    public void setValue(Z value) {
        this.value = value;
    }
}
