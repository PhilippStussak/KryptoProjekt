/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author LiTTle, Mario
 */
public class HammingCode extends Coder {

    private Matrix<Z> controlMatrix;
    private Matrix<Z> generatorMatrix;
    private Matrix<Z> sourceCodeWord;
    private Matrix<Z> encodedWord;
    private Matrix<Z> syndrom;
    private Matrix<Z> decodedWord;

    @Override
    public String encode() {
        this.encodedWord = this.sourceCodeWord.multiply(this.generatorMatrix);
        return convertMatrixToString(this.encodedWord);
    }

    @Override
    public void calculateSyndrom() {
        this.syndrom = this.encodedWord.multiply(this.controlMatrix);
    }

    @Override
    public Matrix<Z> detectError() {
        for (int i = 0; i < this.syndrom.getMatrixColumnCapacity(); i++) {
        }
        throw new UnsupportedOperationException("Not supported yet.");
        //values of type Matrix have to be accessible
        //TODO what should be returned

    }

    @Override
    public String printStatistics() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String decode() {
        KryptoType<Z>[][] t = new KryptoType[1][this.codeWord.length() - 1];
        //values of type Matrix have to be accessible
        this.decodedWord = new Matrix(t);
        return convertMatrixToString(this.decodedWord);
    }

    private void generateGeneratorMatrix(int codewordLength) {
        int columnCapacity = (int) Math.pow(2, codewordLength) - 1;
        KryptoType<Z>[][] t = new KryptoType[codewordLength][columnCapacity];

        //initialise array with Z-objects, value=0
        for (int i = 0; i < codewordLength; i++) {
            for (int j = 0; j < columnCapacity; j++) {
                t[i][j] = new Z(0);
            }
        }

        //generate identity matrix
        for (int i = 0; i < codewordLength; i++) {
            t[i][i] = new Z(1);
        }

        //set parity bits
        for (int i = 0; i < codewordLength; i++) {
            for (int j = codewordLength; j < (int) Math.pow(2, codewordLength) - 1; j++) {
                if (i != j) {
                    t[i][j] = new Z(1);
                }
            }
        }

        this.generatorMatrix = new Matrix(t);
    }

    private void generateControlMatrix() {
         //values of type Matrix have to be accessible
    }

    public HammingCode(Matrix<Z> generatorMatrix, String codeWord) {
        this.codeWord = codeWord;
        this.sourceCodeWord = convertStringToMatrix(codeWord);
        if (Math.pow(2, codeWord.length()) - 1 == generatorMatrix.getMatrixColumnCapacity()) {
            this.generatorMatrix = generatorMatrix;
            generateControlMatrix();
        } else {
            throw new IllegalArgumentException("Codewortlänge stimmt nicht mit Länge der Generatormatrix überein.");
        }
    }

    public HammingCode(String codeWord) {
        this.codeWord = codeWord;
        this.sourceCodeWord = convertStringToMatrix(codeWord);
        generateGeneratorMatrix(codeWord.length());
        generateControlMatrix();
    }

    private Matrix<Z> convertStringToMatrix(String sourceCodeWord) {
        KryptoType<Z> cWord[][] = new KryptoType[1][this.codeWord.length()];
        char[] t = sourceCodeWord.toCharArray();
        for (int i = 0; i < sourceCodeWord.length(); i++) {
            if (t[i] == '0') {
                cWord[0][i] = new Z(0);
            } else {
                cWord[0][i] = new Z(1);
            }
        }
        return new Matrix(cWord);
    }

    private String convertMatrixToString(Matrix<Z> sourceCodeWord) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
