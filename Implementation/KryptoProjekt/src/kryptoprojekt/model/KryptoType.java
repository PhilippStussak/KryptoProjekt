/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author stefan
 */
public interface KryptoType<E> extends Comparable<E> {

    E add(E other);

    E subtract(E other);

    E multiply(E other);

    E divide(E other);

    E mod(E other);

    String toBinaryString();

    boolean isZERO();

    boolean isONE();
}
