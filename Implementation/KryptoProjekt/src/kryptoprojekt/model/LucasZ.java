/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

/**
 *
 * @author Michael
 */
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class LucasZ extends LucasTest<Z>{



    /*
     * Erzeugt ein Lucas-Test Objekt für den PrimeType Z.
     * 1. Argument: Triple (Basen, Faktoren, Potenzen von den Faktoren)
     * 2. Argument: Tuple (Summand, Potenz vom Summanden)
     * 3. Argument: Wahrscheinlichkeit berechnet/ausgegeben, ja oder nein
     */
    //LucasZ(Collection<Triple<Collection<Z> , Collection<Z>, Collection<Z>>> primeFactorsCollection, Collection<Tuple<Z , Z>> summandCollection, boolean calcProb){
    public LucasZ(Collection<Triple<ArrayList<Z> , ArrayList<Z>, ArrayList<Z>>> primeFactorsCollection, Collection<Tuple<Z , Z>> summandCollection, boolean calcProb){
        super(primeFactorsCollection, summandCollection, calcProb);
    }

    public LucasZ(Collection<Z> bases, Collection<Tuple<Z, Z>> primeFactors, Collection<Tuple<Z, Z>> summands, boolean calcProb){
        super(bases, primeFactors, summands, calcProb);
    }

    public ArrayList<Triple<Boolean, Double, LinkedList<String>>> test2(){
        return null;
    }

    //gibt zurück ob es sich um eine Primzahl handelt und mit welcher Wahrscheinlichkeit
    public ArrayList<Triple<Boolean, Double, LinkedList<String>>> test()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        ArrayList<Z> prime;
        double probability;

        boolean checkPrimeArgAnswer = checkPrimeArguments().first();
        String argsCorrectMessage = checkPrimeArguments().second();

        ArrayList<Triple<Boolean, Double, LinkedList<String>>> primeResult = new ArrayList<Triple<Boolean, Double, LinkedList<String>>>();
        if (checkPrimeArgAnswer) {
            assert new TreeSet<Z>(maxBases).first().compareTo(new Z(1)) >= 0: "Found prime number less than 2: value prime number = " +new TreeSet<Z>(maxBases).first().add(new Z(1))+ ", value base = "+new TreeSet<Z>(maxBases).first();
            ArrayList<TreeSet<Z>> checkBases = new ArrayList<TreeSet<Z>>(basesSet);
            ArrayList<ArrayList<Tuple<Z, Z>>> listTuplesPrimeFactors = new ArrayList<ArrayList<Tuple<Z, Z>>>(primeFactorsInListTuples);
            ArrayList<Tuple<Z, Z>> summands = new ArrayList<Tuple<Z, Z>>(summandCollection);
            ArrayList<Z> maxBasesA = new ArrayList<Z>(maxBases);
            Triple<Boolean, Double, LinkedList<String>> result;

            int factorLines = primeFactorsCollection.size();
            for (int i = 0; i<factorLines; i++){
                prime = new ArrayList();
                prime.add(calculatePrime(listTuplesPrimeFactors.get(i), summands.get(i)));
                intermediateValues = new LinkedList<String>();
                intermediateValues.add(prime.get(i).toString());
                result = new FermatZ(checkBases.get(i), prime, false).test().get(0);
                intermediateValues.add(result.third().getFirst());
                if (result.first()){
                    boolean isPrime = lucasCheck(checkBases.get(i), getPrimeFactors(listTuplesPrimeFactors.get(i)), prime.get(0));
                    if(isPrime){
                        if (calcProb){
                            //Postcondition
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == true: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, 1.0, intermediateValues));
                            continue;
                        } else{
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, -1.0, intermediateValues)); //es sollte keine Wahrscheinlichkeit berechnet werden
                            continue;
                        }
                    }
                }else{                  
                    primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(false, 1.0, intermediateValues));
                    continue;
                }
                probability = calculateProbability(maxBasesA.get(i), checkBases.get(i), listTuplesPrimeFactors.get(i));
                primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(false, probability, intermediateValues)); //probability = 1 --> it is not a prime number; probability = -2 --> it could be a prime number
            }
            assert !primeResult.isEmpty():"primeResult is empty: primeResult = " +primeResult.toString();
            return primeResult;
        } else{
            throw new IllegalArgumentException(argsCorrectMessage);
        }
    }


        //checkt ob die übergebenen Werte: Primzahl größer 1 und die Basis '1 < a < Modul' sind
        private Tuple<Boolean, String> checkPrimeArguments()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            //Precondition
            assert !basesSet.isEmpty() && !maxBases.isEmpty() && !primeFactorsCollection.isEmpty() && !summandCollection.isEmpty(): "At least one variable is empty. Number basesSet = " +basesSet.size()+ ", Number maxBases = " +maxBases.size()+ ", Number primeFactorsCollection = "+primeFactorsCollection.size()+ ", Number summandCollection ="+summandCollection.size();

            boolean argsCorrect = true;
            String argsAnswer = "Arguments are correct.";
            Z one = new Z("1");
            Z two = new Z("2");

            // Prüfung ob die Primzahlen korrekt sind. (p > 1)
            if(argsCorrect){
                for (Z primeCheck : maxBases){
                    if (primeCheck.compareTo(one)< 0){ //Vorsicht, die Primzahl ist +1 größer als die Basis, deshalb Test <0. Die Primzahl 2 wird hier durchgelassen
                         argsCorrect = false;
                         argsAnswer = "There are only prime numbers >1";
                         break;
                    }
                }
            } //Ende Prüfung Primzahlentest


            //checkt ob alle angegebenen Basen größer 1 sind und kleiner als das Modul (mögliche Primzahl)
            if(argsCorrect){
                int i = 0;
                for(TreeSet<Z> base : basesSet){
                    if (base.last().compareTo(new ArrayList<Z>(maxBases).get(i)) >0 && !new ArrayList<Z>(maxBases).get(i).equals(one)){
                        argsCorrect = false;
                        argsAnswer = "Base 'a' too large. Lucas-Test requires a base:  1 < a < prime: base = " +base.last()+ " prime = " +new ArrayList<Z>(maxBases).get(i);
                        break;
                    }
                    if (base.first().compareTo(one) <= 0){
                        if(base.first().isONE()){
                            if(base.size() > 1){
                                Iterator<Z> itModuls = base.iterator();
                                itModuls.next();
                                if(base.last().compareTo(itModuls.next()) >=0){
                                    argsCorrect = false;
                                    argsAnswer = "Base 'a' too large. Lucas-Test requires a base:  1 < a < prime";
                                }
                            }
                            if(isPrimeTwo(i)){
                                i++;
                                continue;
                            }
                        }
                        argsCorrect = false;
                        argsAnswer = "Base 'a' too small. Lucas-Test requires a base:  1 < a < prime: base = " +base.first();
                        break;
                    }
                    i++;
                }
            }//Ende Prüfung Basentest

            //Prüfung ob die Anzahl der übergebenen Faktorzeilen konsistenz sind (es muss gleich viele Zeilen mit Basen wie Summanden geben).
            if(argsCorrect){
                if (maxBases.size() != summandCollection.size()){
                    //throw new IllegalArgumentException("Fehler: Anzahl von Faktorenzeilen und Summandenzeilen sind unterschiedlich. Basen = " +maxBases.size()+ " Summanden = " +summandCollection.size());
                    argsCorrect = false;
                    argsAnswer = "Error: Different number of factor bases and summands: bases = " +maxBases.size()+ " summands = " +summandCollection.size();
                }
            }//Ende Prüfung Faktorzeilen und und Summandenzeilen
            return new Tuple<Boolean, String>(argsCorrect, argsAnswer);
        }


        //Berechnet ob die übergebene Zahl die 2 ist. Muss extra gemacht werden, da diese die Basis 1 hat und diese ansonsten nicht zugelassen sind.
        private boolean isPrimeTwo(int c){
            return (calculatePrime(new ArrayList<ArrayList<Tuple<Z, Z>>>(primeFactorsInListTuples).get(c), new ArrayList<Tuple<Z, Z>>(summandCollection).get(c)).equals(new Z(2)));
        }


        //Macht den Lucas-Test.
        /* Erwartet:
         * 1.Argument(Liste mit Basen auf die diese Primfaktorenzeile getestet werden soll
         * 2.Argument(die Primfaktoren also Faktor und Exponent als Tuple in einer Liste pro Primfaktorzeile)
         * 3.Argument (die Primzahl auf die getestet werden soll)
         */
        private boolean lucasCheck(Set<Z> bases, Collection<Z> primeFactors, Z checkPrime)
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            Z oneObj = new Z(1); //wird verwendet um zu schauen ob 1 rauskommt
            Z modul = checkPrime;
            Z phiOfModul = modul.subtract(new Z(1)); //entspricht n-1
            Z result;

            //macht den Primzahltest
            if(!checkPrime.equals(new Z("2"))){
                ArrayList<Z> primeFactorsA = new ArrayList<Z>(primeFactors);
                boolean isPrime = false;
                for(Z base : bases){
                    for(Z factor : primeFactorsA){
                        if(factor.equals(oneObj)){
                            continue;
                        }
                        isPrime = true;
                        result = Basic.squareAndMultiply(base, (phiOfModul.divide(factor)),modul).first();
                        intermediateValues.add(base+ "^(" +phiOfModul+ "/" +factor+ ") mod " +modul+ " = " +result);
                        if(result.equals(oneObj)){
                            isPrime = false;
                            intermediateValues.add("");
                            break;
                        }
                    }
                    if(isPrime){
                        return true;
                    }
                }
                return false;
            }else{
                assert Integer.parseInt(checkPrime.toString()) == 2: "Error, checkPrime != 2. checkPrime: " +checkPrime.toString();
                intermediateValues.add(checkPrime+ " = 1");
                return true;
            }
        }
}
