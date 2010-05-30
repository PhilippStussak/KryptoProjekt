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
            throws NullPointerException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        if (bases == null){
            throw new NullPointerException("bases is null");
        }
        if (checkPrime == null){
            throw new NullPointerException("checkPrime is null");
        }
        try { //prüft ob Primzahl größer 0 ist
            if (checkPrime.compareTo((E) Factory.newInstance(checkPrime.getClass(), "1")) <= 0) {
                throw new IllegalArgumentException("Es gibt nur Primzahlen >1");
                //return new Tuple<Boolean, String>(false, "Es gibt nur Primzahlen >1");
            }
        } catch (ClassCastException e) { //falls Factory eine zu E inkombatible Instanz liefert
            throw new ClassCastException("Comparison is not possible. " + e.toString());
        }
        for (E checkBases : bases) { //prüft ob bases > 0 && bases < checkPrime ist
            try {
                if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), "0")) <= 0) {
                    throw new IllegalArgumentException("Basis zu klein. Sie muss sein:  0 < a < Modul");
                    //return new Tuple<Boolean, String> (false, "Basis zu klein. Sie muss sein:  0 < a < Modul");
                } else if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), checkPrime.toString())) >= 0) {
                    throw new IllegalArgumentException("Basis zu groß. Sie muss sein:  0 < a < Modul");
                    //return new Tuple<Boolean, String>(false, "Basis zu groß. Sie muss sein:  0 < a < Modul");
                }
            } catch (ClassCastException e) {
                throw new ClassCastException("Comparison not possible. " + e.toString());
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
    protected <E extends KryptoType<E>> String calculateProbability(List<E> bases, double probabilityValue, int maxDecimalPlaces)
        throws NullPointerException, IllegalArgumentException {
        int numbOfBases; //beinhaltet die Anzahl der übergebenen Basen
        double probability; //behinhaltet die Wahrscheinlichkeit das es sich um eine Primzahl handelt

        //Prüfung auf korrekte Paramter
        if (bases == null) {
            throw new NullPointerException("bases is null");
        }
        if (bases.isEmpty()){
            throw new IllegalArgumentException("probabilityValue must be >=0 and <=1 ");
        }        
        for (E checkBases : bases) { //prüft ob bases > 0 //Achtung diese Abfrage kommt bereits ähnlich in checkPrimeArguments vot
            try {
                if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), "0")) <= 0) {
                    throw new IllegalArgumentException("Basis zu klein. Sie muss sein:  0 < a < Modul");
                }
            } catch (ClassCastException e) {
                throw new ClassCastException("Comparison not possible. " + e.toString());
            }
        }
        if (probabilityValue < 0 || probabilityValue > 1) {
            throw new IllegalArgumentException("probabilityValue have to be >=0 and <=1 ");
        }
        //Wenn der probabilityValue 0 ist, kann gleich 0% bzw. 0.0% zurückgegeben werden
        if (probabilityValue == 0) {
            return (maxDecimalPlaces <= 0 ? "0" : "0.0") +"%";
        }
        if (maxDecimalPlaces < 0) {
            throw new IllegalArgumentException("maxDecimalPlaces have to be >=0");
        }
        assert bases != null: "bases is null";
        assert !bases.isEmpty(): "No bases were passed.";
        assert probabilityValue <=1: "probabilityValue isn't <= 1";
        assert probabilityValue >=0: "probabilityValue isn't >= 0";
        assert maxDecimalPlaces >=0: "maxDecimalPlaces isn't >= 0";

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
        probability = (1-probability)*100;

        /* Erstellt die Anzahl der Nachkommastellen, wie die Dezimalzahl probability zurückgegeben wird.
         * figure ist ein Muster das angibt mit wie vielen Dezimalstellen probability zurück gegeben wird, abhängig von den übergebenen maxDecimalPlaces.
         * Wenn die probability Zahl jedoch weniger Dezimalstellen hat als von maxDecimalPlaces übergeben, wird maxDecimalPlaces nicht beachtet
         */
        int capacity = Double.toString(probability).length(); //speichert Anzahl der Zeichen von probability
        int behDecimPoint;
        StringBuffer figure = new StringBuffer("0");
        assert numbOfBases > 0: "numbOfBases is not >0.";
        if ((behDecimPoint = String.valueOf(probability).indexOf(".")+1) > -1){
            if (maxDecimalPlaces > 0 && capacity-behDecimPoint > 0) {
                maxDecimalPlaces = maxDecimalPlaces < capacity-behDecimPoint ? maxDecimalPlaces : capacity-behDecimPoint;
                figure.append(".");
                for (int i = 1; i <= maxDecimalPlaces; i++) {
                    figure.append("0"); //es werden Nullen nach dem Gleitkommapunkt hinzugefügt
                }
            } else if (maxDecimalPlaces == 0){
                figure = new StringBuffer("0");
            }
            DecimalFormat df = new DecimalFormat(figure.toString());
            return df.format((probability))+"%";
        } else{
            figure = new StringBuffer("0");
        }
        return figure.toString()+"%";
    }

    //Macht den eigentlichen FermatCheck. Erwartet eine Liste mit den Basen welche prüfen sollen ob die übergebene Zahl eine Primzahl ist
    protected final <E extends KryptoType<E>> boolean fermatCheck(List<E> bases, E checkPrime)
            throws NullPointerException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
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
     * maxDecimalPlaces gibt die max. Anzahl der Nachkommastellen für den Rückgabewert der Wahrscheinlichkeit an, dass es sich um eine Primzahl handelt.
     * Falls keine Wahrscheinlichkeit berechnet werden soll, muss für maxDecimalPlaces ein negativer Wert übergeben werden. Als Rückgabewert erhält man -1.
     * Vertrag einhalten: Rückgabewert ist ein Objekt vom Typ Tuble. Im ersten Argument steht ob es sich um eine Primzahl handelt und im zweiten wie hoch die Wahrscheinlichkeit ist oder -1.
     */
    public abstract <E extends KryptoType<E>> Tuple<Boolean, String> test(List<E> bases, E checkPrime, int maxDecimalPlaces)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}
