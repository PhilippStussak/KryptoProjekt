/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.*;
import java.util.ArrayList;

public interface PrimeTest<E extends KryptoType<E>> {

    /* Diese Methode sollen alle Unterklassen von PrimeTest implementieren. Alle Primzahltests werden über diese Methode aufgerufen.
     * Vertrag einhalten: Rückgabewert ist ein Objekt vom Typ Tuble.
     * 1. Argument: gibt an ob es sich um eine Primzahl handelt
     * 2. Argument: gibt die Wahrscheinlichkeit an, ob es eine Primzahl ist
     * +1 wird zurückgegeben, wenn es sich zu 100% um eine Primzahl handelt(1. Argument = true), oder zu 100% um keine Primzahl(1. Argument = false)
     * -1 wird zurückgegeben wenn keine Wahrscheinlichkeit berechnet/ausgegeben werden soll
     * -2 wird zurückgegeben wenn es eine Primzahl sein könnte, oder wenn für diesen Primzahltest keine Wahrscheinlichkeit berechnet werden kann.
     * (bei Fermat: (true, -2), bei LucasTest: (false, -2)
     */
    ArrayList<Tuple<Boolean, Double>> test()
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}