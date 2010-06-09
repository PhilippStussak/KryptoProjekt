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

    public GaloisElement(int value, int base) {
        this.base = new Z(base);
        this.value = new Z(value).mod(this.base);
    }

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
            return this.multiply(other.inverseElement());
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
            return this.add(other.inverseElement());
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    public String toBinaryString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public GaloisElement inverseElement() {
        return new GaloisElement(base.subtract(value), base);
    }

    public String toString(){
        return value.toString()+ ", " + base.toString();
    }

    public Matrix getAddTable(int maxY, int maxX){
        Z[][] addTable;
        addTable = new Z[maxY][maxX];

        for(int y=0; y<maxY; y++){
            for(int x=0; x<maxX; x++)
                addTable[y][x] = (new Z(y).add(new Z(x))).mod(base);
        }
        return new Matrix(addTable);
    }

    public Matrix getMultiplyTable(int maxY, int maxX){
        Z[][] multiplyTable;
        multiplyTable = new Z[maxY][maxX];

        for(int y=0; y<maxY; y++){
            for(int x=0; x<maxX; x++)
                multiplyTable[y][x] = (new Z(y).multiply(new Z(x))).mod(base);
        }
        return new Matrix(multiplyTable);
    }

    public Z getGaloisElemValue() {
        return this.value;
    }
}
