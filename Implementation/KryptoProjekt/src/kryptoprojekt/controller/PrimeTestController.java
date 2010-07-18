/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.controller;

import kryptoprojekt.model.HammingCode;
import kryptoprojekt.model.Matrix;
import kryptoprojekt.model.PrimeFieldElement;
import kryptoprojekt.model.Z;
import kryptoprojekt.model.Polynom;
import java.util.ArrayList;
import kryptoprojekt.model.Tuple;
import kryptoprojekt.model.Triple;
import kryptoprojekt.model.KryptoType;
import kryptoprojekt.model.FermatZ;
import kryptoprojekt.model.MillerRabinZ;
import kryptoprojekt.model.LucasZ;
import kryptoprojekt.model.PrimeTest;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
/**
 *
 * @author Michael
 */
public class PrimeTestController {

    private PrimeTestController() {
    }


    //Fermat Test
    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> primeTestFermat(ArrayList<KryptoType> bases, ArrayList<KryptoType> moduls, boolean calcProb) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        if (bases.get(0).getClass().equals(Z.class) && (bases.get(0).getClass().equals(Z.class))){
            ArrayList<Z> basesZ = new ArrayList<Z>();
            ArrayList<Z> modulsZ = new ArrayList<Z>();
  
            for(KryptoType<Z> base : bases){
                basesZ.add((Z)base);
            }
            for(KryptoType<Z> modul : moduls){
                modulsZ.add((Z)modul);
            }
            return fermatZPrimeTest(new FermatZ(basesZ, modulsZ, calcProb));
            
        }else if (bases.get(0).getClass().equals(Polynom.class) && (bases.get(0).getClass().equals(Polynom.class))){
            throw new UnsupportedOperationException("Not supported yet.");
        }else{
            throw new IllegalArgumentException("Kryptotypes are different. You have to enter the same Kryptotypes. e.g. Z, Z");
        }
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> fermatZPrimeTest(FermatZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        return fermatZArguments.test2();
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> fermatPolynomTest(FermatZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        throw new UnsupportedOperationException("Not supported yet.");
    }


    //Miller-Rabin Test
    public static ArrayList<Tuple<Boolean, Double>> primeTestRabin(ArrayList<KryptoType> bases, ArrayList<KryptoType> moduls) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        if (bases.get(0).getClass().equals(Z.class) && (bases.get(0).getClass().equals(Z.class))){
            ArrayList<Z> basesZ = new ArrayList<Z>();
            ArrayList<Z> modulsZ = new ArrayList<Z>();

            for(KryptoType<Z> base : bases){
                basesZ.add((Z)base);
            }
            for(KryptoType<Z> modul : moduls){
                modulsZ.add((Z)modul);
            }
            return rabinZPrimeTest(new MillerRabinZ(basesZ, modulsZ, true));

        }else if (bases.get(0).getClass().equals(Polynom.class) && (bases.get(0).getClass().equals(Polynom.class))){
            throw new UnsupportedOperationException("Not supported yet.");
        }else{
            throw new IllegalArgumentException("Kryptotypes are different. You have to enter the same Kryptotypes. e.g. Z, Z");
        }
    }

    public static ArrayList<Tuple<Boolean, Double>> rabinZPrimeTest(MillerRabinZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        return fermatZArguments.test();
    }

    public static ArrayList<Tuple<Boolean, Double>> rabintPolynomTest(MillerRabinZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        throw new UnsupportedOperationException("Not supported yet.");
    }



    //Lucas Test
    public static ArrayList<Tuple<Boolean, Double>> primeTestLucas(ArrayList<Triple<ArrayList<Z> , ArrayList<Z>, ArrayList<Z>>> primeFactorsCollection, ArrayList<Tuple<Z , Z>> summandCollection, boolean calcProp) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        for (Triple<ArrayList<Z>, ArrayList<Z>, ArrayList<Z>> triples : primeFactorsCollection){ //ACHTUNG, FALSCH, es atand überall E da
            if(triples.first().getClass().equals(Z.class) && triples.second().getClass().equals(Z.class)){
                return lucasZPrimeTest(new LucasZ(primeFactorsCollection, summandCollection, true));
                //return rabinZPrimeTest(new LucasZ(basesZ, modulsZ, true));
            } else if(triples.first().getClass().equals(Z.class) && triples.second().getClass().equals(Z.class)){
                throw new UnsupportedOperationException("Not supported yet.");
            }else{
                throw new IllegalArgumentException("Kryptotypes are different. You have to enter the same Kryptotypes. e.g. Z, Z");
            }
        }
        throw new IllegalArgumentException("This line should never been reached");
        //return null; //dürfte niemals erreicht werden
    }    

    public static ArrayList<Tuple<Boolean, Double>> lucasZPrimeTest(LucasZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        return fermatZArguments.test();
    }

    public static ArrayList<Tuple<Boolean, Double>> lucasPolynomTest(LucasZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
