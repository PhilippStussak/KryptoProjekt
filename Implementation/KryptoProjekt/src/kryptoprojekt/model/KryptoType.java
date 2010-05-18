/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author Stefan
 */
public interface KryptoType<E> extends Comparable<E> {

    E add(E e);
    E multiply(E e);
    E divide(E e);
    E mod(E e);
    boolean isZERO();
    boolean isONE();

}
