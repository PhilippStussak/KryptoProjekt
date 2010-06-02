/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.LinkedList;

/**
 *
 * @author phil
 */
public class Main {

    public static void main(String[] args) {
        LinkedList<Z[]> list = Basic.gcd(new Z(123), new Z(321)).second();
        for(KryptoType[] field : list)
            System.out.println(field[0] + " = " + field[1] + " * " + field[2] + " + " + field[3]);
    }

}
