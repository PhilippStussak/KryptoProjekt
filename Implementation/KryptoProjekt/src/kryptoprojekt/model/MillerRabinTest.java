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
    protected LinkedList<String> intermediateValues;
    protected boolean calcProb; //ob die Wahrscheinlichkeit berechnet werden soll
    protected static final double probabilityValue = 0.25; //Wahrscheinlichkeitsfaktor das es sich um eine Primzahl handelt


    protected MillerRabinTest(Collection<E> bases, Collection<E> modul, boolean calcProb) {
        this.bases = new TreeSet<E>(bases);
        this.moduls = new TreeSet<E> (modul);
        this.calcProb = calcProb;
    }

    /* Bestimmt die Faktoren einer geraden Zahl und gibt die Potenz zur 2 und den ungeraden Faktor aus.
     * Konnte die Zahl nicht in Faktoren zerlegt werden, wird -1 zurückgegeben.
     * Falls keine gerade Zahl übergeben wurde, wird eine Exception geworfen.
     */
    protected Tuple<E, E> factorizeEven(E evenNumber){
        //Precondition
        assert evenNumber.compareTo(evenNumber.newInstance("1")) > 0 : "An even number cannot be less than <2. argument passed: " +evenNumber;
        assert evenNumber.mod(evenNumber.newInstance("2")).compareTo(evenNumber.newInstance("0")) == 0 : "It is not even number. number passed: " +evenNumber;
//ACHTUNG: HIER MUSS ICH NOCH EINE EXCEPTION WERFEN, DASS DER USER EINE UNGERADE ZAHL EINGEBEN MUSS!!!!!!!!!!!!!!!!!!!!

        E dividend;
        final E divisor = evenNumber.newInstance("2");
        E quotient = evenNumber;
        int power = 0;
        if (!quotient.mod(divisor).isZERO()){
            throw new IllegalArgumentException("It is an even number. number passed: " +quotient);
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
            return new Tuple(evenNumber.newInstance(String.valueOf(power)), quotient); //Quotient ist der ungerade Faktor
        } else{
            return new Tuple(evenNumber.newInstance("-1"), evenNumber.newInstance("-1"));
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
        assert probabilityValue == 0.25: "probabilityValue at Miller-Rabin-Test is 0.25. but set is: " +probabilityValue;
        assert bases.size() >0: "bases has no elements";
        numbOfBases = bases.size();
        probability = 1;
        for (int i = 1; i <= numbOfBases; i++ ) {
            probability = probability * probabilityValue;
        }
        //Postcondition
        double assertProb; //Hilfsvariable für die assert Anweisung
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