/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author phil
 */
public class GaloisElement implements KryptoType<GaloisElement> {

    private Z value;
    private Z base;

    public GaloisElement(Z value, Z base) {
        this.base = base;
        this.value = value.mod(base);
    }

    public GaloisElement add(GaloisElement other) {
        if (this.base.equals(other.base)) {
            return new GaloisElement((this.value.add(other.value)).mod(base), this.base);
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    public int compareTo(GaloisElement other) {
        if (this.base.equals(other.base)) {
            return value.compareTo(other.value);
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    public GaloisElement divide(GaloisElement other) {
        if (this.base.equals(other.base)) {
            return this.multiply(this.inverseElement());
        } else {
            throw new RuntimeException();
        }
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

    public GaloisElement multiply(GaloisElement other) {
        if (this.base.equals(other.base)) {
            return new GaloisElement((this.value.multiply(other.value)).mod(base), this.base);
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    public GaloisElement subtract(GaloisElement other) {
        if (this.base.equals(other.base)) {
            return new GaloisElement((this.value.add(this.base.subtract(other.value))).mod(base), this.base);
        } else {
            throw new RuntimeException("Base missmatch");
        }
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

    public GaloisElement inverseElement() {
        return new GaloisElement(base.subtract(value), base);
    }
}
