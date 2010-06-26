/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.InvocationTargetException;
import java.util.*;

//Macht den Fermat Test für Z (ganze Zahlen)
public class FermatZ extends FermatTest<Z>{

    /*
     * Erzeugt ein Fermat-Test Objekt für den PrimeType Z.
     * Erwartet eine beliebige Liste mit Basen und Zahlen die auf Primzahleigenschaft getestet werden.
     */
    FermatZ(Collection<Z> bases, Collection<Z> moduls, boolean calcProp){
        super(bases, moduls, calcProp);
    }


    public ArrayList<Tuple<Boolean, Double>> test()
        throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        boolean checkPrimeArgAnswer = checkPrimeArguments();
        ArrayList<Tuple<Boolean, Double>> primeResult = new ArrayList<Tuple<Boolean, Double>>();
        if (checkPrimeArgAnswer) {
            double probability = calculateProbability();
            for (Z checkPrime : moduls){
                boolean isPrime = fermatCheck(checkPrime);
                    if (isPrime){
                        if (calcProp) {
                            //Postcondition
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProp == true: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Tuple<Boolean, Double>(isPrime, probability));
                            continue;
                        } else{
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProp == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Tuple<Boolean, Double>(isPrime, -1.0)); //es sollte keine Wahrscheinlichkeit berechnet werden
                            continue;
                        }
                    } else{
                        //Postcondition
                        assert checkPrimeArgAnswer == true && isPrime == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                        primeResult.add(new Tuple<Boolean, Double>(false, 1.0));
                        continue;
                    }
            }
            return primeResult;
        }
        //Postcondition
        assert false: "This line should never be reached! checkPrimeArgAnswer = " +checkPrimeArgAnswer;
        return null;
    }

    //checkt ob die übergebenen Werte: Primzahl größer 0 und die Basis '0 < a < Modul' sind
    private boolean checkPrimeArguments()
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        //Precondition
        assert Set.class.isAssignableFrom(bases.getClass()): "check that bases contains no dublicate elements: Liste hs = " +bases;
        if (bases.isEmpty()){
            throw new IllegalArgumentException("Es wird mind. eine Basis >0 und <n benötigt.");
        }

        if (getLowestModul().compareTo(new Z(1)) <= 0) { //prüft ob Primzahl größer 1 ist
            throw new IllegalArgumentException("Es gibt nur Primzahlen >1");
        }
        //prüft ob bases > 0 && bases < checkPrime ist
        if (getLowestBase().compareTo(new Z(1)) < 1) {
            throw new IllegalArgumentException("Basis zu klein. Sie muss bei Miller-Rabin sein:  1 < a < Modul");
        }
        else if (getHighestBase().compareTo(getHighestModul())>0){
            throw new IllegalArgumentException("Basis zu groß. Sie muss bei Miller-Rabin sein:  1 < a < Modul");
         }
        //Postcondition
        assert getLowestModul().compareTo(new Z(1)) >0: "checkprime isn't > 1: checkPrime = " +getLowestModul();
        assert getLowestBase().compareTo(new Z(1)) >0: "base isn't > 1: base = " +getLowestBase();
        return true;
    }

    private boolean fermatCheck(Z checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        boolean isPrime = false;

        Z oneObj = new Z(1);
        int assertPostCondCounter = 0;
        for (Z base : bases) {
            ++assertPostCondCounter;
            isPrime = Basic.squareAndMultiply(base, checkPrime.subtract(oneObj), checkPrime).first().isONE();
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
    }
}



