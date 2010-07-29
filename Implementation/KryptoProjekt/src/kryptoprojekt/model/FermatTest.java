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
    protected LinkedHashSet<E> bases;
    protected LinkedHashSet<E> moduls;
    //contains the intermediate values of the Fermat-Test
    protected LinkedList<String> intermediateValues;
    //true to calculate and return the probability, otherwise false
    protected boolean calcProb;
    //probability can't be calculated at Fermat-Test
    protected static final double probabilityValue = -2;
    //contains bases in sorted order and without duplicates
    protected TreeSet<E> basesTree;
    protected TreeSet<E> modulsTree;


    protected FermatTest(Collection<E> bases, Collection<E> modul, boolean calcProb){
        this.bases = new LinkedHashSet<E>(bases);
        this.basesTree = new TreeSet<E>(bases);
        this.moduls = new LinkedHashSet<E>(modul);
        this.modulsTree = new TreeSet<E>(modul);
        this.calcProb = calcProb;
    }
    
    protected double calculateProbability(){
       return probabilityValue;
    }
    
    protected E getHighestBase(){
        return basesTree.last();
    }

    protected E getLowestBase(){
        return basesTree.first();
    }

    protected E getHighestModul(){
        return modulsTree.last();
    }

    protected E getLowestModul(){
        return modulsTree.first();
    }
}