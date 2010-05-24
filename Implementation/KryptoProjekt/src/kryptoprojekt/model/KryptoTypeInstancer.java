package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.*;

class KryptoTypeInstancer {

    //gibt ein Objekt von E zurück (einer Unterklasse von KryptoType)
    //erwartet ein Objekt von der Klasse E die einen FermatTest machen will und einen value der von dem übergebenen Wert abgezogen werden soll.
    @SuppressWarnings("unchecked")
    public static <E extends KryptoType<E>> E getInstanceE(E modul, String value)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        KryptoType<E> kryptoTypeValue = null;
        Constructor<? extends KryptoType> cons = null;
        Object[] reduce_value = null;

        Class<? extends KryptoType> c = modul.getClass();
        Class[] params = new Class[]{String.class};
        reduce_value = new Object[]{value};
        try {
            cons = c.getConstructor(params);
            kryptoTypeValue = cons.newInstance(reduce_value);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodException("public " + modul.getClass().getName() + " (String s) {...}; constructor was not found. " + e.toString());
        } catch (InstantiationException e) {
            throw new InstantiationException("Klasse nicht instanziierbar." + e.toString());
        } catch (IllegalAccessException e) {
            throw new IllegalAccessException("Kein Zugriff auf die Klasse." + e.toString());
        } catch (InvocationTargetException e) {
            throw new IllegalAccessException("Fehler bei der Instanziierung in der Klasse PrimeTest. " + e.toString());
        }
        return (E) kryptoTypeValue;
    }
}
