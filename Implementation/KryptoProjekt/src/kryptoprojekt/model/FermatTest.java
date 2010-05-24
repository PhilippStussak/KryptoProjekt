package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class FermatTest extends PrimeTest {

    private PrimeTest primeT;
    
    //führt den FermatTest über die abstrakte Klasse PrimeTest aus
    public <E extends KryptoType<E>> boolean test(LinkedList<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        primeT = new FermatTest();
        primeT.checkBases(bases, checkPrime); //prüft ob die Basis größer 0 und kleiner ist als das Modul
        return new FermatTest().fermatCheck(bases, checkPrime);
    }
}
