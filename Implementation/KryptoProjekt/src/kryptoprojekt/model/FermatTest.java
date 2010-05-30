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
    public <E extends KryptoType<E>> Tuple<Boolean, String> test(List<E> bases, E checkPrime, int maxDecimalPlaces)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        //soll checkPrimeArguments besser von fermatCheck in PrimeTest aufgerufen werden? In Lucas Test muss man sonst auch vorher checkBases aufrufen
        boolean checkPrimeArgAnswer = checkPrimeArguments(bases, checkPrime); //Überlegen ob hier auch maxDecimalPlaces überprüft werden sollen. Aber eigentlich sollen nur Primzahlargumente wie Bases und Modul überprüft werden.
        if (checkPrimeArgAnswer) { //die IllegalArgumentException löst die checkPrimeArguments Methode von der Klasse PrimeTest aus, brauch ich hier also nicht abfangen
            boolean isPrime = fermatCheck(bases, checkPrime);
                if (isPrime){
                    //Postcondition
                    assert checkPrimeArgAnswer == true && isPrime == true: "checkPrimeArgAnswer or isPrime have a false state";
                    return new Tuple<Boolean, String>(isPrime, calculateProbability(bases, probabilityValue, maxDecimalPlaces));
                } else{
                    //Postcondition
                    assert checkPrimeArgAnswer == true && isPrime == false: "checkPrimeArgAnswer or isPrime have a false state";
                    return new Tuple<Boolean, String>(false, "100%"); //es handelt sich um keine Primzahl
                }           
        }
        //Postcondition
        assert false: "This line should never be reached!";
        return new Tuple<Boolean, String>(false, "-1");
    }
}
