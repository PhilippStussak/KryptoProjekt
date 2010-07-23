/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.*;

/**
 * This is the superclass for all different KryptoTypes (Z, polynomial etc.) Lucas primality test subclasses.
 *
 * @author Michael
 */
abstract class LucasTest <E extends KryptoType<E>> implements PrimeTest<E> {
    protected Collection<TreeSet<E>> basesSet; //contains the given bases to be used for the Lucas-Test to determine primes per Lucas-Test terms
    protected Collection<E> maxBases; //contains the max. value of bases that can used for primality test per Lucas-Test term
    protected Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection; //contains triples (bases, prime factors and powers of prime factors) of each Lucas term
    protected Collection<Tuple<E, E>> summandCollection; //summand and its power per Lucas term
    protected Collection<ArrayList<Tuple<E, E>>> primeFactorsInListTuples; //the list contains a sublist per Lucas term which contains tuples with the factors and powers of them
    protected boolean calcProb; //true to calculate the probability, otherwise false
    protected static double probabilityValue; //probability is at Lucas-Test always 100% (1) or cannot be calculated (-2)
    protected LinkedList<String> intermediateValues; //contains the intermediate values of the Lucas-Test


    /*
     * 1. argument: list of triples per Lucas term (list of bases to be used for the Lucas-Test, prime factors, powers of prime factors) for the number to determine if this is probably prime, (whitout summand !!!)
     * 2. argument: list of summand tuples per Lucas term (summand, power of summand), this summand will be added to the first argument
     * 3. argument: true to calculate and return the probability, otherwise false
     */
    protected LucasTest(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection, Collection<Tuple<E , E>> summandCollection, boolean calcProb){
        this.primeFactorsCollection = primeFactorsCollection;
        this.summandCollection = summandCollection;
        this.calcProb = calcProb;
        if (!checkSummandsOne(summandCollection)){
            lucastTestCompatibleFactoization(primeFactorsCollection); //not supported yet
        }
        if(!checkprimeFactorsCollection(primeFactorsCollection)){
            throw new IllegalArgumentException("Error, different number of factor bases and powers.");
        }
        setPrimeFactorsInListTuples(primeFactorsCollection);
        setBasesSetMaxBases();
    }

    /*
     * 1. argument: list of bases to be used for the Lucas-Test per Lucas Term
     * 2. argument: list of prime factors tuples per Lucas term ( prime factors, powers of prime factors) for the number to determine if this is probably prime, (whitout summand !!!)
     * 3. argument: list of summand tuple per Lucas term (summand, power of summand), this summand will be added to the prime factos tuples argument
     * 4. argument: true to calculate and return the probability, otherwise false
     */
    protected LucasTest(Collection<E> bases, Collection<Tuple<E, E>> primeFactors, Collection<Tuple<E, E>> summands, boolean calcProb){
        ArrayList<E> factors = new ArrayList<E>();
        ArrayList<E> powers = new ArrayList<E>();
        ArrayList<E> basesArrayList = new ArrayList<E>(bases);
        for(Tuple<E, E> primeFactor : primeFactors){
            factors.add(primeFactor.first());
            powers.add(primeFactor.second());
        }
        ArrayList<Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>> lucasPrimeFactors = new ArrayList<Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>>();
        Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>> triplePrimeFactors = new Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>(basesArrayList, factors, powers);
        lucasPrimeFactors.add(triplePrimeFactors);
        this.primeFactorsCollection = lucasPrimeFactors;
        this.summandCollection = summands;
        this.calcProb = calcProb;
        if (!checkSummandsOne(summandCollection)){
            lucastTestCompatibleFactoization(primeFactorsCollection); //not supported yet
        }
        if(!checkprimeFactorsCollection(primeFactorsCollection)){
            throw new IllegalArgumentException("Different number of factor bases and powers.");
        }
        setPrimeFactorsInListTuples(primeFactorsCollection);
        setBasesSetMaxBases();
    }

    /*
     * checks wheter the summand isn't equal to 1 or is 0
     * if false, the prime factorization must be done seperately (--> method lucastTestCompatibleFactoization)
     */
    private boolean checkSummandsOne(Collection<Tuple<E , E>> summandCollection){
        for (Tuple<E, E> summand: summandCollection){
            if (!(summand.first().isONE() || summand.first().isZERO())){
                return false;
            }
        }
        return true;
    }

    //checks whether all prime factors per Lucas term have one power
    private boolean checkprimeFactorsCollection(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection){
        for (Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>> triples : primeFactorsCollection){
            if(triples.second().size() == triples.third().size()){
                return true;
            }
        }
        return false;
    }

    //a passed number will be broken down into its prime factors added with the summand +1 (so that it's a Lucas-Test compatible term)
    private boolean lucastTestCompatibleFactoization(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection){
        throw new UnsupportedOperationException("Not supported yet. Please enter only one summand with the value of +1");
    }

    // constructs from the passed triple primeFactorsCollection (bases, prime factors, powers of the prime factos) a list with a sublist of tuples per Lucas term which contains the factors and powers of them
    private void setPrimeFactorsInListTuples(Collection<Triple<ArrayList<E> , ArrayList<E>, ArrayList<E>>> primeFactorsCollection){
        //that list contains a tuple per index which contains all prime factors and its powers per Lucas term
        ArrayList<Tuple<ArrayList<E>, ArrayList<E>>> TuplePrimeFactorsArrayList = new ArrayList<Tuple<ArrayList<E>, ArrayList<E>>>();
        for (Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>> triples : primeFactorsCollection){
            TuplePrimeFactorsArrayList.add(new Tuple(triples.second(), triples.third()));
        }
        //contains per primeFactorsArrayList index a sublist of tuples per Lucas term which contains the factors and powers of them
        ArrayList<ArrayList<Tuple<E, E>>> primeFactorsInListTuplesA = new ArrayList<ArrayList<Tuple<E, E>>>();
        ArrayList<Tuple<E, E>> primeFactors = new ArrayList<Tuple<E, E>>();
        for (Tuple<ArrayList<E>, ArrayList<E>> primeFactorsArrayList : TuplePrimeFactorsArrayList){
            for (int i = 0; i < primeFactorsArrayList.first().size(); i++){
                primeFactors.add(new Tuple<E, E>(primeFactorsArrayList.first().get(i), primeFactorsArrayList.second().get(i)));
            }
            primeFactorsInListTuplesA.add(primeFactors);
        }
        this.primeFactorsInListTuples = primeFactorsInListTuplesA;
    }


    /* 
     * stores the passed bases in the list 'basesSet' and using the method calculateMaxBasesSeparate to determine the max base store in 'maxBases', later to check if the passed Lucas term arguments are correct
     * If no base was passed, the max base will determine that can use for the Lucas-Test and the bases 2 to max base will store in the list 'basesSet'
     */
    private void setBasesSetMaxBases(){
        //Postcondition
        assert primeFactorsCollection.size() == summandCollection.size(): "Number of prime factors collection and summands collection aren't equals: summands = " +primeFactorsCollection.size()+ ", primetest collection = " +summandCollection.size();

        //auxiliary variable to construct the list 'basesSet'
        ArrayList<TreeSet<E>> basesList = new ArrayList<TreeSet<E>>();
        //maxBase that can be used for the Lucas-Test per Lucas term
        ArrayList<E> maxBase = new ArrayList<E>();
        //stores the summandCollection in an ArrayList
        ArrayList<Tuple<E, E>> tupleSummandList = new ArrayList<Tuple<E, E>>(summandCollection);
        ArrayList<Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>> triples = new ArrayList<Triple<ArrayList<E>, ArrayList<E>, ArrayList<E>>>(primeFactorsCollection);
        int counter = 0;

        for (ArrayList<Tuple<E, E>> primeFactors : primeFactorsInListTuples) {
            maxBase.add(calculateMaxBasesSeparate(primeFactors, tupleSummandList.get(counter)));
            //if no bases was passed
            if (triples.get(counter).first().isEmpty()){
                TreeSet<E> baseToAdd = new TreeSet<E>();
                if(!maxBase.get(counter).isONE()){
                E start = maxBase.get(0).newInstance("2");
                E end = start.newInstance(maxBase.get(counter).toString());
                E incrementOne = start.newInstance("1");
                //auxiliary variable for assert check
                E maxiBase = maxBase.get(0).newInstance("2");
                while(start.compareTo(end) <= 0){
                    assert maxiBase.compareTo(maxBase.get(counter)) <= 0:"Too many iterations. Variable maxiBase is greater than bases. maxiBase = " +maxiBase;
                    baseToAdd.add(start);
                    start = start.add(incrementOne);
                    maxiBase = maxiBase.add(incrementOne);
                    assert start.compareTo(end.add(incrementOne))<=0 && maxiBase.compareTo(end.add(incrementOne)) <= 0: "start- or maxiBase variable is too big. start = "+start.toString()+ ", maxiBase = " +maxiBase.toString();
                }
                basesList.add(baseToAdd);
                }else{                    
                    baseToAdd.add(maxBase.get(0).newInstance("1"));
                    basesList.add(baseToAdd);
                }
            }else{
                basesList.add(new TreeSet<E>(triples.get(counter).first()));
            }
            counter++;
            assert counter>0 && counter == tupleSummandList.size(): "Too many iterations. Variable primeFactorsCollection is greater than summands (tupleSummandList). They have to be the same size. primeFactorsCollection = " +primeFactorsCollection.size()+ ", tupleSummandList = " +tupleSummandList.size()+ ", counter = " +counter;
        }
        //contains the passed bases per Lucas term, if no base was passed it contains the bases from 2 to maxBases
        this.basesSet = basesList;
        ///maxBase that can be used for the Lucas-Test per Lucas term
        this.maxBases = maxBase;
    }


    // used by the method (setBasesSetMaxBases)
    /* determine the max base per Lucas term
     * 1. argument: needs a Tuple (a list of prime factors, powers of the prime factors), this represents one prime factor Lucas term
     * 2. argument: summand, that will be added to the Lucas term (summand_base, power of the summand)
     * in this implementation the summand have to be = 1
     */
    private E calculateMaxBasesSeparate(Collection<Tuple<E, E>> primeFactors, Tuple<E , E> summands){
        //Precondition
        assert summands.first().isONE() || summands.first().isZERO() || summands.second().isZERO():"Value of summand isn't 1 or 0: summand = "+summands.first()+ " power = "+summands.second();

        //contains the prime factors which are included at the first argument of prime factor tuple
        ArrayList<E> factorList = new ArrayList<E>();
        //contains the powers which are included at the second of primeFactors tuple
        ArrayList<E> powerList = new ArrayList<E>();
        //contains the number of factos which contains the tuple collection
        int numberOfFactors;
        //contains the number of powers which contains the tuple collection
        int numberOfPowers;

        numberOfFactors = factorList.size();
        numberOfPowers = powerList.size();

        //calculate the product of the passed prime factors
        assert numberOfFactors == numberOfPowers: "Number of factors and number of powers aren't equals: factors = " +numberOfFactors+ ", powers = " +numberOfPowers;
        ArrayList<Tuple<E, E>> maxBase = new ArrayList<Tuple<E, E>>(primeFactors);
        E product = maxBase.get(0).first().newInstance("1"); //contains the product of prime factors
        //returns the max base (calculate the probably prime, subratct 1)
        return calculatePrime(maxBase, summands).subtract(product.newInstance("1"));
     }


    /*
     * Calculated the product of passed Lucas-Test prime factors and added every summand on every product once.
     * Subtract 1 from the product = max base you can use for these Lucas term
     *
     */
    private ArrayList<E> calculateMaxBases(Collection<Tuple<Collection<E> , Collection<E>>> primeFactors, Collection<Tuple<E , E>> summandCollection){
        throw new UnsupportedOperationException("Not supported yet. Please use the method calculateMaxBasesSeparate ");
    }

    //return the prime factors
    protected Collection<E> getPrimeFactors(Collection<Tuple<E, E>> primeFactors){
        Collection<E> factors = new ArrayList<E>();

        for(Tuple<E, E> factor : primeFactors){
            factors.add(factor.first());
        }
        return factors;
    }

    //calculated the modul (the number which is probably prime) by using the prime factors
    protected E calculatePrime(Collection<Tuple<E, E>> primeFactors, Tuple<E, E> summands){
        //contains the number of tuples (prime factors)
        int numberOfprimeFactors = primeFactors.size();
        ArrayList<Tuple<E, E>> primeFactorList;
        primeFactorList = new ArrayList<Tuple<E, E>>(primeFactors);
        E modul = primeFactorList.get(0).first().newInstance("1");
        for(int i = 0; i<numberOfprimeFactors; i++) {
            modul = modul.multiply(Basic.squareAndMultiply(primeFactorList.get(i).first(), primeFactorList.get(i).second()).first());
        }
        return modul.add(Basic.squareAndMultiply(summands.first(), summands.second()).first());
    }

    /*
     * NOT IMPLEMENTED CORRECTLY. DON'T USE THESE METHOD!
     * returns:
     *  1: the number is 100% not a prime
     * -2: it could be a prime number
     */
    protected double calculateProbability(E maxBases, Set<E> bases, Collection<Tuple<E, E>> primeFactorsInTuples){
        //contains the factos without the summand
        ArrayList<E> factorsA = new ArrayList<E>(getPrimeFactors(primeFactorsInTuples));
        //contains the number of generating elements
        E numberOfGeneratingElements = maxGeneratingElements(factorsA);
        if (maxBases.subtract(numberOfGeneratingElements).compareTo((E) maxBases.newInstance(Integer.toString(bases.size()))) >= 1){
            //it could be a prime number
            return probabilityValue = -2;
        }
        return probabilityValue = 1;
    }

    /*
     * NOT IMPLEMENTED CORRECTLY. DON'T USE THESE METHOD!
     * needs the prime factors of the Lucas term and return the generating elemts
     */
    private E maxGeneratingElements(Collection<E> primeFactors){
        ArrayList<E> factors = new ArrayList<E>(primeFactors);
        E one = factors.get(0).newInstance("1");
        E generatingElements = one.newInstance("1");

        for(E factor : factors){
            generatingElements = generatingElements.multiply(factor.subtract(one));
        }
        return generatingElements;
    }
}