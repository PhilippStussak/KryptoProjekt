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

//Macht den Miller-Rabin-Test für Z (ganze Zahlen)
public class MillerRabinZ extends MillerRabinTest<Z>{


        /*
         * Erzeugt ein Miller-Rabin-Test Objekt für den PrimeType Z.
         * Erwartet eine beliebige Liste mit Basen und Zahlen die auf Primzahleigenschaft getestet werden sollen.
        */
        public MillerRabinZ(Collection<Z> bases, Collection<Z> moduls, boolean calcProb){
            super(bases, moduls, calcProb);
        }

        public ArrayList<Triple<Boolean, Double, LinkedList<String>>> test()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            
            boolean checkPrimeArgAnswer = checkPrimeArguments().first();
            String argsCorrectMessage = checkPrimeArguments().second();
            
            ArrayList<Triple<Boolean, Double, LinkedList<String>>> primeResult = new ArrayList<Triple<Boolean, Double, LinkedList<String>>>();
            if (checkPrimeArgAnswer) {
                Z zeroObj = new Z(0); //um zu testen ob es eine gerade Zahl ist (Rest muss 0 ergeben)
                Z twoObj = new Z(2); //um zu testen ob es eine gerade Zahl ist
                double probability = calculateProbability(bases);
                for (Z checkPrime : moduls){
                    /*ACHTUNG
                     * Sollte ich vielleicht wegen machen dieses "if", da ja der MillerRabin-Test Algorithmus feststellen
                     * soll, dass es keine Primzahl ist und nicht durch solche Aktionen.
                     */
                    /*if (checkPrime.mod(twoObj).equals(zeroObj)&& !checkPrime.equals(twoObj)){
                        primeResult.add(new Tuple<Boolean, Double>(false, 1.0));
                        continue;
                    }*/
                    //Ende if, welche ich vielleicht entfernen möchte
                    boolean isPrime = millerRabinCheck(checkPrime);
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
                    }else{
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
                    argsAnswer = "Base 'a' too small. Miller-Rabin-Test requires a base:  1 < a < prime";
                }
                if (getHighestBase().compareTo(getLowestModul())>=0 && !getLowestModul().equals(two)){  //Wenn das kleinste Modul die 2 ist, gehe zum nächsten else if
                    argsCorrect = false;
                    argsAnswer = "Base 'a' too large. Miller-Rabin-Test requires a base:  1 < a < prime";
                }else if(getHighestBase().compareTo(getLowestModul())>=0 && getLowestModul().equals(two)){
                    if(moduls.size() > 1){
                        Iterator<Z> itModuls = moduls.iterator();
                        itModuls.next();
                        if(getHighestBase().compareTo(itModuls.next()) >=0){
                            argsCorrect = false;
                            argsAnswer = "Base 'a' too large. Miller-Rabin-Test requires a base:  1 < a < prime";
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
            //Postcondition
            assert getLowestModul().compareTo(new Z(1)) >0: "checkprime isn't > 1: checkPrime = " +getLowestModul();
            assert getLowestBase().compareTo(new Z(1)) >0 || getLowestModul().equals(new Z(2)): "base isn't > 1: base = " +getLowestBase();
            return new Tuple<Boolean, String>(argsCorrect, argsAnswer);
        }

        //Macht den Miller-Rabin-Test.
        private boolean millerRabinCheck(Z checkPrime)
                 throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            Z oneObj = new Z(1); //Die neue Instanz wird mit 1 initialisiert, das ist der Wert der vom Exponenten dann subtrahiert wird
            Z twoObj = new Z(2); //Die neue Instanz wird mit 2 initialisiert, das ist der Wert mit dem der Exponent immer multipliziert wird
            intermediateValues = new LinkedList<String>();

            if(!checkPrime.equals(twoObj)){
                Tuple<Z, Z> factors = factorizeEven(checkPrime.subtract(oneObj));  //erstes Argument: Potenz zur 2, zweites Argument: ungerader Faktor
                if(factors.first().equals(new Z(-1)) || factors.second().equals(new Z(-1))){
                    throw new RuntimeException("The passed number couldn't be factored."); //Frage: Gibt es dafür eine bessere Exception?
                }

                Z exponent = factors.first(); //beinhaltet den Exponenten nach der Faktorisierung
                Z oddFactor = factors.second(); //beinhaltet den ungeraden Faktor nach der Faktorisierung
                Z maxPower = Basic.squareAndMultiply(twoObj, exponent.subtract(oneObj)).first(); //beinhaltet die maximale Potenz mit der die checkBasis hoch oddFactor potenziert werden darf nach der Faktorisierung
                int assertPostCondCounter = 0;
                Z firstTest; //beinhaltet den Wert nach dem ersten FermatTest Schritt ob modul eine Primzahl ist
                Z result;
                nextBase:
                for (Z base : bases) {
                    ++assertPostCondCounter;
                    firstTest = Basic.squareAndMultiply(base, oddFactor, checkPrime).first();
                    intermediateValues.add(base+ "^" +oddFactor+ " mod " +checkPrime+ " = " +firstTest);
                    if((firstTest).isONE() || firstTest.equals(checkPrime.subtract(oneObj))) {
                        continue;
                    } else{
                        Z newBase = firstTest;
                        assert oddFactor.compareTo(new Z(2147483647)) <0 : "oddFactor is too large (I think). oddFactor: " +oddFactor;
                        int potenzK = 1; //ist der 'k' Exponent
                        for (Z twoFactor = twoObj; twoFactor.compareTo(maxPower) <= 0; twoFactor=twoFactor.multiply(twoObj)){
                            assert potenzK <= maxPower.intValue(): "Too many Iterations. assertZaehler: "+potenzK+ ", maxPower: " +maxPower ;
                            result = Basic.squareAndMultiply(newBase, twoFactor, checkPrime).first();
                            intermediateValues.add("(" +newBase+ ")^2^" +potenzK+ " mod " +checkPrime+ " = " +result);
                            if (result.equals(new Z(checkPrime.subtract(oneObj).toString()))){ //Wenn n-1 rauskommt, handelt es sich um eine Primzahl
                                continue nextBase;
                            }
                            ++potenzK;
                        }
                        return false; //der Test hat immer ungleich n-1 ergeben
                    }
                }
                //Postcondition
                assert assertPostCondCounter == bases.size(): "There have not tested all bases.";
                return true;
            }else{
                //Precondition
                assert Integer.parseInt(checkPrime.toString()) == 2: "Error, checkPrime != 2. checkPrime: " +checkPrime.toString();
                intermediateValues.add(checkPrime+ " = 1");
                return true;
            }
        }
}