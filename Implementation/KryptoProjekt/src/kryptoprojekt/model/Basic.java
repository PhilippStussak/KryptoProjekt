/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

import java.util.LinkedList;

/**
 *
 * @author Stefan
 */
public class Basic {

    /**
     * Calculates the gratest common divisor of two objects of KryptoType.
     *
     * @param <E> defines the type of the parameters and the return-value.
     * @param e1 first value for the calculation of the gratest common divisor
     * @param e2 second value for the calculation of the gratest common divisor
     * @return Returns the gratest common divisor of two values
     * in the first part of the tuple,
     * and the extensions in the second part of the tuple
     * ({@code LinkedList<Integer[]>}).
     * The extensions are formatted like
     * {@code [0] = [1] * [2] + [3]}.
     */
    public static <E extends KryptoType<E>> Tuple<E, LinkedList<E[]>> gcd(E e1, E e2) {
        if(e1.isZERO() && e2.isZERO())
            throw new IllegalArgumentException();
        if(e1.getClass().equals(Z.class)) {
            Object o = e1;
            e1 = (E)(KryptoType)((Z)o).abs();
            o = e2;
            e2 = (E)(KryptoType)((Z)o).abs();
        }
        LinkedList<E[]> list = new LinkedList<E[]>();
        do {
            if (e1.compareTo(e2) < 0) {
                E buffer = e1;
                e1 = e2;
                e2 = buffer;
            }
            list.add((E[])new KryptoType[] {e1, e1.divide(e2), e2, e1.mod(e2)});
            e1 = e1.mod(e2);
        } while (!e1.isZERO());
        return new Tuple<E, LinkedList<E[]>>(e2, list);
    }

    /**
     * Calculates this<sup>exponent</sup> in a very efficient way,
     * using the square and multiply-algorithm.
     *
     * @param <E> defines the type of the parameters and the return-value.
     * @param base value which is to be raise to the exponent.
     * @param exponent exponent to which the base is to be raised.
     * @return this<sup>exponent</sup>
     */
    public static <E extends KryptoType<E>> E squareAndMultiply(E base, E exponent) {
        char[] bin = exponent.toBinaryString().toCharArray();
        E result = base.newInstance("1");
        for (char c : bin) {
            result = result.multiply(result);
            if (c == '1') {
                result = result.multiply(base);
            }
        }
        return result;
    }

    /**
     * Calculates this<sup>exponent</sup> % modul in a very efficient way,
     * using the square and multiply-algorithm.
     *
     * @param <E> defines the type of the parameters and the return-value.
     * @param base value which is to be raise to the exponent.
     * @param exponent exponent to which the base is to be raised.
     * @param modul the modulus which is used in every arithmetic computation.
     * @return this<sup>exponent</sup> % modul
     */
    public static <E extends KryptoType<E>> E squareAndMultiply(E base, E exponent, E modul) {
        char[] bin = exponent.toBinaryString().toCharArray();
        E result = base.newInstance("1");
        for (char c : bin) {
            result = result.multiply(result).mod(modul);
            if (c == '1') {
                result = result.multiply(base).mod(modul);
            }
        }
        return result;
    }

    /**
     * Calculates Eulers totient function phi(number).
     * Gives the number of positive integers which are coprimes
     * to the given number.
     *
     * @param number number for which the totient function is to be calculated.
     * @return the number of positive coprimes to the given parameter.
     */
    public static Z phi(Z number) {
        if(number.compareTo(new Z(1)) < 0 || number.compareTo(new Z(Integer.MAX_VALUE).multiply(new Z(Integer.MAX_VALUE))) > 0)
            throw new IllegalArgumentException();
        if (number.equals(new Z(1)) || number.equals(new Z(2))) {
            return new Z(1);
        } else if (number.equals(new Z(3))) {
            return new Z(2);
        }
        Z result = number;
        for (int i : new Primes(Basic.sqrt(number).intValue())) {
            Z divider = new Z(i);
            if (number.mod(divider).isZERO()) {
                result = result.subtract(result.divide(divider));
                while (number.mod(divider).isZERO()) {
                    number = number.divide(divider);
                }
                if (number.isONE()) {
                    break;
                }
            }
        }
        if (!number.isONE()) {
            result = result.subtract(result.divide(number));
        }
        return result;
    }

    /**
     * Calculates the squareroot of the given number.
     * Returns a new Z which is the floor of {@code sqrt(number)}.
     *
     * @param number value for shich the squareroot is computed.
     * @return {@code integer(sqrt(number))}
     */
    public static Z sqrt(Z number) {
        if(number.compareTo(new Z(0)) < 0)
            throw new IllegalArgumentException();
        Z result = number.divide(new Z(2)).add(new Z(1));
        Z help = result.add(new Z(1));
        while (result.multiply(result).compareTo(number) > 0 || help.multiply(help).compareTo(number) < 0) {
            result = result.add(number.divide(result)).divide(new Z(2));
            help = result.add(new Z(1));
        }
        return result;
    }

    /**
     * Calculates the extended euclidean algorithm.
     *
     * @param <E> defines the type of the parameters and the return-value.
     * @param e1 first value for extended euclidean algorithm.
     * @param e2 second value for extended euclidean algorithm.
     * @return
     */
    public static <E extends KryptoType<E>> Tuple<E, LinkedList<E[]>> extendedGCD(E e1, E e2) {
        Tuple<E, LinkedList<E[]>> gcd = Basic.gcd(e1, e2);
        LinkedList<E[]> list = gcd.second();
        list.removeLast();
        E[] result = permuteForEctendedGCD(list.getLast());
        list.removeLast();
        int index = 0;
        while(!list.isEmpty()) {
            E[] field = permuteForEctendedGCD(list.getLast());
            list.removeLast();
            if(index % 2 == 0) {
                result[0] = result[0].add(result[2].multiply(field[2]));
                result[2] = result[2].multiply(field[0]);
                result[3] = field[1];
            }
            else {
                result[2] = result[2].add(result[0].multiply(field[2]));
                result[0] = result[0].multiply(field[0]);
                result[1] = field[1];
            }
            for(E e : result)
                System.out.print(e + " ");
            System.out.println();
            index++;
        }
        return new Tuple<E, LinkedList<E[]>>(result[0], null);
    }

    private static <E extends KryptoType<E>> E[] permuteForEctendedGCD(E[] equation) {
        equation[3] = equation[2];
        equation[2] = equation[1].multiply(equation[0].newInstance("-1"));
        equation[1] = equation[0];
        equation[0] = equation[0].newInstance("1");
        return equation;
    }

    private Basic() {
    }
}
