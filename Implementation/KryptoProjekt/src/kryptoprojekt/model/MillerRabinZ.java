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
        public MillerRabinZ(Collection<Z> bases, Collection<Z> moduls, boolean calcProp){
            super(bases, moduls, calcProp);
        }

        public ArrayList<Tuple<Boolean, Double>> test()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            boolean checkPrimeArgAnswer = checkPrimeArguments();
            ArrayList<Tuple<Boolean, Double>> primeResult = new ArrayList<Tuple<Boolean, Double>>();

            if (checkPrimeArgAnswer) {
                Z zeroObj = new Z(0); //um zu testen ob es eine gerade Zahl ist (Rest muss 0 ergeben)
                Z twoObj = new Z(2); //um zu testen ob es eine gerade Zahl ist
                double probability = calculateProbability(bases);
                for (Z checkPrime : moduls){
                    if (checkPrime.mod(twoObj).equals(zeroObj)&& !checkPrime.equals(twoObj)){
                        primeResult.add(new Tuple<Boolean, Double>(false, 1.0));
                        continue;
                    }
                    boolean isPrime = millerRabinCheck(checkPrime);
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
                        primeResult.add(new Tuple<Boolean, Double>(false, 1.0));//es handelt sich um keine Primzahl
                        continue;
                        //return new Tuple<Boolean, Double>(false, 1.0); //es handelt sich um keine Primzahl
                    }
                }
                return primeResult;
            }
            assert false: "This line should never be reached! checkPrimeArgAnswer = " +checkPrimeArgAnswer;
            return null;
        }


        //muss noch überarbeitet werden. Soll so sein wie bei Lucas Test
        //checkt ob die übergebenen Werte: Primzahl größer 1 und die Basis '1 < a < Modul' sind
        private boolean checkPrimeArguments()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            //Precondition
            assert Set.class.isAssignableFrom(bases.getClass()): "check that bases contains no dublicate elements: Liste hs = " +bases;
            if (bases.isEmpty()){
                throw new IllegalArgumentException("Es wird mind. EINE Basis >1 und <n benötigt.");
            }
            if (getLowestModul().compareTo(new Z(1)) <= 0) { //prüft ob Primzahl größer 1 ist
                throw new IllegalArgumentException("Es gibt nur Primzahlen >1");
            }

            if (!getLowestModul().equals(new Z(2))){
                if (getLowestBase().compareTo(new Z(1)) < 1) {
                    throw new IllegalArgumentException("Basis 'a' zu klein. Sie muss bei Miller-Rabin sein:  1 < a < Modul");
                }
                if (getHighestBase().compareTo(getHighestModul())>=0){
                    throw new IllegalArgumentException("Basis 'a' zu groß. Sie muss bei Miller-Rabin sein:  1 < a < Modul");
                }
            }
            //Postcondition
            assert getLowestModul().compareTo(new Z(1)) >0: "checkprime isn't > 1: checkPrime = " +getLowestModul();
            assert getLowestBase().compareTo(new Z(1)) >0: "base isn't > 1: base = " +getLowestBase();
            return true;
        }

        //Macht den Miller-Rabin-Test.
        private boolean millerRabinCheck(Z checkPrime)
                 throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            Z firstTest; //beinhaltet den Wert nach dem ersten FermatTest Schritt ob modul eine Primzahl ist
            Z oneObj = new Z(1); //Die neue Instanz wird mit 1 initialisiert, das ist der Wert der vom Exponenten dann subtrahiert wird
            Z twoObj = new Z(2); //Die neue Instanz wird mit 2 initialisiert, das ist der Wert mit dem der Exponent immer multipliziert wird

            Tuple<Z, Z> factors = factorizeEven(checkPrime.subtract(oneObj));  //erstes Argument: Potenz zur 2, zweites Argument: ungerader Faktor
            if(factors.first().equals(new Z(-1)) || factors.second().equals(new Z(-1))){
                throw new RuntimeException("Die übergeben gerade Zahl konnte nicht faktorisiert werden."); //Frage: Gibt es dafür eine bessere Exception?
            }
            Z exponent = factors.first(); //beinhaltet den Exponenten nach der Faktorisierung
            Z oddFactor = factors.second(); //beinhaltet den ungeraden Faktor nach der Faktorisierung
            Z maxPower = Basic.squareAndMultiply(twoObj, exponent.subtract(oneObj)).first(); //beinhaltet die maximale Potenz mit der die checkBasis hoch oddFactor potenziert werden darf nach der Faktorisierung

            int assertPostCondCounter = 0;
            nextBase:
            for (Z checkBases : bases) {
                ++assertPostCondCounter;
                
                firstTest = Basic.squareAndMultiply(checkBases, oddFactor, checkPrime).first();
                if((firstTest).isONE() || firstTest.equals(checkPrime.subtract(oneObj))) {
                    continue;
                } else{
                    Z newBase = firstTest;
                    assert oddFactor.compareTo(new Z(2147483647)) <0 : "oddFactor is too large (I think). oddFactor: " +oddFactor;
                    int potenzK = 1; //ist der 'k' Exponent
                    for (Z twoFactor = twoObj; twoFactor.compareTo(maxPower) <= 0; twoFactor=twoFactor.multiply(twoObj)){
                        assert potenzK <= maxPower.intValue(): "Too many Iterations. assertZaehler: "+potenzK+ ", maxPower: " +maxPower ;
                        if (Basic.squareAndMultiply(newBase, twoFactor, checkPrime).equals(new Z(checkPrime.subtract(oneObj).toString()))){ //Wenn n-1 rauskommt, handelt es sich um eine Primzahl                            
                            continue nextBase;
                        }
                        ++potenzK;
                    }
                    return false; //der Test hat immer ungleich n-1 ergeben
                }
            }
            //Postcondition
            assert assertPostCondCounter == bases.size(): "There have not tested all the bases on 'true'";
            return true;
        }
}
