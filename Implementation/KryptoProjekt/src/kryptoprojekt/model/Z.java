package kryptoprojekt.model;

import java.math.BigInteger;

/**
 * This Class is a wrapper for BigInteger
 * and implements the interface Kryptotype for generic use.
 * It uses an internal BigInteger
 * and has some arithmetics which are similar to those of BigInteger.
 *
 * @author Stefan
 */
public class Z implements KryptoType<Z> {

    private BigInteger number;

    /**
     * This Constructor is for internal use,
     * if arithmetic instructions are used.
     * Sets the value of a new instance of Z.
     *
     * @param number the value of the new instance of Z.
     */
    private Z(BigInteger number) {
        this.number = number;
    }

    /**
     * Translates the long representation
     * of an integer number to a new Z instance.
     *
     * @param number long representation of Z.
     */
    public Z(long number) {
        this.number = BigInteger.valueOf(number);
    }

    /**
     * Translates the String representation
     * of an integer number to a new Z instance.
     *
     * @param number String representation of Z.
     */
    public Z(String number) {
        this.number = new BigInteger(number);
    }

    /**
     * Returns a new Z whose value is {@code this + val}
     *
     * @param other value to be added to this Z.
     * @return {@code this + val}
     * @see java.math.BigInteger#add(java.math.BigInteger)
     */
    public Z add(Z other) {
        return new Z(number.add(other.number));
    }

    /**
     * Returns a new Z whose value is {@code this - val}
     *
     * @param other value to be subtracted from this Z.
     * @return {@code this - val}
     * @see java.math.BigInteger#subtract(java.math.BigInteger)
     */
    public Z subtract(Z other) {
        return new Z(number.subtract(other.number));
    }

    /**
     * Returns a new Z whose value is {@code this * val}
     *
     * @param other value to be multiplied with this Z.
     * @return {@code this * val}
     * @see java.math.BigInteger#multiply(java.math.BigInteger)
     */
    public Z multiply(Z other) {
        return new Z(number.multiply(other.number));
    }

    /**
     * Returns a new Z whose value is {@code this / val}
     *
     * @param other value by which this Z is to be divided.
     * @return {@code this / val}
     * @see java.math.BigInteger#divide(java.math.BigInteger)
     */
    public Z divide(Z other) {
        return new Z(number.divide(other.number));
    }

    /**
     * Returns a new Z whose value is {@code this % val}.
     * Returns a non-negative Z.
     *
     * @param other value by which this Z is to be divided,
     * and the remainder is calculated.
     * @return {@code this % val}
     * @see java.math.BigInteger#mod(java.math.BigInteger)
     */
    public Z mod(Z other) {
        return new Z(number.mod(other.number));
    }

    /**
     * Ckecks if the value of this Z is equals to 1.
     *
     * @return {@code this == 1}
     */
    public boolean isONE() {
        return number.equals(BigInteger.ONE);
    }

    /**
     * Ckecks if the value of this Z is equals to 0.
     *
     * @return {@code this == 0}
     */
    public boolean isZERO() {
        return number.equals(BigInteger.ZERO);
    }

    /**
     * Compares this Z with the spezified other Z.
     *
     * @param other Z to which this Z is to be compared.
     * @return -1, 0 or 1 as this Z is numerically less than, equal
     *         to, or greater than {@code other}.
     * @see java.math.BigInteger#compareTo(java.math.BigInteger)
     */
    public int compareTo(Z other) {
        return number.compareTo(other.number);
    }

    /**
     * Compares the value of this Z and the spezified other Z for equality.
     *
     * @param other Object to which this is to be compared.
     * @return {@code true} if the value of this
     * and other are numerically equal.
     * @see java.math.BigInteger#equals(java.math.BigInteger)
     */
    @Override
    public boolean equals(Object other) {
        if(this == other)
            return true;
        if(other instanceof Z) {
            Z value = (Z)other;
            return number.equals(value.number);
        }
        return false;
    }

    /**
     * Generates the hashcode of this Z.
     *
     * @return the hashcode of the value of this Z.
     * @see java.math.BigInteger#equals(java.math.BigInteger)
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.number != null ? this.number.hashCode() : 0);
        return hash;
    }

    /**
     * Returns an int representation of this value.
     *
     * @return int value of this Z.
     * @see java.math.BigInteger#intValue()
     */
    public int intValue() {
        return number.intValue();
    }

    /**
     * Returns a String which is a bit representation of the value of this Z.
     *
     * @return String with the binary representation of this Z.
     */
    public String toBinaryString() {
        String result = "";
        for (int i = number.bitLength() - 1; i >= 0; i--) {
            result += (number.testBit(i)) ? "1" : "0";
        }
        return result;
    }

    /**
     * Returns a String representation of this Z.
     *
     * @return String which represents the value of this Z.
     * @see java.math.BigInteger#toString()
     */
    @Override
    public String toString() {
        return number.toString();
    }
}
