package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public final class FermatTest extends PrimeTest {

    private PrimeTest primeT;
    //probabilityValue muss von Prof. Laschinger noch berechnet werden.
    private static final double probabilityValue = 0.25; //Gibt die Wahrscheinlichkeit an, ob es sich um eine Primzahl handelt pro getestete Basis

    /* Führt den FermatTest über die abstrakte Klasse PrimeTest aus.
     * Die Methode test ist abstrakt in PrimeTest definiert und dort befinden sich auch die Erläuterungen zu den Übergabe- und Rückgabewerten.
     */
    public <E extends KryptoType<E>> Tuple<Boolean, String> test(List<E> bases, E checkPrime, int decimalPlaces)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        primeT = new FermatTest();
        //soll checkPrimeArguments besser von fermatCheck in PrimeTest aufgerufen werden? In Lucas Test muss man sonst auch vorher checkBases aufrufen
        int numbOfElements = bases.size();
        Tuple<Boolean, String> checkPrimeArgAnswer = null;
        if (numbOfElements > 0) {
            checkPrimeArgAnswer = primeT.checkPrimeArguments(bases, checkPrime); //prüft ob die Basis größer 0 und kleiner ist als das Modul
            if (checkPrimeArgAnswer.first() == false){
                throw new IllegalArgumentException(checkPrimeArgAnswer.second());
            } else{
                Boolean isPrime = primeT.fermatCheck(bases, checkPrime);
                if (isPrime){
                    return new Tuple<Boolean, String>(isPrime, primeT.calculateProbability(bases, probabilityValue, decimalPlaces));
                }
            }
        }
        return new Tuple<Boolean, String>(false, "100%"); //Wenn keine Basen übergeben wurde, wird false zurückgegeben das es sich 100% um keine Primzahl handelt.
    }
}
