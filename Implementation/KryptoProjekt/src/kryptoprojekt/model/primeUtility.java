/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

//import java.util.BitSet;
//import java.util.LinkedList;
import java.util.Random;
import java.util.TreeSet;
/**
 *
 * @author Michael
 */
public class PrimeUtility {

    public static TreeSet<Integer> getRandomNumber(int startRange, int endRange, int number){
        Random randomNumber = new Random();
        //BitSet bitS = new BitSet();
        TreeSet<Integer> probNumberList = new TreeSet<Integer>();
        //int probNumber;
        int checkLoop = 0;
        for(int i = 0; probNumberList.size()<number; i++){
            probNumberList.add(startRange + (randomNumber.nextInt(endRange)%(endRange-startRange)));
            /*if(!bitS.get(probNumber)){
                bitS.set(probNumber);
            }*/
            checkLoop++;
            if(checkLoop > 20000){
                break;
            }
        }
        return probNumberList;
    }
}
