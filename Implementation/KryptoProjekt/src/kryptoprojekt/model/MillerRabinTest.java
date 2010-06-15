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


//Ist die Oberklasse für alle verschiedenen KryptoTypen die einen Miller-Rabin-Test machen können.
public abstract class MillerRabinTest <E extends KryptoType<E>> implements PrimeTest<E> {
    protected TreeSet<E> bases;
    protected TreeSet<E> moduls;
    protected boolean calcProp;
    protected static final double probabilityValue = 0.25;


    protected MillerRabinTest(Collection<E> bases, Collection<E> modul, boolean calcProp) {
        this.bases = new TreeSet<E>(bases);
        this.moduls = new TreeSet<E> (modul);
        this.calcProp = calcProp;
    }

    /* Bestimmt die Faktoren einer geraden Zahl und gibt die Potenz zur 2 und den ungeraden Faktor aus.
     * Konnte die Zahl nicht in Faktoren zerlegt werden, wird -1, -1 zurückgegeben.
     * Falls keine gerade Zahl übergeben wurde, wird eine Exception geworfen.
     */
    protected Tuple<E, E> factorizeEven(E evenNumber){
        //Precondition
        assert evenNumber.compareTo((E) Factory.newInstance(evenNumber.getClass(), "1")) > 0 : "An even number cannot be less than <2. argument passed: " +evenNumber;
        assert evenNumber.mod((E) Factory.newInstance(evenNumber.getClass(), "2")).compareTo((E) Factory.newInstance(evenNumber.getClass(), "0")) == 0 : "It is not an even number. number passed: " +evenNumber;

        E dividend;
        final E divisor = (E) Factory.newInstance(evenNumber.getClass(), "2");
        E quotient = evenNumber;
        int power = 0;
        if (!quotient.mod(divisor).isZERO()){
            throw new IllegalArgumentException("It is not an even number. number passed: " +quotient);
        }
        while(quotient.mod(divisor).isZERO()){
            dividend = quotient;
            quotient = dividend.divide(divisor);
            ++power;
            assert evenNumber.compareTo(Basic.squareAndMultiply(divisor, (E) Factory.newInstance(evenNumber.getClass(), String.valueOf(power)))) >=0: "Too many Iterations. even number: " +evenNumber+ ", " +power+ " to the power of " +divisor+": " +Basic.squareAndMultiply(divisor, (E) Factory.newInstance(evenNumber.getClass(), String.valueOf(power)));
        }
        if (Basic.squareAndMultiply(divisor, (E) Factory.newInstance(evenNumber.getClass(), String.valueOf(power))).multiply(quotient).compareTo(evenNumber) == 0) {
            //Postcondition
            assert ((E) Factory.newInstance(evenNumber.getClass(), String.valueOf(power))).compareTo((E) Factory.newInstance(evenNumber.getClass(), "0")) >=0 || ((E) Factory.newInstance(evenNumber.getClass(), String.valueOf(quotient))).compareTo((E) Factory.newInstance(evenNumber.getClass(), "0")) >0:"power or quotient(odd number) have a wrong value. power: " +power+ ", quotient: " +quotient;
            return new Tuple((E) Factory.newInstance(evenNumber.getClass(), String.valueOf(power)), quotient);
        } else{
            return new Tuple((E) Factory.newInstance(evenNumber.getClass(), "-1"), (E) Factory.newInstance(evenNumber.getClass(), "-1"));
        }
    }

    protected double calculateProbability(Set<E> bases)
        throws IllegalArgumentException {
        int numbOfBases; //beinhaltet die Anzahl der übergebenen Basen
        double probability; //behinhaltet die Wahrscheinlichkeit das es sich um eine Primzahl handelt

        assert bases != null: "bases is null";
        assert !bases.isEmpty(): "No bases were passed.";
        assert probabilityValue <=1: "probabilityValue isn't <= 1: probabilityValue = " +probabilityValue;
        assert probabilityValue >=0: "probabilityValue isn't >= 0: probabilityValue = " +probabilityValue;
        assert probabilityValue == 0.25: "probabilityValue ist bei Miller-Rabin Test 0.25. Eingestellt ist aber: " +probabilityValue;
        assert bases.size() >0: "bases has no elements";
        numbOfBases = bases.size();
        probability = 1;
        for (int i = 1; i <= numbOfBases; i++ ) {
            probability = probability * probabilityValue;
        }
        //Postcondition
        double assertProp; //Hilfsvariable für die assert Anweisung
        assert (assertProp = Math.pow(probabilityValue, bases.size())) == probability: "Inkonsistenz bei probability Werten: probability = " +probability+ ", assertProp = " +assertProp;
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