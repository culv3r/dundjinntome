package io.roll.dundjinntome.util;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import io.roll.dundjinntome.data.CharInst;

/* This class is designed to generate skills and attributes to feed to the 'saved' characters.
 */
public class CharGenHelper {

    public CharGenHelper(String name, String charClass){
        CharInst instance = CharInst.getInstance();
        instance.setName(name);
        instance.setClass(charClass);
        setDetails(instance);
    }

    private static void setDetails(CharInst instance){
        initStats(instance);

    }

    private static void initStats(CharInst instance){
        int die1, die2, die3, die4, min, max;
        ArrayList<Integer> dice = new ArrayList<Integer>();
        min = 1;
        max = 6;
        die1 = 0;
        die2 = 0;
        die3 = 0;
        die4 = 0;
        dice.add(die1);
        dice.add(die2);
        dice.add(die3);
        dice.add(die4);
        int[] attrScores = new int[6];
        for(int i = 0; i<6; i++) {
            for (int die : dice) {
                die = ThreadLocalRandom.current().nextInt(min, max + 1);
            }
            int low = 0;
            int sum = 0;
            for (int die : dice){
                if (low == 0){
                    low = die;
                }
                if (die < low){
                    low = die;
                }
                sum += die;
            }
            sum -= low;
            attrScores[i] = sum;

        }
        instance.setAbilityScores(attrScores);

    }

}
