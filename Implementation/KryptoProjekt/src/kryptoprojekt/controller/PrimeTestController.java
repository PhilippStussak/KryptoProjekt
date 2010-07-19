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

    private static XMLReader xml = XMLReader.getInstance("./languageFiles/english.xml");
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
            throw new UnsupportedOperationException(xml.getTagElement("General", "UnsupportedOperationException"));
        }else{
            throw new IllegalArgumentException(xml.getTagElement("PrimeTestController", "FalseKryptoTypes"));
        }
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> fermatZPrimeTest(FermatZ fermatZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        return fermatZArguments.test();
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> fermatPolynomTest(FermatZ fermatPolynomArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        throw new UnsupportedOperationException(xml.getTagElement("General", "UnsupportedOperationException"));
    }

    

    //Miller-Rabin Test
    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> primeTestRabin(ArrayList<KryptoType> bases, ArrayList<KryptoType> moduls, boolean calcProb) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        if (bases.get(0).getClass().equals(Z.class) && (bases.get(0).getClass().equals(Z.class))){
            ArrayList<Z> basesZ = new ArrayList<Z>();
            ArrayList<Z> modulsZ = new ArrayList<Z>();

            for(KryptoType<Z> base : bases){
                basesZ.add((Z)base);
            }
            for(KryptoType<Z> modul : moduls){
                modulsZ.add((Z)modul);
            }
            return rabinZPrimeTest(new MillerRabinZ(basesZ, modulsZ, calcProb));

        }else if (bases.get(0).getClass().equals(Polynom.class) && (bases.get(0).getClass().equals(Polynom.class))){
            throw new UnsupportedOperationException(xml.getTagElement("General", "UnsupportedOperationException"));
        }else{
            throw new IllegalArgumentException(xml.getTagElement("PrimeTestController", "FalseKryptoTypes"));
        }
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> rabinZPrimeTest(MillerRabinZ millerRabinZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        return millerRabinZArguments.test();
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> rabintPolynomTest(MillerRabinZ millerRabinPolynonArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        throw new UnsupportedOperationException(xml.getTagElement("General", "UnsupportedOperationException"));
    }



    //Lucas Test
    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> primeTestLucas(ArrayList<Triple<ArrayList<Z> , ArrayList<Z>, ArrayList<Z>>> primeFactorsCollection, ArrayList<Tuple<Z , Z>> summandCollection, boolean calcProb) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        for (Triple<ArrayList<Z>, ArrayList<Z>, ArrayList<Z>> triples : primeFactorsCollection){ //ACHTUNG, FALSCH, es atand überall E da
            if(triples.first().getClass().equals(Z.class) && triples.second().getClass().equals(Z.class)){
                return lucasZPrimeTest(new LucasZ(primeFactorsCollection, summandCollection, calcProb));
                //return rabinZPrimeTest(new LucasZ(basesZ, modulsZ, true));
            } else if(triples.first().getClass().equals(Z.class) && triples.second().getClass().equals(Z.class)){
                throw new UnsupportedOperationException(xml.getTagElement("General", "UnsupportedOperationException"));
            }else{
                throw new IllegalArgumentException(xml.getTagElement("PrimeTestController", "FalseKryptoTypes"));
            }
        }
        throw new IllegalArgumentException("This line should never been reached");
        //return null; //dürfte niemals erreicht werden
    }    

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> lucasZPrimeTest(LucasZ lucasZArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        return lucasZArguments.test();
    }

    public static ArrayList<Triple<Boolean, Double, LinkedList<String>>> lucasPolynomTest(LucasZ lucasPolynomArguments) throws RuntimeException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException{
        throw new UnsupportedOperationException(xml.getTagElement("General", "UnsupportedOperationException"));
    }

}