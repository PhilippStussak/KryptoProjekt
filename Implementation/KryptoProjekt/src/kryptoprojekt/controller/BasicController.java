/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.controller;

import java.util.LinkedList;
import kryptoprojekt.Kit.DropTextField;
import kryptoprojekt.model.Basic;
import kryptoprojekt.model.KryptoType;
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

    public static Tuple calculateGCD(DropTextField field1, DropTextField field2){
        Tuple result = Basic.gcd((KryptoType) field1.getResult(), (KryptoType) field2.getResult());
        return result;
    }

    public static <E extends KryptoType<E>> E calculateModulo(E kt1, E kt2){
        return kt1.mod(kt2);
    }

    public static KryptoType division(DropTextField field1, DropTextField field2){
        Object result = ((KryptoType)field1.getResult()).divide((KryptoType)field2.getResult());
        return (KryptoType) result;
    }

    public static KryptoType subtraction(DropTextField field1, DropTextField field2){
        Object result = ((KryptoType)field1.getResult()).subtract((KryptoType)field2.getResult());
        return (KryptoType) result;
    }

    public static KryptoType addition(DropTextField field1, DropTextField field2){
        Object result = ((KryptoType)field1.getResult()).add((KryptoType)field2.getResult());
        return (KryptoType) result;
    }

    public static KryptoType multiplication(DropTextField field1, DropTextField field2){
        Object result = ((KryptoType)field1.getResult()).multiply((KryptoType)field2.getResult());
        return (KryptoType) result;
    }

}
