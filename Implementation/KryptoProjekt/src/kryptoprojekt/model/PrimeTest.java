/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Interface for all the classes to perform a probabilistic test to determine if a number is probably prime or composite.
 *
 * @author Michael
 * @param <E> defines the type (Z, polynomial etc.) of the parameters and the return-values
 */
public interface PrimeTest<E extends KryptoType<E>> {
    /**
     * All subclasses have to implement this method. This methods start the primality test.
     *
     * @return list of results by using the prime test (if 'modul' is probably prime, probability that 'modul' is prime, intermediate values).
     * +1 = the number is 100% a prime (1. argument = true), or the number is 100% not a prime (2. argument = false)
     * -1 = no probability should be calculated/returns
     * -2 = it could be a prime number, or the probability can't be calculated for this prime test
     * @throws IllegalArgumentException if the paramters are incorrect for the used prime test
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws ClassCastException
     */
    ArrayList<Triple<Boolean, Double, LinkedList<String>>> test()
            throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException;
}