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

	public Z add(Z e) {
		return new Z(number.add(e.number));
	}

	public Z multiply(Z e) {
		return new Z(number.multiply(e.number));
	}

	public Z divide(Z e) {
		return new Z(number.divide(e.number));
	}

	public Z mod(Z e) {
		return new Z(number.mod(e.number));
	}

	public boolean isONE() {
		return number.equals(BigInteger.ONE);
	}

	public boolean isZERO() {
		return number.equals(BigInteger.ZERO);
	}

        public boolean equals(Z other) {
		return number.equals(other.number);
	}

        @Override
        public String toString() {
		return number.toString();
	}

	public int compareTo(Z other) {
		return number.compareTo(other.number);
	}

}