package com.bmmart2.forbiddendesert.Components;

public class Barometer {

    //drawArr[i][j] = the cutoff point for turn size-j inclusive, for i-many players
    private static int[][] drawArr = {
            {},
            {},
            {-1,-1,0,3,7,10,12},
            {-1,-1,0,4,8,11,13},
            {-1,-1,0,4,8,11,13},
            {-1,-1,0,5,9,12,14}
    };

    private int tick;
    final private int playerCount;
    boolean isMaxed;

    public Barometer(int handicap, int playerCount) {
        tick = handicap;
        this.playerCount = playerCount;
        isMaxed = false;

    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public void setDrawArr(int[] drawArr) {
        this.drawArr[playerCount] = drawArr;
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
        return drawArr[playerCount];
    }

    public int getDrawAmt() {
        if (isMaxed) return drawArr[playerCount].length-1;
        int i = 2;
        while (i < 7) {
            if (tick <= drawArr[playerCount][i])
                return i;
            i++;
        }
        isMaxed = true;
        return i;
    }
}
