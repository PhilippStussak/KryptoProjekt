/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.*;

/**
 * This is the superclass for all different KryptoTypes (Z, polynomial etc.) Miller-Rabin primality test subclasses.
 *
 * @author Michael
 */
abstract class MillerRabinTest <E extends KryptoType<E>> implements PrimeTest<E> {
    protected TreeSet<E> bases;
    protected TreeSet<E> moduls;
    protected LinkedList<String> intermediateValues;
    //true to calculate the probability, otherwise false
    protected boolean calcProb;
    //probablity factor that the result is a prime
    protected static final double probabilityValue = 0.25;


    protected MillerRabinTest(Collection<E> bases, Collection<E> modul, boolean calcProb) {
        this.bases = new TreeSet<E>(bases);
        this.moduls = new TreeSet<E> (modul);
        this.calcProb = calcProb;
    }

    /* Determine the factors of an even number and returns the power to the base 2 an the odd factor.
     * If the number can't broken into factors, -1 is returned.
     * If non even number was passed, an exception will thrown.
     */
    protected Tuple<E, E> factorizeEven(E evenNumber){
        //Precondition
        assert evenNumber.compareTo(evenNumber.newInstance("1")) > 0 : "An even number cannot be less than <2. argument passed: " +evenNumber;

        E dividend;
        final E divisor = evenNumber.newInstance("2");
        E quotient = evenNumber;
        int power = 0;
        if (!quotient.mod(divisor).isZERO()){
            throw new IllegalArgumentException("You have to pass an odd number!!!");
        }
        while(quotient.mod(divisor).isZERO()){
            dividend = quotient;
            quotient = dividend.divide(divisor);
            ++power;
            assert evenNumber.compareTo(Basic.squareAndMultiply(divisor, evenNumber.newInstance(String.valueOf(power))).first()) >=0: "Too many Iterations. even number: " +evenNumber+ ", " +power+ " to the power of " +divisor+": " +Basic.squareAndMultiply(divisor, evenNumber.newInstance(String.valueOf(power)));
        }
        if (Basic.squareAndMultiply(divisor, evenNumber.newInstance(String.valueOf(power))).first().multiply(quotient).compareTo(evenNumber) == 0) {
            //Postcondition
            assert (evenNumber.newInstance(String.valueOf(power))).compareTo(evenNumber.newInstance("0")) >=0 ||  (evenNumber.newInstance(String.valueOf(quotient))).compareTo(evenNumber.newInstance("0")) >0:"power or quotient(odd number) have a wrong value. power: " +power+ ", quotient: " +quotient;
            //quotient is the odd factor
            return new Tuple(evenNumber.newInstance(String.valueOf(power)), quotient);
        } else{
            return new Tuple(evenNumber.newInstance("-1"), evenNumber.newInstance("-1"));
        }
    }


    protected double calculateProbability(Set<E> bases)
        throws IllegalArgumentException {
        //contains the number of the passed bases
        int numbOfBases;
        //contains the probability that the number is prime
        double probability;

        assert bases != null: "bases is null";
        assert !bases.isEmpty(): "No bases were passed.";
        assert probabilityValue <=1: "probabilityValue isn't <= 1: probabilityValue = " +probabilityValue;
        assert probabilityValue >=0: "probabilityValue isn't >= 0: probabilityValue = " +probabilityValue;
        assert probabilityValue == 0.25: "probabilityValue at Miller-Rabin-Test is 0.25. but set is: " +probabilityValue;
        assert bases.size() >0: "bases has no elements";
        numbOfBases = bases.size();
        probability = 1;
        for (int i = 1; i <= numbOfBases; i++ ) {
            probability = probability * probabilityValue;
        }
        //Postcondition
        //auxiliary variable for assert statement
        double assertProb;
        assert (assertProb = Math.pow(probabilityValue, bases.size())) == probability: "Inconsistency in probability values: probability = " +probability+ ", assertProb = " +assertProb;
        return probability = (1-probability);
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