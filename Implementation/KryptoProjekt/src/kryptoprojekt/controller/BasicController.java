/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.controller;

import java.util.LinkedList;
import kryptoprojekt.model.Basic;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.PrimeFieldElement;
import kryptoprojekt.model.Tuple;
import kryptoprojekt.model.Z;

/**
 *
 * @author phil
 */
public class BasicController {
    private BasicController(){}

    public static Tuple<Z, LinkedList<Z>> calculatePhi(Z z1){
        return Basic.phi(z1);
    }

    public static Tuple calculateGCD(KryptoType kt1, KryptoType kt2){
        return Basic.gcd(kt1,kt2);
    }

    public static <E extends KryptoType<E>> E calculateModulo(E kt1, E kt2){
        return kt1.mod(kt2);
    }

    public static <E extends KryptoType<E>> E division(E kt1, E kt2){
        return kt1.divide(kt2);
    }

    public static <E extends KryptoType<E>> E subtraction(E kt1, E kt2){
        return kt1.subtract(kt2);
    }

    public static <E extends KryptoType<E>> E addition(E kt1, E kt2){
        return kt1.add(kt2);
    }

    public static <E extends KryptoType<E>> E multiplication(E kt1, E kt2){
        return kt1.multiply(kt2);
    }

    public static PrimeFieldElement createPrimeFieldElement(Z z1, Z z2){
        return new PrimeFieldElement(z1, z2);
    }

    public static <E extends KryptoType, F extends KryptoType> Tuple<KryptoType<E>, LinkedList<String>> squareAndMultiply(E kt1, F kt2){
        return Basic.squareAndMultiply(kt1, kt2);
    }

    public static <E extends KryptoType> Tuple<KryptoType<E>, LinkedList<String>> squareAndMultiply(E kt1, E kt2, E kt3){
        return Basic.squareAndMultiply(kt1, kt2, kt3);
    }
}
