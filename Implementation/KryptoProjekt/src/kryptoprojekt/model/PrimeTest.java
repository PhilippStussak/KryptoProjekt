package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.*;
import java.util.*;

public abstract class PrimeTest {

    //soll diese Methode vielleicht besser in die Validator Klasse?
    //checkt ob die übergebeneb Wert: Primzahl größer 0 und die Basis '0 < a < Modul' ist
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

    //Diese Methode sollen alle Unterklassen von PrimeTest implementieren. Alle Primzahltests werden über diese Methode aufgerufen
    public abstract <E extends KryptoType<E>> boolean test(List<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}
