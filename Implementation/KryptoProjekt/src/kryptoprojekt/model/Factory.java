/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author Stefan
 */
public class Factory {

    public static <E extends KryptoType<E>> E newInstance(Class<E> c, String argument) {
        try {
            return c.getConstructor(new Class[]{String.class}).newInstance(new Object[]{argument});
        } catch (Exception e) {
            return null;
        }
    }

    private Factory() {
    }
}
