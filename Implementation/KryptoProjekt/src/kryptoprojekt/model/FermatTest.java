/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.util.*;

//Ist die Oberklasse für alle verschiedenen KryptoTypen die einen Fermat Test machen können.
public abstract class FermatTest <E extends KryptoType<E>> implements PrimeTest<E> {
    protected TreeSet<E> bases;
    protected TreeSet<E> moduls;
    protected boolean calcProp;
    protected static final double probabilityValue = 0;


    protected FermatTest(Collection<E> bases, Collection<E> modul, boolean calcProp){
        this.bases = new TreeSet<E>(bases);
        this.moduls = new TreeSet<E>(modul);
        this.calcProp = calcProp;
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
