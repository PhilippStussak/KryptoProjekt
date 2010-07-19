/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package kryptoprojekt.model;

import java.util.Random;
import java.util.TreeSet;
/**
 *
 * @author Michael
 */
public class PrimeUtility {

    public static TreeSet<Integer> getRandomNumber(int startRange, int endRange, int number){
        Random randomNumber = new Random();
        TreeSet<Integer> probNumberList = new TreeSet<Integer>();
        int checkLoop = 0;
        if(endRange == 2){
            probNumberList.add(2);
            return probNumberList;
        }
        for(int i = 0; probNumberList.size()<number; i++){
            probNumberList.add(startRange + (randomNumber.nextInt(endRange)%(endRange-startRange)));
            checkLoop++;
            if(checkLoop > 20000){
                break;
            }
        }
        return probNumberList;
    }
}
