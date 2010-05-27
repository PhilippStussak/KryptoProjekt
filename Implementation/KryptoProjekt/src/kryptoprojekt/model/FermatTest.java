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
    public <E extends KryptoType<E>> boolean test(List<E> bases, E checkPrime)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        primeT = new FermatTest();
        //soll besser checkPrimeArguments von fermatCheck in PrimeTest aufgerufen werden? In Lucas Test muss man sonst auch vorher checkBases aufrufen
        Tuple<Boolean, String> checkBasesAnswer = primeT.checkPrimeArguments(bases, checkPrime); //prüft ob die Basis größer 0 und kleiner ist als das Modul
        if (checkBasesAnswer.first() == true){
            return primeT.fermatCheck(bases, checkPrime);
        } else{
            throw new IllegalArgumentException(checkBasesAnswer.second());
        }
    }
}
