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
    public FermatZ(Collection<Z> bases, Collection<Z> moduls, boolean calcProb){
        super(bases, moduls, calcProb);
    }


    public ArrayList<Triple<Boolean, Double, LinkedList<String>>> test2()
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
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, -1.0, intermediateValues)); //es sollte keine Wahrscheinlichkeit berechnet werden
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
        //Postcondition
        //assert false: "This line should never be reached! checkPrimeArgAnswer = " +checkPrimeArgAnswer;
        //return null;
    }

    /*
    public ArrayList<Tuple<Boolean, Double>> test()
        throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        boolean checkPrimeArgAnswer = checkPrimeArguments();
        ArrayList<Tuple<Boolean, Double>> primeResult = new ArrayList<Tuple<Boolean, Double>>();
        if (checkPrimeArgAnswer) {
            double probability = calculateProbability();
            for (Z checkPrime : moduls){
                boolean isPrime = fermatCheck(checkPrime);
                    if (isPrime){
                        if (calcProb) {
                            //Postcondition
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == true: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Tuple<Boolean, Double>(isPrime, probability));
                            continue;
                        } else{
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
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
    }*/


    //muss noch überarbeitet werden. Soll so sein wie bei Lucas Test
    //checkt ob die übergebenen Werte: Primzahl größer 1 und die Basis '1 < a < Modul' sind
    private Tuple<Boolean, String> checkPrimeArguments()
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        //Precondition
        assert Set.class.isAssignableFrom(bases.getClass()): "check that bases contains no dublicate elements: Liste hs = " +bases;

        boolean argsCorrect = true;
        String argsAnswer = "Arguments are correct.";
        Z one = new Z("1");
        Z two = new Z("2");

        //prüft ob bases > 1 && bases < checkPrime ist
        if (argsCorrect && !bases.isEmpty()){
            if (getLowestBase().compareTo(new Z(1)) < 1) {
                argsCorrect = false;
                argsAnswer = "Base 'a' too small. Fermat-Test requires a base:  1 < a < prime";
            }
            if (getHighestBase().compareTo(getLowestModul())>=0 && !getLowestModul().equals(two)){  //Wenn das kleinste Modul die 2 ist, gehe zum nächsten else if
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


        //prüft ob Primzahl größer 1 ist
        if (argsCorrect && getLowestModul().compareTo(new Z(1)) <= 0) {
            argsCorrect = false;
            argsAnswer = "There are only prime numbers >1";
        }

        /*
        if (bases.isEmpty()){
            throw new IllegalArgumentException("Es wird mind. EINE Basis >1 und <n benötigt.");
        }

        if (getLowestModul().compareTo(new Z(1)) <= 0) { //prüft ob Primzahl größer 1 ist
            throw new IllegalArgumentException("Es gibt nur Primzahlen >1");
        }
        //prüft ob bases > 1 && bases < checkPrime ist
        if (!getLowestModul().equals(new Z(2))){
            if (getLowestBase().compareTo(new Z(1)) < 1) {
                throw new IllegalArgumentException("Basis 'a' zu klein. Sie muss bei Fermat-Test sein:  1 < a < Modul");
            }
            if (getHighestBase().compareTo(getLowestModul())>=0){
                throw new IllegalArgumentException("Basis 'a' zu groß. Sie muss bei Fermat-Test sein:  1 < a < Modul");
            }
        }*/
        //else if (getHighestBase().compareTo(getHighestModul())>=0){
          //  throw new IllegalArgumentException("Basis 'a' zu groß. Sie muss bei Fermat-Test sein:  1 < a < Modul");
        // }
        //Postcondition
        assert getLowestModul().compareTo(new Z(1)) >0: "checkprime isn't > 1: checkPrime = " +getLowestModul();
        assert getLowestBase().compareTo(new Z(1)) >0 || getLowestModul().equals(new Z(2)): "base isn't > 1: base = " +getLowestBase();
        return new Tuple<Boolean, String>(argsCorrect, argsAnswer);
    }

    private boolean fermatCheck(Z checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        boolean isPrime = false;
        intermediateValues = new LinkedList<String>();

        Z twoObj = new Z(2);
        if(!checkPrime.equals(twoObj)){
            Z oneObj = new Z(1); //Die neue Instanz wird mit 1 initialisiert, das ist der Wert der vom Exponenten dann subtrahiert wird
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



