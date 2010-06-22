/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author phil
 */
public class PrimeFieldElement implements KryptoType<PrimeFieldElement> {

    private Z value;
    private Z base;

    //ToDo: Check if ConstructorBase is Prime. Lucas Test must be implemented.

    /**
     * Integer Constructor for PrimeFieldelement
     * @param Value and Base of the Element, both Integer
     */
    public PrimeFieldElement(int value, int base) {
        this.base = new Z(base);
        this.value = new Z(value).mod(this.base);
    }

    /**
     * Z Constructor for PrimeFieldelement
     * @param Value and Base of the Element, both Z
     */
    public PrimeFieldElement(Z value, Z base) {
        this.base = base;
        this.value = value.mod(base);
    }

    /**
     * Add-Method for two PrimeFieldElements
     * @param PrimeFieldElement to be added
     * @return new PrimeFieldElement as result of the addition
     * @throws Base missmatch if bases don't match
     */
    public PrimeFieldElement add(PrimeFieldElement other) {
        if (this.base.equals(other.base)) {
            return new PrimeFieldElement((this.value.add(other.value)).mod(base), this.base);
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    /**
     * Compare-Method for two PrimeFieldElements. Values will be compared if bases match
     * @param PrimeFieldElement to be compared
     * @return  -1, 0 or 1 as this value is numerically less than, equal
     *         to, or greater than the other
     * @throws Base missmatch if bases don't match
     */
    public int compareTo(PrimeFieldElement other) {
        if (this.base.equals(other.base)) {
            return value.compareTo(other.value);
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    /**
     * Divide-Method for two PrimeFieldElements
     * @param PrimeFieldElement to be divided
     * @return new PrimeFieldElement as result of the operation
     * @throws Base missmatch if bases don't match
     */
    public PrimeFieldElement divide(PrimeFieldElement other) {
        if (this.base.equals(other.base)) {
            return this.multiply(other.inverseElementMultiplication());
        } else {
            throw new RuntimeException();
        }
    }

    /**
     * isOne checks if value is one
     * @return true if it is one, else false
     */
    public boolean isONE() {
        return value.isONE();
    }

    /**
     * isZero checks if value is zero
     * @return true if it is zero, else false
     */
    public boolean isZERO() {
        return value.isZERO();
    }

    public PrimeFieldElement mod(PrimeFieldElement other) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Multiply-Method for two PrimeFieldElements
     * @param PrimeFieldElement to be multiplied
     * @return new PrimeFieldElement as result of the operation
     * @throws Base missmatch if bases don't match
     */
    public PrimeFieldElement multiply(PrimeFieldElement other) {
        if (this.base.equals(other.base)) {
            return new PrimeFieldElement((this.value.multiply(other.value)).mod(base), this.base);
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    /**
     * Subtract-Method for two PrimeFieldElements
     * @param PrimeFieldElement to be subtracted
     * @return new PrimeFieldElement as result of the operation
     * @throws Base missmatch if bases don't match
     */
    public PrimeFieldElement subtract(PrimeFieldElement other) {
        if (this.base.equals(other.base)) {
            return this.add(other.inverseElementAddition());
        } else {
            throw new RuntimeException("Base missmatch");
        }
    }

    public String toBinaryString() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Returns the Inverse Element for the Addition in this Field
     * @return new inverse PrimeFieldElement
     */
    public PrimeFieldElement inverseElementAddition() {
        return new PrimeFieldElement(base.subtract(value), base);
    }

    /**
     * Returns the Inverse Element for the multiplycation in this Field
     * @return new inverse PrimeFieldElement
     */
    public PrimeFieldElement inverseElementMultiplication(){
        return new PrimeFieldElement(Basic.squareAndMultiply(value, base.subtract(new Z(2))), base);
    }

    /**
     * Returns the Element as a String
     * @return Element as a String
     */
    @Override
    public String toString(){
        return value.toString()+ ", " + base.toString();
    }

    /**
     * Calculates the Addition-Table for this PrimeField based on its base
     * @param maximum rows and colums
     * @return new Matrix Add-Table for this PrimeField
     */
    public Matrix getAddTable(int maxY, int maxX){
        Z[][] addTable;
        addTable = new Z[maxY][maxX];

        for(int y=0; y<maxY; y++){
            for(int x=0; x<maxX; x++)
                addTable[y][x] = (new Z(y).add(new Z(x))).mod(base);
        }
        return new Matrix(addTable);
    }

    /**
     * Calculates the Multiply-Table for this PrimeField based on its base
     * @param maximum rows and colums
     * @return new Matrix Multply-Table for this PrimeField
     */
    public Matrix getMultiplyTable(int maxY, int maxX){
        Z[][] multiplyTable;
        multiplyTable = new Z[maxY][maxX];

        for(int y=0; y<maxY; y++){
            for(int x=0; x<maxX; x++)
                multiplyTable[y][x] = (new Z(y).multiply(new Z(x))).mod(base);
        }
        return new Matrix(multiplyTable);
    }

    /**
     * Returns the value of the field
     * @return value of field
     */
    public Z getPrimeElemValue() {
        return this.value;
    }
}