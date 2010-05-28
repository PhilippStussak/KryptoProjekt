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
    protected <E extends KryptoType<E>> Tuple<Boolean, String> checkPrimeArguments(List<E> bases, E checkPrime)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        try {
            if (checkPrime.compareTo((E) Factory.newInstance(checkPrime.getClass(), "1")) <= 0) {
                return new Tuple<Boolean, String>(false, "Es gibt nur Primzahlen >1");
            }
        } catch (ClassCastException e) {
            throw new ClassCastException("Comparison not possible. " + e.toString());
        }
        for (E checkBases : bases) {
            try {
                if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), "0")) <= 0) {
                    return new Tuple<Boolean, String> (false, "Basis zu klein. Sie muss sein:  0 < a < Modul");
                } else if (checkBases.compareTo((E) Factory.newInstance(checkBases.getClass(), checkPrime.toString())) >= 0) {
                    return new Tuple<Boolean, String>(false, "Basis zu groß. Sie muss sein:  0 < a < Modul");
                }
            } catch (ClassCastException e) {
                throw new ClassCastException("Comparison not possible. " + e.toString());
            }
        }
        return new Tuple<Boolean, String>(true, "");
    }

    /* verlangt die Basen (bases), einen double Wert (probabilityValue) mit dem die Wahrscheinlichkeit '1-probabilityValue' berechnet wird
     * und die Anzahl der maximalen Nachkommastellen(maxDecimalPlaces) für die Wahrscheinlichkeitsausgabe
     * diese Methode müssen Primzahltests überschreiben, bei denen die Wahrscheinlichkeitsberechnung anders berechnet wird.
     * gibt -1 zurück wenn probilityValue größer als 1 übergeben wurde und -2 wenn keine bases übertragen wurden
     * Wenn maxDecimalPlaces negativ oder 0 ist, wird immer 0% zurückgegeben. Wenn probabilityValue negativ oder 0 ist und maxDecimalPlaces >0 wird 0,0% zurück gegeben.
     * soll das Tuple statts einem String lieber ein BigInteger oder ein double zurückliefern?
     */
    protected <E extends KryptoType<E>> String calculateProbability(List<E> bases, double probabilityValue, int maxDecimalPlaces) {
        int numbOfBases; //beinhaltet die Anzahl der übergebenen Basen
        double probability; //behinhaltet die Wahrscheinlichkeit abhängig von der Anzahl der Base

        //Prüfung ob ein sinnvoller probabilityValue und maxDecimalPlaces übergeben wurd
        if (probabilityValue <= 0 || maxDecimalPlaces <0) {
            return (maxDecimalPlaces <= 0 ? "0" : "0.0") +"%";
        } else if (probabilityValue > 1){
            return "-1";
        }

        //probability wird abhängig von den übergebenen Basen berechnet
        HashSet<E> hs = new HashSet<E>();
        hs.addAll(bases); //Bitte überprüfen ob mehr als 10000 Elemente Platz haben, oder wo die Grenze ist. Dann besser abfangen. Bzw. schon bei der Eingabe in der GUI abfangen
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
        int capacity = Double.toString(probability).length();
        int beDecimPoint;
        StringBuffer figure = new StringBuffer("0");
        if (numbOfBases > 0) {
            if ((beDecimPoint = String.valueOf(probability).indexOf(".")+1) > -1){
                if (maxDecimalPlaces > 0 && capacity-beDecimPoint > 0) {
                    maxDecimalPlaces = maxDecimalPlaces < capacity-beDecimPoint ? maxDecimalPlaces : capacity-beDecimPoint;
                    figure.append(".");
                    for (int i = 1; i <= maxDecimalPlaces; i++) {
                        figure.append("0");
                    }
                } else{
                    figure = new StringBuffer("0");
                }
                DecimalFormat df = new DecimalFormat(figure.toString());
                return df.format((probability))+"%";
            } else{
                figure = new StringBuffer("0");
            }
            return figure.toString()+"%";
        } else {
            return "-2";
        }
    }

    //Macht den eigentlichen FermatCheck. Erwartet eine Liste mit den Basen welche prüfen sollen ob die übergebene Zahl eine Primzahl ist
    protected final <E extends KryptoType<E>> boolean fermatCheck(List<E> bases, E checkPrime)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        E obj = (E) Factory.newInstance(checkPrime.getClass(), "1"); //Die neue Instanz wir mit 1 initialisiert, das ist der Wert der vom Exponenten dann subtrahiert wird
        for (E item : bases) {
            if (!Basic.squareAndMultiply(item, checkPrime.subtract(obj), checkPrime).isONE()) {
                return false;
            }
        }
        return true;
    }

    //Was ist, wenn keine Wahrscheinlichkeit berechnet werden soll? Sollen wir zwei test-Methoden implementieren und die eine überschreibt er einfach mit leeren Klammern?
    /* Diese Methode sollen alle Unterklassen von PrimeTest implementieren. Alle Primzahltests werden über diese Methode aufgerufen.
     * bases enthält die Basen auf welche die checkPrime Zahl überprüft wird, ob es sich um eine Primzahl handelt oder nicht.
     * decimal Places gibt die Anzahl der Nachkommastellen an, wie die Wahrscheinlichkeit das es sich um eine Primzahl handelt zurückgegeben wird.
     * Vertrag einhalten: Rückgabewert ist ein Objekt vom Typ Tuble. Im ersten Argument steht ob es sich um eine Primzahl handelt und im zweiten wie hoch die Wahrscheinlichkeit ist.
     */
    public abstract <E extends KryptoType<E>> Tuple<Boolean, String> test(List<E> bases, E checkPrime, int decimalPlaces)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}
