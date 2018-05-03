package io.roll.dundjinntome.util;

import java.util.concurrent.ThreadLocalRandom;

public enum Dice {
    D4(1,4),
    D6(1,6),
    D8(1,8),
    D10(1,10),
    D12(1,12),
    D20(1,20),
    D100(1,100);

    private final int min;
    private final int max;
    Dice(int min, int max){
        this.min = min;
        this.max = max;
    }

    public static int rollResult(int qty, int modifier, Dice d){
        return ThreadLocalRandom.current().nextInt(d.min*qty, d.max*qty) + modifier;
    }
}
