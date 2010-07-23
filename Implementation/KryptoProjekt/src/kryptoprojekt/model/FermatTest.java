/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.*;

/**
 * This is the superclass for all different KryptoTypes (Z, polynomial etc.) Fermat primality test subclasses.
 *
 * @author Michael
 */
 abstract class FermatTest <E extends KryptoType<E>> implements PrimeTest<E>{
    protected TreeSet<E> bases;
    protected TreeSet<E> moduls;
    //contains the intermediate values of the Fermat-Test
    protected LinkedList<String> intermediateValues;
    //true to calculate and return the probability, otherwise false
    protected boolean calcProb;
    //probability can't be calculated at Fermat-Test
    protected static final double probabilityValue = -2;


    protected FermatTest(Collection<E> bases, Collection<E> modul, boolean calcProb){
        this.bases = new TreeSet<E>(bases);
        this.moduls = new TreeSet<E>(modul);
        this.calcProb = calcProb;
    }
    
    protected double calculateProbability(){
       return probabilityValue;
    }
    
    protected E getHighestBase(){
        return bases.last();
    }

    protected E getLowestBase(){
        return bases.first();
    }

    protected E getHighestModul(){
        return moduls.last();
    }

    protected E getLowestModul(){
        return moduls.first();
    }
}