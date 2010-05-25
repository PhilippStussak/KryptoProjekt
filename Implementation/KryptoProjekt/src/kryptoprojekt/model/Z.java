/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

import java.math.BigInteger;

/**
 *
 * @author Stefan
 */
public class Z implements KryptoType<Z> {

    private BigInteger number;

    private Z(BigInteger number) {
        this.number = number;
    }

    public Z(long number) {
        this.number = BigInteger.valueOf(number);
    }

    public Z(String number) {
        this.number = new BigInteger(number);
    }

    public Z add(Z other) {
        return new Z(number.add(other.number));
    }

    public Z subtract(Z other) {
        return new Z(number.subtract(other.number));
    }

    public Z multiply(Z other) {
        return new Z(number.multiply(other.number));
    }

    public Z divide(Z other) {
        return new Z(number.divide(other.number));
    }

    public Z mod(Z other) {
        return new Z(number.mod(other.number));
    }

    public boolean isONE() {
        return number.equals(BigInteger.ONE);
    }

    public boolean isZERO() {
        return number.equals(BigInteger.ZERO);
    }

    public int compareTo(Z other) {
        return number.compareTo(other.number);
    }

    public boolean equals(Z other) {
        return number.equals(other.number);
    }

    public int intValue() {
        return number.intValue();
    }

    public String toBinaryString() {
        String result = "";
        for (int i = number.bitLength() - 1; i >= 0; i--) {
            result += (number.testBit(i)) ? "1" : "0";
        }
        return result;
    }

    @Override
    public String toString() {
        return number.toString();
    }
}
