/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 * Can be used to create an object which has three parameters of any type.
 *
 * @author Michael
 */
public class Triple<F, S, T>{

    private F first;
    private S second;
    private T third;

    public Triple(F first, S second, T third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public F first() {
        return first;
    }

    public S second() {
        return second;
    }

    public T third() {
        return third;
    }
}