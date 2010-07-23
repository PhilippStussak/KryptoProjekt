/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 *
 * Runs the Fermat primality test for natural numbers (PrimeType Z).
 *
 * @author Michael
 */
public class FermatZ extends FermatTest<Z>{

    /**
     * Constructs a new FermatZ object for natural numbers (PrimeType Z) by using the given argumets.
     *
     * @param bases Bases to be used for the Fermat-Test per modul
     * @param moduls determine if these numbers are probably primes
     * @param calcProb true if 'moduls' is probably prime, otherwise false
     */
    public FermatZ(Collection<Z> bases, Collection<Z> moduls, boolean calcProb){
        super(bases, moduls, calcProb);
    }

    /**
     * Starts the Fermat-Test for natural numbers.
     *
     * @return List of results by using the Fermat-Test (whether 'modul' is probably prime, probability, intermediate values).
     * @throws IllegalArgumentException if the paramters are incorrect (bases have to be: 1 < base < moduls, moduls have to be: 1 < modul > bases)
     */
    public ArrayList<Triple<Boolean, Double, LinkedList<String>>> test()
        throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        
        boolean checkPrimeArgAnswer = checkPrimeArguments().first();
        String argsCorrectMessage = checkPrimeArguments().second();

        ArrayList<Triple<Boolean, Double, LinkedList<String>>> primeResult = new ArrayList<Triple<Boolean, Double, LinkedList<String>>>();
        if (checkPrimeArgAnswer) {
            double probability = calculateProbability();
            for (Z checkPrime : moduls){
                boolean isPrime = fermatCheck(checkPrime);
                    if (isPrime){
                        if (calcProb) {
                            //Postcondition
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == true: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, probability, intermediateValues));
                            continue;
                        } else{
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, -1.0, intermediateValues)); //return no probability
                            continue;
                        }
                    } else{
                        //Postcondition
                        assert checkPrimeArgAnswer == true && isPrime == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                        if(calcProb){
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(false, 1.0, intermediateValues));
                            continue;
                        }else{
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(false, -1.0, intermediateValues));
                            continue;
                        }
                    }
            }
            return primeResult;
        } else{
            throw new IllegalArgumentException(argsCorrectMessage);
        }
    }

    //checks whether the parameter values are correct: probably prime greater than 1 and base '1 < base < modul'
    private Tuple<Boolean, String> checkPrimeArguments()
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        //Precondition
        assert Set.class.isAssignableFrom(bases.getClass()): "check that bases contains no dublicate elements: Liste hs = " +bases;

        boolean argsCorrect = true;
        String argsAnswer = "Arguments are correct.";
        Z one = new Z("1");
        Z two = new Z("2");

        //checks whether 'bases' > 1 && bases < checkPrime
        if (argsCorrect && !bases.isEmpty()){
            if (getLowestBase().compareTo(new Z(1)) < 1) {
                argsCorrect = false;
                argsAnswer = "Base 'a' too small. Fermat-Test requires a base:  1 < a < prime";
            }
            //if the smallest modul is 2, go to the next else-if
            if (getHighestBase().compareTo(getLowestModul())>=0 && !getLowestModul().equals(two)){
                argsCorrect = false;
                argsAnswer = "Base 'a' too large. Fermat-Test requires a base:  1 < a < prime";
            }else if(getHighestBase().compareTo(getLowestModul())>=0 && getLowestModul().equals(two)){
                if(moduls.size() > 1){
                    Iterator<Z> itModuls = moduls.iterator();
                    itModuls.next();
                    if(getHighestBase().compareTo(itModuls.next()) >=0){
                        argsCorrect = false;
                        argsAnswer = "Base 'a' too large. Fermat-Test requires a base:  1 < a < prime";
                    }
                }
            }
        }else if(argsCorrect && bases.isEmpty()){
            argsCorrect = false;
            argsAnswer = "It requires at least one base >1 and <n.";
        }

        //checks whether the probably primes are greater than 1
        if (argsCorrect && getLowestModul().compareTo(new Z(1)) <= 0) {
            argsCorrect = false;
            argsAnswer = "There are only prime numbers >1";
        }

        //Postcondition
        assert getLowestModul().compareTo(new Z(1)) >0: "checkprime isn't > 1: checkPrime = " +getLowestModul();
        assert getLowestBase().compareTo(new Z(1)) >0 || getLowestModul().equals(new Z(2)): "base isn't > 1: base = " +getLowestBase();
        return new Tuple<Boolean, String>(argsCorrect, argsAnswer);
    }

    //checks wheter the parameter is a prime number
    private boolean fermatCheck(Z checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        boolean isPrime = false;
        intermediateValues = new LinkedList<String>();

        Z twoObj = new Z(2);
        if(!checkPrime.equals(twoObj)){
            //The new instance is initialized to 1. This is the value which will be subtracted from the exponent.
            Z oneObj = new Z(1);
            Z result;
            int assertPostCondCounter = 0;
            for (Z base : bases) {
                ++assertPostCondCounter;
                result = Basic.squareAndMultiply(base, checkPrime.subtract(oneObj), checkPrime).first();
                isPrime = result.isONE();
                intermediateValues.add(base+ "^" +checkPrime.subtract(oneObj)+ " mod " +checkPrime+ " = " +result);
                if (!isPrime) {
                    //Postcondition
                    assert isPrime == false: "isPrime has a false state: isPrime = " +isPrime;
                    return false;
                }
                //Postcondition
                assert isPrime == true: "isPrime has a false state: isPrime = " +isPrime;
                continue;
            }
            //Postcondition
            assert assertPostCondCounter == bases.size(): "There were not tested all bases on 'true'";
            return true;
        } else{
            //Precondition
            assert Integer.parseInt(checkPrime.toString()) == 2: "Error, checkPrime != 2. checkPrime: " +checkPrime.toString();
            intermediateValues.add(checkPrime+ " = 1");
            return true;
        }
    }
}