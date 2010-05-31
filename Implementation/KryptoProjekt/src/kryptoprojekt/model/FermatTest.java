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
    public <E extends KryptoType<E>> Tuple<Boolean, Double> test(List<E> bases, E checkPrime, boolean calcProp)
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        //soll checkPrimeArguments besser von fermatCheck in PrimeTest aufgerufen werden? In Lucas Test muss man sonst auch vorher checkBases aufrufen
        boolean checkPrimeArgAnswer = checkPrimeArguments(bases, checkPrime); //Überlegen ob hier auch calcProp überprüft werden soll. Aber eigentlich sollen nur Primzahlargumente wie Bases und Modul überprüft werden.
        if (checkPrimeArgAnswer) { //die IllegalArgumentException löst die checkPrimeArguments Methode von der Klasse PrimeTest aus, brauch ich hier also nicht abfangen
            boolean isPrime = fermatCheck(bases, checkPrime);
                if (isPrime){
                    if (calcProp) {
                        //Postcondition
                        assert checkPrimeArgAnswer == true && isPrime == true && calcProp == true: "checkPrimeArgAnswer, isPrime or calcProp have a false state";
                        return new Tuple<Boolean, Double>(isPrime, calculateProbability(bases, probabilityValue));
                    } else{
                        assert checkPrimeArgAnswer == true && isPrime == true && calcProp == false: "checkPrimeArgAnswer, isPrime or calcProp have a false state";
                        return new Tuple<Boolean, Double>(isPrime, -1.0);
                    }
                } else{
                    //Postcondition
                    assert checkPrimeArgAnswer == true && isPrime == false: "checkPrimeArgAnswer or isPrime have a false state";
                    return new Tuple<Boolean, Double>(false, 100.0); //es handelt sich um keine Primzahl
                }           
        }
        //Postcondition
        assert false: "This line should never be reached!";
        return null;
    }
}
