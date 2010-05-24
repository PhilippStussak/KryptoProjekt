package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.*;
import java.util.*;

public abstract class PrimeTest extends Validator {

    private Basic b1;
    
    //checkt ob die übergebene Basis '0 < a < Modul' ist
    protected <E extends KryptoType<E>> boolean checkBases(LinkedList<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        for (E checkBases : bases) {
            try {
                if (checkBases.compareTo(KryptoTypeInstancer.getInstanceE(checkBases, "0")) <= 0) {
                    throw new IllegalArgumentException("Basis zu klein. Sie muss sein:  0 < a < Modul");
                } else if (checkBases.compareTo(KryptoTypeInstancer.getInstanceE(checkBases, checkPrime.toString())) >= 0) {
                    throw new IllegalArgumentException("Basis zu gro�. Sie muss sein:  0 < a < Modul");
                }
            } catch (ClassCastException e) {
                throw new ClassCastException("Comparison not possible. " + e.toString());
            }
        }
        return true;
    }

    //Macht den eigentlichen FermatCheck. Erwartet eine Liste mit den Basen welche prüfen sollen ob die übergebene Zahl eine Primzahl ist
    protected final <E extends KryptoType<E>> boolean fermatCheck(LinkedList<E> bases, E checkPrime)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        E obj = KryptoTypeInstancer.getInstanceE(checkPrime, "1"); //Die neue Instanz wir mit 1 initialisiert, das ist der Wert der vom Exponenten dann subtrahiert wird
        b1 = new Basic();
        for (E item : bases) {
            if (!b1.squareAndMultiply(item, checkPrime.subtract(obj), checkPrime).isONE()) {
                return false;
            }
        }
        return true;
    }

    //Diese Methode sollen alle Unterklassen von PrimeTest implementieren. Alle Primzahltests werden über diese Methode aufgerufen
    public abstract <E extends KryptoType<E>> boolean test(LinkedList<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}
