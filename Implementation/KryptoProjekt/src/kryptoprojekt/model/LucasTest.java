/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.util.*;

//Ist die Oberklasse für alle verschiedenen KryptoTypen die einen Lucas Test machen können.
public abstract class LucasTest <E extends KryptoType<E>> implements PrimeTest<E> {
    protected Collection<TreeSet<E>> basesSet; //beinhaltet die übergebenen Basen, auf der die Lucas Test Primfaktoren auf Primzahl überprüft werden sollen pro Lucas Test Primfaktoren
    protected Collection<E> maxBases; //enthält den Wert der maximalen Basen für den Primzahltest pro übergebene Lucas-Test Faktorenzeile
    protected Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection; //beinhaltet jeweils ein Triple (Bases, Faktoren und der Potenzen) von jeder Faktorenzeile
    protected Collection<Tuple<E, E>> summandCollection; //pro Primzahlenzeile sind hier der Summand und seine Potenz gespeichert
    protected Collection<ArrayList<Tuple<E, E>>> primeFactorsInListTuples; //Liste pro Primfaktorenzeile mit einer Unterliste, welche die Tuples mit den Faktoren und Potenzen enthält
    protected boolean calcProp; //ob die Wahrscheinlichkeit berechnet werden soll
    //protected static final double probabilityValue = 1; //Wahrscheinlichkeit ist bei Lucas Test immer 100% oder kann nicht berechnet werden (-2)
    protected static double probabilityValue;

    //ACHTUNG, OB NUR BESTIMMTE ODER ALLE BASEN BERECHNET WERDEN SOLLEN, GIBT DIE GUI AN. SIE ÜBERGIBT ENTWEDER EINE LISTE DER ZU BERECHNENTEN BASEN ODER
    //null(engl.)WENN ALLE BASEN BERECHNET WERDEN SOLLEN. DAZU WIRD DANN DIE METHODE calculateMaxBasesSeparate aufgerufen.
    /* Erwartet eine Collection mit einer ArrayListee von Primfaktoren pro LucasTest Berechnung die alle durchgetestet werden. Dazu noch Summanden
     * die nacheinander an alle Primfaktorprodukte angehängt werden.
     * - 1. Argument: erwartet ein Triple (Basen, Primfaktorbasis, Potenz) auf welche die Primzahleigenschaft von dem 'Produkt+Summand'
     * der primFactors getestet werden soll.
     * - 2. Argument: erwartet ein Tuple, welches als erstes den Summanden und als zweites die Potenz des Summanden enthält.
     * Pro Lucas Test Primfaktoren gibt es nur einen Summanden, aber es können durch die Methode calculateMaxBases (noch nicht implementiert)
     * alle Summanden aus der Collection einmal an die Primfaktorenliste hinten angehängt werden.
     * - 3. Argument wird angegeben ob die Wahrscheinlichkeit berechnet/ausgegeben werden soll.
     */
    protected LucasTest(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection, Collection<Tuple<E , E>> summandCollection, boolean calcProp){
        this.primeFactorsCollection = primeFactorsCollection; //beinhaltet jeweils ein Triple (Bases, Faktoren und der Potenzen) von jeder Faktorenzeile
        this.summandCollection = summandCollection; //pro Primzahlenzeile sind hier der Summand und seine Potenz gespeichert
        this.calcProp = calcProp;
        if (!checkSummandsOne(summandCollection)){
            lucastTestCompatibleFactoization(primeFactorsCollection); //not supported yet
        }
        if(!checkprimeFactorsCollection(primeFactorsCollection)){
            throw new IllegalArgumentException("Unterschiedliche Anzahl an Faktorbasen und Potenzen");
        }
        setPrimeFactorsInListTuples(primeFactorsCollection);
        setBasesSetMaxBases();
    }


    //prüft ob nur Summanden +1 oder 0 übergeben wurden
    private boolean checkSummandsOne(Collection<Tuple<E , E>> summandCollection){
        for (Tuple<E, E> summand: summandCollection){
            if (!(summand.first().isONE() || summand.first().isZERO() || summand.second().isZERO())){
                return false;
            }
        }
        return true;
    }

    private boolean checkprimeFactorsCollection(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection){
        for (Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>> triples : primeFactorsCollection){
            if(triples.second().size() == triples.third().size()){
                return true;
            }
        }
        return false;
    }


    //Soll eine übergebene Zahl in Primfaktoren zerlegen, welche hinten +1 als Summanden haben.
    private boolean lucastTestCompatibleFactoization(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection){
        throw new UnsupportedOperationException("Not supported yet. Please enter only one summand with the value of +1");
        //soll für primeFactorsCollection die LucastTest kompatiblen Faktoren berechnen und Summanden alle auf 1 setzen
    }


    /* Wird verwendet um aus der Triple Liste (first = bases, second = factorBase, third = power) ein Tuple zu erstellen,
    * welches als erstes Argument (first) die Bases beinhaltet und als zweites (second) die Potenz des Faktors.
    * Es werden so viele Tuple erzeugt, wie es Faktoren pro Faktorenzeile übergeben wurden. Diese werden in einer ArrayList gespeichert.
    * Die ArrayList ist wiederum in einer Collection.
    * Man braucht später nur die Collection durchgehen und erhält in einer ArrayList alle Primfaktoren und Potenzen einer Factorenzeile.
    */
    private void setPrimeFactorsInListTuples(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection){
        //Pro ArrayList-Eintrag gibt es ein Tuple welches als Liste die Faktoren und Potenzen einer Primfaktorenzeile speichert
        ArrayList<Tuple<ArrayList<E>, ArrayList<E>>> TuplePrimeFactorsArrayList = new ArrayList<Tuple<ArrayList<E>, ArrayList<E>>>(); //primeFactorsArrayList beinhaltet pro Index ein Tuple, welches alle Faktoren und Potenzen pro Primfaktorenzeile beinhaltet
        for (Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>> triples : primeFactorsCollection){
            TuplePrimeFactorsArrayList.add(new Tuple(triples.second(), triples.third()));
        }
        //Pro ArrayList-Eintrag gibt es eine Liste die Tuple bestehend aus Faktor und Potenz enthält. Der interne ArrayList-Eintrag bezieht sich nur auf EINE Primfaktorenzeile
        ArrayList<ArrayList<Tuple<E, E>>> primeFactorsInListTuplesA = new ArrayList<ArrayList<Tuple<E, E>>>(); //pro primeFactorsArrayList Index, beinhaltet es als Tuple alle Faktoren und Potenzen pro PrimfaktorenZeile
        ArrayList<Tuple<E, E>> primeFactors = new ArrayList<Tuple<E, E>>();
        for (Tuple<ArrayList<E>, ArrayList<E>> primeFactorsArrayList : TuplePrimeFactorsArrayList){
            for (int i = 0; i < primeFactorsArrayList.first().size(); i++){
                primeFactors.add(new Tuple<E, E>(primeFactorsArrayList.first().get(i), primeFactorsArrayList.second().get(i)));
            }
            primeFactorsInListTuplesA.add(primeFactors); //Speichert eine Liste, wo jeder Eintrag eine Liste ist - welche Tupel enthält mit Faktor und Potenz einer Primfaktorenzeile
        }
        this.primeFactorsInListTuples = primeFactorsInListTuplesA;
    }


    /* Setzt die übergebenen Basen in basesSet und lässt davon noch die maximale Basis berechnen um später zu checken ob die Argumente richtig sind.
     * Anmerkung: Zufällige Basen werden hier auch übergeben. Also die zufälligen Basen werden dann von der GUI übermittelt.
     * Wird keine Basis übergeben, wird die maximale Basis bestimmt welche genommen werden kann und die Basen von 2 bis maxBases aufgefüllt
     */
    //private void setBasesSetMaxBases(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection, Collection<Tuple<E , E>> summandCollection){
    private void setBasesSetMaxBases(){
        //Postcondition
        assert primeFactorsCollection.size() == summandCollection.size(): "Number of prime factors collection and summands collection aren't equals: summands = " +primeFactorsCollection.size()+ ", primetest collection = " +summandCollection.size();

        ArrayList<TreeSet<E>> basesList = new ArrayList<TreeSet<E>>(); //enthält die Basen auf welche die einzelnen Lucas Test Faktoren auf Primzahleigenschaft getestet werden sollen
        ArrayList<E> maxBase = new ArrayList<E>(); //enthält die maxBasis pro Lucas Test Faktorenzeile mit der auf Primzahl getestet werden darf
        ArrayList<Tuple<E, E>> tupleSummandList = new ArrayList<Tuple<E, E>>(summandCollection); //speichert den Inhalt der übergebenen summandCollection in eine ArrayList
        ArrayList<Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>> triples = new ArrayList<Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>>(primeFactorsCollection);

        int counter = 0; //Hilfsvariable um durch die tupleSummandList zu iterieren und um bestimmte Werte von maxBase in der for-Schleife zu erhalten

        /* berechnet die maximale Basis die auf diesen einzelnen Test (Primfaktorenzeile) angewandt werden darf und speichert es in maxBases
         * Außerdem speichert es die übergebenen Basen für diesen Test in der Variablen basesSet.
         */
        for (ArrayList<Tuple<E, E>> primeFactors : primeFactorsInListTuples) {
            maxBase.add(calculateMaxBasesSeparate(primeFactors, tupleSummandList.get(counter)));

            //Wenn keine Basen für eine LucasTest Faktorenzeile (also null) übergeben wurden, werden alle Basen von 2 bis maxBase für diese Faktorenzeile aufgefüllt

            if (triples.get(counter).first().isEmpty()){
                TreeSet<E> baseToAdd = new TreeSet<E>();
                if(!maxBase.get(counter).isONE()){

                E start = maxBase.get(0).newInstance("2");
               // E start = (E) Factory.newInstance(maxBase.get(0).getClass(), "2");
               // E end = (E) Factory.newInstance(start.getClass(), maxBase.get(counter).toString());
                E end = start.newInstance(maxBase.get(counter).toString());
                //E incrementOne = (E) Factory.newInstance(start.getClass(), "1");
                E incrementOne = start.newInstance("1");
                //E maxiBase = (E) Factory.newInstance(maxBase.get(0).getClass(), "2"); //Hilfsvariable für assert
                E maxiBase = maxBase.get(0).newInstance("2"); //Hilfsvariable für assert
                while(start.compareTo(end) <= 0){
                    assert maxiBase.compareTo(maxBase.get(counter)) <= 0:"Too many iterations. Variable maxiBase is greater than bases. maxiBase = " +maxiBase;
                    baseToAdd.add(start);
                    start = start.add(incrementOne);
                    maxiBase = maxiBase.add(incrementOne);
                    assert start.compareTo(end.add(incrementOne))<=0 && maxiBase.compareTo(end.add(incrementOne)) <= 0: "start- or maxiBase variable is too big. start = "+start.toString()+ ", maxiBase = " +maxiBase.toString();
                }
                basesList.add(baseToAdd);
                }else{                    
                    //baseToAdd.add((E)Factory.newInstance(maxBase.get(0).getClass(), "1"));
                    baseToAdd.add(maxBase.get(0).newInstance("1"));
                    basesList.add(baseToAdd);
                }

            }else{ //Es werden die Basen in basesSet geschrieben, welche für diesen Lucas Test Faktor im Konstruktor übergeben wurden
                basesList.add(new TreeSet<E>(triples.get(counter).first()));
            }
            counter++;
            assert counter>0 && counter == tupleSummandList.size(): "Too many iterations. Variable primeFactorsCollection is greater than summands (tupleSummandList). They have to be the same size. primeFactorsCollection = " +primeFactorsCollection.size()+ ", tupleSummandList = " +tupleSummandList.size()+ ", counter = " +counter;
        }


        this.basesSet = basesList; //Von den übergebenen Primfaktorzeilen, stehen hier jeweils die Basen drin, die für einen Primzahltest (individuell für jede Zeile) verwendet werden sollen
        this.maxBases = maxBase; //Ist die maximale Basis die (individuell für jede Zeile) für den Primzahltest verwendet werden darf
    }


    /* Berechnet die maximale Basis für eine Lucas-Test Folge.
     * - 1. Argument: erwartet ein Tuple, welches als erstes Argument(first) eine Liste von Faktoren verlangt und zweites Argument(second) die Potenzen
     * dieser Faktoren.
     * - 2. Argument: erwartet den Summanden, welcher zu den Lucas Test Faktoren dazu addiert wird.
     * Dieser hat als erstes Argument(first) den Summanden und als zweites Argument(second) die Potenz.
     * In dieser Implementierung muss der Summand 1 sein.
     * Am Ende wird noch der Wert '+1' von dem Endwert abgezogen und ergibt die maximale Basis.
     */
    private E calculateMaxBasesSeparate(Collection<Tuple<E, E>> primeFactors, Tuple<E , E> summands){
        //Precondition
        assert summands.first().isONE() || summands.first().isZERO() || summands.second().isZERO():"Value of summand isn't 1 or 0: summand = "+summands.first()+ " power = "+summands.second();

        ArrayList<E> factorList = new ArrayList<E>(); //enthält die Faktoren welche im ersten Argument von dem Tuple enthalten sind
        ArrayList<E> powerList = new ArrayList<E>(); //enthält die Potenzen welche im zweiten Argument von dem Tuple enthalten sind
        int numberOfFactors; //beinhaltet wie viele Faktoren eine Tuple Collection jeweils beinhaltet
        int numberOfPowers; //beinhaltet wie viele Basen eine Tuple Collection jeweils beinhaltet

        numberOfFactors = factorList.size();
        numberOfPowers = powerList.size();

        //berechnet das Produkt der Primfaktoren des übergebenen Tuples
        assert numberOfFactors == numberOfPowers: "Number of factors and number of powers aren't equals: factors = " +numberOfFactors+ ", powers = " +numberOfPowers;
        ArrayList<Tuple<E, E>> maxBase = new ArrayList<Tuple<E, E>>(primeFactors);
        //E product = (E) Factory.newInstance(maxBase.get(0).first().getClass(), "1"); //enthält das Produkt von den Primfaktoren
        E product = maxBase.get(0).first().newInstance("1"); //enthält das Produkt von den Primfaktoren

        //lässt die Primzahl berechnen und zieht den Wert (+1)abund gibt dies als maximale Basis zurück. (vielleicht könnten auch andere Summanden später übergeben werden)
        return calculatePrime(maxBase, summands).subtract(product.newInstance("1"));
     }


    /* Berechnet das Produkt der übergenen LucasTest Faktoren und addiert dazu nacheinander alle übergebenen Summanden auf das jeweilige Lucas Test
     * Produkt dazu. Falls dies später funktionieren soll.
     * Von dem jeweiligen Ergebnis wird noch 1 subtrahiert und ergibt dann die maximale Basis die für diese LucasTest Faktoren angewandt werden dürfen.
     * z. B. übergeben wurde für einen LucasTest die Faktoren 2^2, 3^1 und 4^1 und als Summanden 1, 3, 5
     * jetzt wird gerechnet:
     * ((2^2) * (3^1) * (4^1) + 1)-1
     * ((2^2) * (3^1) * (4^1) + 3)-1
     * ((2^2) * (3^1) * (4^1) + 5)-1
     * Zudem könnte man auch mehrere Potenzen übergeben (von - bis), welche über alle Primfaktoren nacheinander zugewiesen werden.
     * Möglicherweise wird dies über die GUI realisiert.
     */
    private ArrayList<E> calculateMaxBases(Collection<Tuple<Collection<E> , Collection<E>>> primeFactors, Collection<Tuple<E , E>> summandCollection){
        throw new UnsupportedOperationException("Not supported yet. Please use the method calculateMaxBasesSeparate ");
    }


    //gibt die Primfaktoren zurück
    protected Collection<E> getPrimeFactors(Collection<Tuple<E, E>> primeFactors){
        Collection<E> factors = new ArrayList<E>();

        for(Tuple<E, E> factor : primeFactors){
            factors.add(factor.first());
        }
        return factors;
    }


    //berechnet aus den Primfaktoren das Modul
    protected E calculatePrime(Collection<Tuple<E, E>> primeFactors, Tuple<E, E> summands){
        int numberOfprimeFactors = primeFactors.size(); //beinhaltet aus wie vielen Tuplen (entspricht den Primfaktoren) die Collection besteht
        ArrayList<Tuple<E, E>> primeFactorList;
        primeFactorList = new ArrayList<Tuple<E, E>>(primeFactors);
        //E modul = (E) Factory.newInstance(primeFactorList.get(0).first().getClass(), "1");
        E modul = primeFactorList.get(0).first().newInstance("1");
        for(int i = 0; i<numberOfprimeFactors; i++) {
            modul = modul.multiply(Basic.squareAndMultiply(primeFactorList.get(i).first(), primeFactorList.get(i).second()).first());
        }
        return modul.add(Basic.squareAndMultiply(summands.first(), summands.second()).first());//((E) Factory.newInstance(primeFactorList.get(0).first().getClass(), "1"));
    }


     /* ***ACHTUNG!!!! möglicherweise wird diese Methode in die einzelnen Unterklassen verlagert, da ich nicht weiß ob diese jeweils anders berechnet werden.
      * Ist es dann trotzdem richtig diese Klasse abstrakt zu machen? Oder soll es dann ein Interface werden? Aber was soll da drin stehen?.
      * Gibt 1 oder -2 zurück. Je nachdem ob genug Basen bestimmt wurden.
      *  1: es handelt sich 100% um keine Primzahl
      * -2: Es könnte sich trozdem um eine Primzahl handeln.
      */
    protected double calculateProbability(E maxBases, Set<E> bases, Collection<Tuple<E, E>> primeFactorsInTuples){
        ArrayList<E> factorsA = new ArrayList<E>(getPrimeFactors(primeFactorsInTuples)); //beinhaltet die Faktoren (ohne dem Summanden) mit dem die zu checkende Primzahl gebildet wurde
        E numberOfGeneratingElements = maxGeneratingElements(factorsA); //enthält die Anzahl der erzeugenden Elemente
        //if (maxBases.subtract(numberOfGeneratingElements).compareTo((E) Factory.newInstance(maxBases.getClass(), Integer.toString(bases.size()))) >= 1){
        if (maxBases.subtract(numberOfGeneratingElements).compareTo((E) maxBases.newInstance(Integer.toString(bases.size()))) >= 1){
            return probabilityValue = -2; //it could be a prime number
        }
        return probabilityValue = 1;
    }


    //Erhält als Argument die Primfaktoren und gibt die Anzahl der Erzeugenden Elemente zurück
    private E maxGeneratingElements(Collection<E> primeFactors){
        ArrayList<E> factors = new ArrayList<E>(primeFactors);
        //E one = (E) Factory.newInstance(factors.get(0).getClass(), "1");
        E one = factors.get(0).newInstance("1");
        //E generatingElements = (E) Factory.newInstance(one.getClass(), "1");
        E generatingElements = one.newInstance("1");

        for(E factor : factors){
            generatingElements = generatingElements.multiply(factor.subtract(one));
        }
        return generatingElements;
    }

    /*Hier nicht verwendbar, da nicht sichergestellt werden kann, welches Objekt man erhält
    protected E getHighestBase(Collection<E> base){
        return new TreeSet<E>(base).last();
    }

    protected E getLowestBase(Collection<E> base){
        return base.first();
    }

    protected E getHighestModul(Collection<E> modul){
        return moduls.last();
    }

    protected E getLowestModul(Collection<E> modul){
        return moduls.first();
    }*/
}

