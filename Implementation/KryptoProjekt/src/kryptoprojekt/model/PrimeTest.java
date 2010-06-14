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
     * Vertrag einhalten: Rückgabewert ist ein Objekt vom Typ Tuble. Im ersten Argument steht ob es sich um eine Primzahl handelt und
     * im zweiten wie hoch die Wahrscheinlichkeit ist das es eine ist, oder -1 wenn keine Wahrscheinlichkeit berechnet werden sollte.
     */
    ArrayList<Tuple<Boolean, Double>> test()
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}

