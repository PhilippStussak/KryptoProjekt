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

    public static <E extends KryptoType<E>> Tuple<E, LinkedList<E[]>> gcd(E e1, E e2) {
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

    public static <E extends KryptoType<E>> E squareAndMultiply(E base, E exponent) {
        char[] bin = exponent.toBinaryString().toCharArray();
        E result = (E)Factory.newInstance(base.getClass(), "1");
        for (char c : bin) {
            result = result.multiply(result);
            if (c == '1') {
                result = result.multiply(base);
            }
        }
        return result;
    }

    public static <E extends KryptoType<E>> E squareAndMultiply(E base, E exponent, E modul) {
        char[] bin = exponent.toBinaryString().toCharArray();
        E result = (E)Factory.newInstance(base.getClass(), "1");
        for (char c : bin) {
            result = result.multiply(result).mod(modul);
            if (c == '1') {
                result = result.multiply(base).mod(modul);
            }
        }
        return result;
    }

    public static Z phi(Z number) {
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

    public static Z sqrt(Z number) {
        Z result = number.divide(new Z(2)).add(new Z(1));
        Z help = result.add(new Z(1));
        while (result.multiply(result).compareTo(number) > 0 || help.multiply(help).compareTo(number) < 0) {
            result = result.add(number.divide(result)).divide(new Z(2));
            help = result.add(new Z(1));
        }
        return result;
    }

    public static <E extends KryptoType<E>> Tuple<E, LinkedList<E[]>> extendedGCD(E e1, E e2) {
        LinkedList<E[]> gcd = Basic.gcd(e1, e2).second();
        gcd.removeLast();
        E[] result = gcd.getLast();
        gcd.removeLast();
        E buffer = result[0];
        result[0] = result[3];
        result[3] = result[2];
        result[2] = (E)Factory.newInstance(e1.getClass(), "-1").multiply(result[1]);
        result[1] = buffer;
        result[3] = gcd.getLast()[0];
        //result[]
        //result[1] = result[2].multiply(gcd.getLast()[1]).add((E)Factory.newInstance(e1.getClass(), "1"));
    }

    private Basic() {
    }
}
