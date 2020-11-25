package com.bmmart2.forbiddendesert.Components;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class BarometerTest {

    private Barometer[] barometers = init(0);

    public static Barometer[] init(int difficulty) {
        Barometer[] barometers = new Barometer[6];
        for (int i = 2; i < 6; i++) {
            barometers[i] = new Barometer(difficulty, i);
        }
        return Arrays.copyOfRange(barometers, 2, 6);
    }

    @Test
    void DifficultySettingDrawTest() {
        Barometer b = new Barometer(2, 2);
        assertEquals(b.getDrawAmt(), 3);
    }

    @Test
    void DifficultySettingTickTest() {
        final int handicap = 2;
        Barometer b = new Barometer(handicap,4);
        assertEquals(handicap, b.getTick());
    }


    @Test
    void getDrawAmtForFirstTurn() {
        assertTrue(Arrays.stream(barometers).allMatch(b -> (b.getDrawAmt() == 2)));
    }

    @Test
    void getDrawAmtForZeroDifficulty() {
        int[] expected = {4, 4, 4, 4};
        assertTrue(getDrawAmtTestHelper(barometers, 7, expected));

        expected = new int[]{4, 4, 4, 3};
        assertTrue(getDrawAmtTestHelper(barometers, 5, expected));

        expected = new int[]{4, 3, 3, 3};
        assertTrue(getDrawAmtTestHelper(barometers, 4, expected));
    }

    private static boolean getDrawAmtTestHelper(Barometer[] input, int tickIncrease, int[] expected) {
        Barometer[] cpy = copyBarometerArray(input);
        Arrays.stream(cpy).forEach(b -> b.increase(tickIncrease));
        ArrayList<Integer> output = new ArrayList<>();
        Arrays.stream(cpy).forEach(b -> output.add(b.getDrawAmt()));

        return Arrays.equals(output.stream().mapToInt(i -> i).toArray(), expected);

    }

    private static Barometer[] copyBarometerArray(Barometer[] input) {
        Barometer[] cpy = new Barometer[input.length];
        try {
            for (int i = 0; i < cpy.length; i++) {
                cpy[i] = (Barometer)input[i].clone();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return cpy;
    }
}