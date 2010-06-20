/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

import java.util.Hashtable;

/**
 *
 * @author LiTTle, Mario
 */
public class HammingCode extends Coder {

    private Matrix<GaloisElement> controlMatrix;
    private Matrix<GaloisElement> generatorMatrix;
    private Matrix<GaloisElement> sourceCodeWord;
    private Matrix<GaloisElement> encodedWord;
    private Matrix<GaloisElement> syndrom;
    private Matrix<GaloisElement> decodedWord;
    private final int galoisBase = 2;

    /**
     * Encodes the sourceCodeWord which was set within constructor.
     * @throws NullPointerException if sourceCodeWord or generatorMatrix is not calculated yet
     * @return encoded String
     */
    @Override
    public String encode() throws NullPointerException{
        if (sourceCodeWord == null || generatorMatrix == null)
            throw new NullPointerException("sourceCodeWord or generatorMatrix is not calculated yet.");
        this.encodedWord = this.sourceCodeWord.multiply(this.generatorMatrix);
        return convertOneRowMatrixToString(this.encodedWord);
    }

    /**
     * Calculates the syndrom.
     * @throws NullPointerException if encodedWord or controlMatrix is not calculated yet
     */
    @Override
    //Do we have to throw an exception in this case, too?
    public void calculateSyndrom() throws NullPointerException{
        if (encodedWord == null || controlMatrix == null)
            throw new NullPointerException("EncodedWord or ControlMatrix not calculated yet.");
        this.syndrom = this.encodedWord.multiply(this.controlMatrix);
    }

    /**
     * Detects error(s) in the encodedWord. Uses the syndrom-attribute which has to be calculated before.
     * @throws NullPointerException if syndrom is not calculated yet
     * @return Hashtable index 0 => bool-value, if errors were found
     *                   index 1 => String, original decodedWord
     *                   index 2 => int, error position
     *                   index 3 => String, decodedWord, but corrected if possible
     */
    @Override
    public Hashtable detectError() throws NullPointerException{
        Hashtable result = new Hashtable();
        GaloisElement comparison = new GaloisElement(0, this.galoisBase);

        if (this.syndrom == null) {
            throw new NullPointerException("Syndrom is not calculated yet.");
        }

        //is there any error in the syndrom
        for (int i = 0; i < this.syndrom.getMatrixColumnCapacity(); i++) {
            if (comparison.compareTo(this.syndrom.get(0, i)) != 0) {
                result.put(0, true);
            }
        }
        if (result.get(0) == null) {
            result.put(0, false);
        }

        //decoded codeWord
        result.put(1, decode());


        if ((Boolean) result.get(0) == true) {
            int errorPos = getErrorPosition();
            result.put(2, errorPos);
            if (errorPos > -1) {
                result.put(3, correctError(errorPos));
            } else {
                result.put(3, "Zuviele Fehler. Korrektur kann nicht vorgenommen werden.");
            }
        }

        return result;

    }

    /**
     * Corrects the error in encodedWord.
     * @param pos position of error in encodedWord
     * @throws NullPointerException if encodedWord is not calculated yet
     * @return corrected encodedWord
     */
    private String correctError(int pos) throws NullPointerException{
        if (encodedWord == null)
            throw new NullPointerException("EncodedWord is not calculated yet.");
        GaloisElement comparison = new GaloisElement(0, this.galoisBase);

        if (comparison.compareTo(this.encodedWord.get(0, pos)) == 0) {
            this.encodedWord.set(0, pos, new GaloisElement(1, this.galoisBase));
        } else {
            this.encodedWord.set(0, pos, comparison);
        }
        return convertOneRowMatrixToString(encodedWord);
    }

    /**
     * Calculates errorPosition in encodedWord on base of syndrom and controlMatrix
     * @throws NullPointerException if syndrom or controlMatrix are not calculated yet
     * @return errorPosition or -1 if too many errors found
     */
    private int getErrorPosition() throws NullPointerException{
        if (controlMatrix == null || syndrom == null)
            throw new NullPointerException("Syndrom or controlMatrix not calculated yet.");
        boolean check = false;
        boolean test = true;
        int position = 0;

        for (int y = 0; y < this.controlMatrix.getMatrixRowCapacity(); y++) {
            for (int i = 0; i < this.syndrom.getMatrixColumnCapacity(); i++) {
                if (this.syndrom.get(0, i).compareTo(this.controlMatrix.get(y, i)) != 0) {
                    check = true;
                }
            }
            if (check == false) {
                test = true;
                position = y;
            }
        }

        if (test == true) {
            return position;
        } else {
            return -1;
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String printStatistics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Decodes the encodedWord.
     * @throws NullPointerException if encodedWord is not calculated yet
     * @return decoded Word
     */
    @Override
    public String decode() {
        if (encodedWord == null)
            throw new NullPointerException("EncodedWord is not calculated yet.");
        KryptoType<GaloisElement>[][] t = new KryptoType[1][this.codeWord.length() - 1];
        for (int i = 0; i < this.codeWord.length(); i++) {
            t[0][i] = encodedWord.get(0, i);
        }
        this.decodedWord = new Matrix(t);
        return convertOneRowMatrixToString(this.decodedWord);
    }

    /**
     * Generates the generatorMatrix on base of given codeWordLength.
     * @param codewordLength Length of given codeWord
     */
    private void generateGeneratorMatrix(int codewordLength) {
        int columnCapacity = (int) Math.pow(2, codewordLength) - 1;
        KryptoType<GaloisElement>[][] t = new KryptoType[codewordLength][columnCapacity];

        //initialise array with GaloisElement-objects, value=0
        for (int i = 0; i < codewordLength; i++) {
            for (int j = 0; j < columnCapacity; j++) {
                t[i][j] = new GaloisElement(0, this.galoisBase);
            }
        }

        //generate identity matrix
        for (int i = 0; i < codewordLength; i++) {
            t[i][i] = new GaloisElement(1, this.galoisBase);
        }

        //set parity bits
        for (int i = 0; i < codewordLength; i++) {
            for (int j = codewordLength; j < (int) Math.pow(2, codewordLength) - 1; j++) {
                if (i != j) {
                    t[i][j] = new GaloisElement(1, this.galoisBase);
                }
            }
        }
        this.generatorMatrix = new Matrix(t);
    }

    /**
     * Generates the controlMatrix on base of given generatorMatrix.
     */
    private void generateControlMatrix() {
        int x = this.generatorMatrix.getMatrixColumnCapacity() - this.generatorMatrix.getMatrixRowCapacity();
        KryptoType<GaloisElement> k[][] = new KryptoType[x][this.generatorMatrix.getMatrixColumnCapacity()];
        int n = this.generatorMatrix.getMatrixRowCapacity();

        //initialise array with GaloisElement-objects, value=0
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < this.generatorMatrix.getMatrixColumnCapacity(); j++) {
                k[i][j] = new GaloisElement(0, this.galoisBase);
            }
        }

        //sets parity bits in control matrix
        for (int i = n + 1; i < x + n; i++) {
            for (int j = 0; j < n; j++) {
                k[i][j] = this.generatorMatrix.get(i, j);
            }
        }

        //generates identity matrix
        for (int i = 0; i < x; i++) {
            k[i][i] = new GaloisElement(1, this.galoisBase);
        }
    }

    /**
     * Constructs a new object of HammingCode on base of given generatorMatrix with  galoisBase = 2 and the given codeWord.
     * Length of codeWord have to be the same as columnCapacity of given generatorMatrix.
     * @param generatorMatrix matrix, which consists of identity-matrix and parity-matrix
     * @param codeWord word, which has to be encoded
     * @throws IllegalArgumentException if length of given codeWord != columnCapacity of given generatorMatrix
     */
    public HammingCode(Matrix<GaloisElement> generatorMatrix, String codeWord) throws IllegalArgumentException {
        this.codeWord = codeWord;
        this.sourceCodeWord = convertStringToOneRowMatrix(codeWord);
        if (Math.pow(2, codeWord.length()) - 1 == generatorMatrix.getMatrixColumnCapacity()) {
            this.generatorMatrix = generatorMatrix;
            generateControlMatrix();
        } else {
            throw new IllegalArgumentException("Codewortlänge stimmt nicht mit Länge der Generatormatrix überein.");
        }
    }

    /**
     * Constructs a new object of HammingCode on base of given generatorMatrix with galoisBase = 2 and the given codeWord.
     * @param codeWord word, which has to be encoded
     */
    public HammingCode(String codeWord) {
        this.codeWord = codeWord;
        this.sourceCodeWord = convertStringToOneRowMatrix(codeWord);
        generateGeneratorMatrix(codeWord.length());
        generateControlMatrix();
    }

    /**
     * Converts a String into a matrix with one row and column quantity of given sourceCodeWordLength.
     * @param sourceCodeWord word which has to be converted into matrix
     * @return given String converted in Matrix<GaloisElement>
     */
    private Matrix<GaloisElement> convertStringToOneRowMatrix(String sourceCodeWord) {
        KryptoType<GaloisElement> cWord[][] = new KryptoType[1][this.codeWord.length()];
        char[] t = sourceCodeWord.toCharArray();
        for (int i = 0; i < sourceCodeWord.length(); i++) {
            for (int j = 0; j < this.galoisBase; j++) //added 48 to j because of ascii-value of '0'
            {
                if (t[i] == j + 48) {
                    cWord[0][i] = new GaloisElement(j, this.galoisBase);
                    break;
                }
            }
        }
        return new Matrix(cWord);
    }

    /**
     * Converts a one row matrix into a String.
     * @param sourceCodeWord matrix which has to be converted into a String
     * @return Matrix<GaloisElement> converted into a String
     */
    private String convertOneRowMatrixToString(Matrix<GaloisElement> sourceCodeWord) {
        String s = "";
        for (int i = 0; i < sourceCodeWord.getMatrixColumnCapacity(); i++) {
            char t = (char) (sourceCodeWord.get(0, i).getGaloisElemValue().intValue() + 48);
            s += t;
        }
        return s;
    }

    /**
     * Calculates the hamming distance between the one-row-vectors a and b. a and b must have the same length.
     * @param a first vector
     * @param b second vector
     * @return hamming distance
     * @throws IllegalArgumentException if a or b have more than one row
     * @throws IllegalArgumentException if a has different columnCapacity as b
     */
    public static int hammingDistance(Matrix<GaloisElement> a, Matrix<GaloisElement> b) throws IllegalArgumentException {
        if (a.getMatrixRowCapacity() > 1 || b.getMatrixRowCapacity() > 1) {
            throw new IllegalArgumentException("Only matrices with one row are allowed!");
        }
        if (a.getMatrixColumnCapacity() != b.getMatrixColumnCapacity()) {
            throw new IllegalArgumentException("Matrices should have same ColumnCapacity!");
        }
        int distance = 0;
        for (int i = 0; i < a.getMatrixColumnCapacity(); i++) {
            if (a.get(0, i) != b.get(0, i)) {
                distance++;
            }
        }
        return distance;
    }

    /**
     * Calculates the vector weight between the one-row-vectors a and the null vector.
     * @param a vector which should be calculated
     * @return calculated vector weight
     */
    public static int vectorWeight(Matrix<GaloisElement> a) {
        KryptoType<GaloisElement> k[][] = new KryptoType[1][a.getMatrixColumnCapacity()];
        for (int i = 0; i < a.getMatrixColumnCapacity(); i++) {
            k[0][i] = new GaloisElement(0, 2);
        }
        Matrix<GaloisElement> b = new Matrix(k);
        return hammingDistance(a, b);
    }

    /**
     * Calculates a bitError based on the given probability
     * @param probability value between 0 and 1
     * @return new GaloisElement with value 0 or 1
     */
    private GaloisElement bitErrorProbability(double probability) {
        int rand = (int) Math.floor((Math.random() * 999) + 1);
        if (rand > probability * 1000) {
            return new GaloisElement(0, this.galoisBase);
        } else {
            return new GaloisElement(1, this.galoisBase);
        }
    }

    /**
     * Generates a bitError with given probability in encodedWord.
     * @param probability value between 0 and 1
     */
    public void generateBitError(double probability) {
        int length = encodedWord.getMatrixColumnCapacity();
        for (int i = 0; i < length; i++) {
            GaloisElement tmp = encodedWord.get(0, i);
            encodedWord.set(0, i, tmp.add(bitErrorProbability(probability)));
        }
    }
}
