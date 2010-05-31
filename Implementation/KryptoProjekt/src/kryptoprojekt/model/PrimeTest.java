package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.*;
import java.util.*;
import java.text.DecimalFormat;

public abstract class PrimeTest {

    //soll diese Methode vielleicht besser in die Validator Klasse?
    //checkt ob die übergebenen Werte: Primzahl größer 0 und die Basis '0 < a < Modul' sind
    protected <E extends KryptoType<E>> boolean checkPrimeArguments(List<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        if (checkPrime.compareTo((E) Factory.newInstance(checkPrime.getClass(), "1")) <= 0) { //prüft ob Primzahl größer 0 ist
            throw new IllegalArgumentException("Es gibt nur Primzahlen >1");
        }
        for (E checkBases : bases) { //prüft ob bases > 0 && bases < checkPrime ist
            if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), "0")) <= 0) {
                throw new IllegalArgumentException("Basis zu klein. Sie muss sein:  0 < a < Modul");
             } else if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), checkPrime.toString())) >= 0) {
                 throw new IllegalArgumentException("Basis zu groß. Sie muss sein:  0 < a < Modul");
               }
        }
        //Postcondition
        assert checkPrime.compareTo((E) Factory.newInstance(checkPrime.getClass(), "1")) == 1: "checkprime isn't > 1";
        return true;
    }

    /* verlangt als Parameter die Basen (bases), einen double Wert (probabilityValue) mit dem die Wahrscheinlichkeit '1-probabilityValue' berechnet wird
     * und die Anzahl der maximalen Nachkommastellen(maxDecimalPlaces) für die Wahrscheinlichkeitsausgabe
     * diese Methode müssen Primzahltests überschreiben, bei denen die Wahrscheinlichkeitsberechnung anders berechnet wird.
     * Wenn probabilityValue 0 ist und maxDecimalPlaces =0 wird 0% zurückgegeben - bei maxDecimalPlaces >0 wird 0.0% zurückgegeben
     * soll das Tuple statts einem String lieber ein BigInteger oder ein double zurückliefern?
     */
    protected <E extends KryptoType<E>> double calculateProbability(List<E> bases, double probabilityValue)
        throws IllegalArgumentException {
        int numbOfBases; //beinhaltet die Anzahl der übergebenen Basen
        double probability; //behinhaltet die Wahrscheinlichkeit das es sich um eine Primzahl handelt

        //Prüfung auf korrekte Paramter
        if (bases.isEmpty()){
            throw new IllegalArgumentException("probabilityValue must be >=0 and <=1 ");
        }        
        for (E checkBases : bases) { //prüft ob bases > 0 //Achtung diese Abfrage kommt bereits ähnlich in checkPrimeArguments vot
            if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), "0")) <= 0) {
                throw new IllegalArgumentException("Basis zu klein. Sie muss sein:  0 < a < Modul");
            }             
        }
        if (probabilityValue < 0 || probabilityValue > 1) {
            throw new IllegalArgumentException("probabilityValue have to be >=0 and <=1 ");
        }
        if (probabilityValue == 0) {
            return 0;
        }
        assert bases != null: "bases is null";
        assert !bases.isEmpty(): "No bases were passed.";
        assert probabilityValue <=1: "probabilityValue isn't <= 1";
        assert probabilityValue >=0: "probabilityValue isn't >= 0";

        //probability wird abhängig von den übergebenen Basen berechnet
        HashSet<E> hs = new HashSet<E>();
        hs.addAll(bases); //Bitte überprüfen ob mehr als 10000 Elemente Platz haben, oder wo die Grenze ist. Dann besser abfangen. Bzw. schon bei der Eingabe in der GUI abfangen
        assert Set.class.isAssignableFrom(hs.getClass()): "check that bases contains no dublicate elements";
        assert hs.size() >0: "bases has no elements";
        numbOfBases = hs.size();
        probability = probabilityValue;
        for (int i = 1; i <= numbOfBases; i++ ) {
            probability = probability * probabilityValue;
        }
        return probability = (1-probability);
    }

    //Macht den eigentlichen FermatCheck. Erwartet eine Liste mit den Basen welche prüfen sollen ob die übergebene Zahl eine Primzahl ist
    protected final <E extends KryptoType<E>> boolean fermatCheck(List<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        boolean basesCorrect;
        boolean isPrime = false;

        basesCorrect = checkPrimeArguments(bases, checkPrime);
        if (basesCorrect) { //die IllegalArgumentException löst die checkPrimeArguments Methode aus, brauch ich hier also nicht abfangen
            E obj = (E) Factory.newInstance(checkPrime.getClass(), "1"); //Die neue Instanz wir mit 1 initialisiert, das ist der Wert der vom Exponenten dann subtrahiert wird
            for (E item : bases) {
                isPrime = Basic.squareAndMultiply(item, checkPrime.subtract(obj), checkPrime).isONE();
                if (!isPrime) {
                    //Postcondition
                    assert basesCorrect == true && isPrime == false: "basesCorrect or isPrime have a false state";
                    return false;
                }
             }
            //Postcondition
            assert basesCorrect == true && isPrime == true: "basesCorrect or isPrime hava a false state";
            return true;
        }
        assert false: "This line should never be reached!";
        Boolean notReached = null;
        return notReached;
    }

    // Was ist, wenn keine Wahrscheinlichkeit berechnet werden soll? Sollen wir zwei test-Methoden implementieren und die eine überschreibt er einfach mit leeren Klammern?
    /* Diese Methode sollen alle Unterklassen von PrimeTest implementieren. Alle Primzahltests werden über diese Methode aufgerufen.
     * bases enthält die Basen auf welche die checkPrime Zahl überprüft wird, ob es sich um eine Primzahl handelt oder nicht.
     * calcProp gibt an, ob die Wahrscheinlichkeit das es sich um eine Primzahl handelt berechnet werden soll
     * Falls keine Wahrscheinlichkeit berechnet werden soll, oder diese nicht möglich ist, erhält man als Rückgabewert -1.
     * Vertrag einhalten: Rückgabewert ist ein Objekt vom Typ Tuble. Im ersten Argument steht ob es sich um eine Primzahl handelt und im zweiten wie hoch die Wahrscheinlichkeit ist oder -1.
     */
    public abstract <E extends KryptoType<E>> Tuple<Boolean, Double> test(List<E> bases, E checkPrime, boolean calcProp)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}
