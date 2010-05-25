/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author Stefan
 */
public class Tuple<F, S> {

    private F first;
    private S second;

    public Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F first() {
        return first;
    }

    public S second() {
        return second;
    }
}
