/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Runs the Lucas primality test for natural numbers (PrimeType Z).
 *
 * @author Michael
 */
public class LucasZ extends LucasTest<Z>{
    
    /**
     * Constructs a new LucasZ object for natural numbers by using the given argumets.
     *
     * @param primeFactorsCollection list of triples per Lucas term (list of bases to be used for the Lucas-Test, prime factors, powers of prime factors) for the number to determine if this is probably prime, (whitout summand !!!)
     * @param summandCollection list of summand tuples per Lucas term (summand, power of summand), this summand will be added to the first argument
     * @param calcProb true to calculate and return the probability, otherwise false
     */
    public LucasZ(Collection<Triple<ArrayList<Z> , ArrayList<Z>, ArrayList<Z>>> primeFactorsCollection, Collection<Tuple<Z , Z>> summandCollection, boolean calcProb){
        super(primeFactorsCollection, summandCollection, calcProb);
    }

    /**
     * Constructs a new LucasZ object for natural numbers by using the given argumets.
     *
     * @param list of bases to be used for the Lucas-Test per Lucas Term
     * @param primeFactors list of prime factors tuples per Lucas term (prime factors, powers of prime factors) for the number to determine if this is probably prime, (whitout summand !!!)
     * @param summands list of summand tuple per Lucas term (summand, power of summand), this summand will be added to the prime factos tuples argument
     * @param calcProb true to calculate and return the probability, otherwise false
     */
    public LucasZ(Collection<Z> bases, Collection<Tuple<Z, Z>> primeFactors, Collection<Tuple<Z, Z>> summands, boolean calcProb){
        super(bases, primeFactors, summands, calcProb);
    }

    /**
     * Starts the Lucas-Test for natural numbers.
     *
     * @return List of results by using the Lucas-Test (whether 'modul' is probably prime, probability, intermediate values).
     * @throws IllegalArgumentException  IllegalArgumentException if the paramters are incorrect (bases have to be: 1 < base < moduls, moduls have to be: 1 < modul > bases. modul = {@code prime factors + summand})
     */
    public ArrayList<Triple<Boolean, Double, LinkedList<String>>> test()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
        ArrayList<Z> prime;
        double probability;

        //the passed parameters are correct/incorrect
        boolean checkPrimeArgAnswer = checkPrimeArguments().first();
        //message about the parameters answer correct/incorrect (e.g. Base 'a' too large ...)
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
                //all numbers that have passed the Fermat-Test
                if (result.first()){
                    //boolean isPrime = lucasCheck(checkBases.get(i), getPrimeFactors(listTuplesPrimeFactors.get(i)), prime.get(0)); //please keep
                    boolean isPrime = lucasCheck(checkBases.get(i), new ArrayList<Z>(primeFactorizationTreeSet(listTuplesPrimeFactors.get(i))), prime.get(0)); //only to test the phi function
                    //all numbers that have passed the Lucas-Test
                    if(isPrime){
                        if (calcProb){
                            //Postcondition
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == true: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, 1.0, intermediateValues));
                            continue;
                        } else{
                            assert checkPrimeArgAnswer == true && isPrime == true && calcProb == false: "checkPrimeArgAnswer or isPrime have a false state: checkPrimeArgAnswer = " +checkPrimeArgAnswer+ ", isPrime = " +isPrime;
                            //no probability should be calculated
                            primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(isPrime, -1.0, intermediateValues));
                            continue;
                        }
                    }
                 //all numbers that have failed the Fermat-Test
                }else{
                    primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(false, 1.0, intermediateValues));
                    continue;
                }
                /*
                 * all numbers that have failed the Lucas-Test
                 * probability = 1 --> it is not a prime number; probability = -2 --> it could be a prime number
                 */
                //probability = calculateProbability(maxBasesA.get(i), checkBases.get(i), listTuplesPrimeFactors.get(i));
                probability = calculateProbability(maxBasesA.get(i), checkBases.get(i), calculatePrime(listTuplesPrimeFactors.get(i), summands.get(i)));
                primeResult.add(new Triple<Boolean, Double, LinkedList<String>>(false, probability, intermediateValues));
            }
            //Postcondition
            assert !primeResult.isEmpty():"primeResult is empty: primeResult = " +primeResult.toString();
            return primeResult;
        } else{
            throw new IllegalArgumentException(argsCorrectMessage);
        }
    }


        /* checks whether the parameter values are correct: probably prime greater than 1 and base '1 < base < modul'
         * returns Tuple:
         * 1. argument = prime arguments (number greater than 1, 1 < base < modul) are correct true/false
         * 2. argument = message to describe what's false or all correct
         */
        private Tuple<Boolean, String> checkPrimeArguments()
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            //Precondition
            assert !basesSet.isEmpty() && !maxBases.isEmpty() && !primeFactorsCollection.isEmpty() && !summandCollection.isEmpty(): "At least one variable is empty. Number basesSet = " +basesSet.size()+ ", Number maxBases = " +maxBases.size()+ ", Number primeFactorsCollection = "+primeFactorsCollection.size()+ ", Number summandCollection ="+summandCollection.size();

            boolean argsCorrect = true;
            String argsAnswer = "Arguments are correct.";
            Z one = new Z("1");

            //checks whether the probably primes are greater than 1
            if(argsCorrect){
                for (Z primeCheck : maxBases){
                    //Beware! The probably prime is greater +1 than the base, therefore test <0. The prime 2 let be passed.
                    if (primeCheck.compareTo(one)< 0){
                         argsCorrect = false;
                         argsAnswer = "There are only prime numbers >1";
                         break;
                    }
                }
            } //end of testing probability prime

            //checks whether 'bases' > 1 && bases < probably prime (max. possible base +1)
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
            }//end of testing bases

            //check, wheter the passed factor lines are consistent (there have to the same number of base lines like summands).
            if(argsCorrect){
                if (maxBases.size() != summandCollection.size()){
                    argsCorrect = false;
                    argsAnswer = "Error: Different number of factor bases and summands: bases = " +maxBases.size()+ " summands = " +summandCollection.size();
                }
            }//end of testing lines of factors lines and summands
            return new Tuple<Boolean, String>(argsCorrect, argsAnswer);
        }


        //determine wheter the passed number is the prime number 2.
        private boolean isPrimeTwo(int c){
            return (calculatePrime(new ArrayList<ArrayList<Tuple<Z, Z>>>(primeFactorsInListTuples).get(c), new ArrayList<Tuple<Z, Z>>(summandCollection).get(c)).equals(new Z(2)));
        }

        /*
         * This is the Lucas-Test
         * 1.argument: list of bases to be used for the Lucas-Test for this Lucas term
         * 2.argument: primefactor_base
         * 3.argument: the probably prime
         */
        private boolean lucasCheck(Set<Z> bases, Collection<Z> primeFactors, Z checkPrime)
                throws IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, ClassCastException {
            Z modul = checkPrime;
            Z phiOfModul = modul.subtract(new Z(1)); //this is n-1
            Z result;

            if(!checkPrime.equals(new Z("2"))){
                ArrayList<Z> primeFactorsA = new ArrayList<Z>(primeFactors);
                boolean isPrime = false;
                int basesCounter = 0;
                int basesSize = bases.size();
                for(Z base : bases){
                    for(Z factor : primeFactorsA){
                        if(factor.isONE()){
                            continue;
                        }
                        isPrime = true;
                        result = Basic.squareAndMultiply(base, (phiOfModul.divide(factor)),modul).first();
                        intermediateValues.add(base+ "^(" +phiOfModul+ "/" +factor+ ") mod " +modul+ " = " +result);
                        if(result.isONE()){
                            isPrime = false;
                            if(++basesCounter < basesSize){
                                intermediateValues.add("");
                            }
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
                //intermediateValues.add(checkPrime+ " = 1"); //This is already inserted by Fermat-Test.
                return true;
            }
        }
}