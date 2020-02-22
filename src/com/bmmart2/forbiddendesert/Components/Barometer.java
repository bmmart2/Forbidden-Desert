package com.bmmart2.forbiddendesert.Components;

import java.util.LinkedList;

public class Barometer {
    private int tick;
    private int[] drawArr = {-1,-1,0,5,9,12,14};

    public Barometer() {
        tick = 0;
    }

    public Barometer(int difficulty) {
        tick = 0;
        //TODO: add templates for different player sizes
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void setDrawArr(int[] drawArr) {
        this.drawArr = drawArr;
    }

    public int increase() {
        tick++;
        return tick;
    }

    public int increase(int n) {
        tick += n;
        return tick;
    }

    public int getTick() {
        return tick;
    }

    public int[] getDrawArr() {
        return drawArr;
    }

    public int getDrawAmt() {
        int i = 2;
        while (i < 7) {
            if (tick <= drawArr[i]) {
                return i;
            }
        }
        return -1;
    }
}
