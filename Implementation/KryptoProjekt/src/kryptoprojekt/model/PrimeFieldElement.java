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

    /**
     * Integer Constructor for PrimeFieldelement
     * @param Value and Base of the Element, both Integer
     * @throws Runtime Exception if Base is not prime
     */
    public PrimeFieldElement(int value, int base) {
        if (new Z(base).isPrime()) {
            this.base = new Z(base);
            this.value = new Z(value).mod(this.base);
        } else {
            throw new RuntimeException("Base is not prime!");
        }
    }

    /**
     * Z Constructor for PrimeFieldelement
     * @param Value and Base of the Element, both Z
     * @throws Runtime Exception if Base is not prime
     */
    public PrimeFieldElement(Z value, Z base) {
        if (base.isPrime()) {
            this.base = base;
            this.value = value.mod(base);
        } else {
            throw new RuntimeException("Base is not prime!");
        }
    }

     /**
     * String Constructor for PrimeFieldelement
     * @param String with value and base in form: value,base
     * @throws Runtime Exception if Base is not prime
     */
    public PrimeFieldElement(String valueAndBase){
        String[] probableParam = valueAndBase.split(",");
        Z probValue = new Z(probableParam[0]);
        Z probBase = new Z(probableParam[1]);
        if (probBase.isPrime()) {
            this.base = probBase;
            this.value = probValue.mod(probBase);
        } else {
            throw new RuntimeException("Base is not prime!");
        }
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
            throw new RuntimeException("Base missmatch");
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
    public PrimeFieldElement inverseElementMultiplication() {
        return new PrimeFieldElement(Basic.squareAndMultiply(value, base.subtract(new Z(2))).first(), base);
    }

    /**
     * Returns the Element as a String
     * @return Element as a String
     */
    @Override
    public String toString() {
        return "[" + value.toString() + ", " + base.toString() + "]";
    }

    /**
     * Calculates the Addition-Table for this PrimeField based on its base
     * @param maximum rows and colums
     * @return new Matrix Add-Table for this PrimeField
     */
    public Matrix getAddTable(int maxY, int maxX) {
        PrimeFieldElement[][] addTable;
        addTable = new PrimeFieldElement[maxY][maxX];

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                addTable[y][x] = (new PrimeFieldElement(new Z(y), base).add(new PrimeFieldElement(new Z(x), base)));
            }
        }
        return new Matrix(addTable);
    }

    /**
     * Calculates the Multiply-Table for this PrimeField based on its base
     * @param maximum rows and colums
     * @return new Matrix Multply-Table for this PrimeField
     */
    public Matrix getMultiplyTable(int maxY, int maxX) {
        PrimeFieldElement[][] multiplyTable;
        multiplyTable = new PrimeFieldElement[maxY][maxX];

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                multiplyTable[y][x] = (new PrimeFieldElement(new Z(y), base).multiply(new PrimeFieldElement(new Z(x), base)));
            }
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

    public Z getPrimeElemBase() {
        return this.base;
    }

    @Override
    public boolean equals (Object o){
        if(this == o){
            return true;
        }

        if(o instanceof PrimeFieldElement){
            PrimeFieldElement pfe = (PrimeFieldElement)o;
            if (this.value.equals(pfe.value) && this.base.equals(pfe.base))
                return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (this.value != null ? this.value.hashCode() : 0);
        hash = 89 * hash + (this.base != null ? this.base.hashCode() : 0);
        return hash;
    }

    public PrimeFieldElement newInstance(String value) {
        return new PrimeFieldElement(value);
    }
}
