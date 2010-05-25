/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

import java.util.BitSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Stefan
 */
public class Primes implements Iterable<Integer> {

    private BitSet bs;
    private int max;

    public Primes(int max) {
        if (max < 2) {
            throw new IllegalArgumentException();
        }
        this.max = max;
        if (max > 2) {
            bs = new BitSet();
            bs.flip(0, (max - 1) / 2);
            long actual = 3;
            while (actual >= 3) {
                for (long i = 3 * (actual - 1) / 2; bs.length() - i >= actual && i > 0; i += actual) {
                    bs.set((int) i, false);
                }
                actual = 2 * bs.nextSetBit(((int) actual - 1) / 2) + 3;
            }
        }
    }

    public Iterator<Integer> iterator() {
        return new PIterator();
    }

    private class PIterator implements Iterator<Integer> {

        int actual = 2;

        public boolean hasNext() {
            return actual >= 2;
        }

        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int result = actual;
            actual = (actual == 2) ? (bs == null) ? -1 : 3 : 2 * bs.nextSetBit((actual - 1) / 2) + 3;
            return result;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
