/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kryptoprojekt.model;

/**
 *
 * @author mario
 */

//Class is not tested and finished yet
public class Polynom {

    private Z[] polynom;
    private final Z base = new Z(2);

    public Polynom(String polynom) {
        this.polynom = new Z[polynom.length()];
        for (int i = 0; i < polynom.length(); i++) {
            this.polynom[i] = new Z(polynom.charAt(i));
        }
    }

    public Polynom(int length) {
        this.polynom = new Z[length];
    }

    public void set(int pos, Z value) {
        if (pos >= 0 && pos < polynom.length) {
            polynom[pos] = value;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Z get(int pos) {
        if (pos >= 0 && pos < polynom.length) {
            return polynom[pos];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public Polynom add(Polynom other) {
        int newLength = 0;
        Polynom tmp = this;

        if (this.polynom.length > other.getLength()) {
            other = expand(other.getLength(), this.polynom.length, other);
            newLength = this.polynom.length;
        } else {
            tmp = expand(this.polynom.length, other.getLength(), this);
            newLength = other.getLength();
        }

        for (int i = 0; i < newLength; i++) {
            this.set(i, tmp.get(i).add(other.get(i)).mod(base));
        }

        return this;

    }

    private Polynom expand(int originalLength, int newLength, Polynom p) {
        Polynom result = new Polynom(newLength);

        for (int i = 0; i < newLength; i++) {
            if (i == originalLength) {
                result.set(i, new Z(0));
            } else {
                result.set(i, p.get(i));
            }
        }

        return result;
    }

    //inefficent implementation
    public Polynom multiply(Polynom other){
        Z[][] multiplicationTable = new Z[polynom.length][other.getLength()];
        int newLength = 0;
        Polynom tmp = this;


        if (this.polynom.length > other.getLength()) {
            other = expand(other.getLength(), this.polynom.length, other);
            newLength = this.polynom.length;
        } else {
            tmp = expand(this.polynom.length, other.getLength(), this);
            newLength = other.getLength();
        }

        //build up multiplication table
        for(int i=0; i<other.getLength(); i++){
            for(int j=0; j<tmp.getLength(); j++){
                if(other.get(i).intValue() == 0){
                    multiplicationTable[i][j] = new Z(0);
                }
                else{
                    multiplicationTable[i][j] = tmp.get(j);
                }
            }
        }

        

        Polynom result = new Polynom(polynom.length+other.getLength()-1);


        return this;
    }

    public int getLength() {
        return polynom.length;
    }

    public Z[] getPolynom(){
        return polynom;
    }
}
