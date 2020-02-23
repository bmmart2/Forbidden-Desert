package com.bmmart2.forbiddendesert.Components;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class BarometerTest {


    @Test
    void getDrawAmt() {
        Barometer b = new Barometer();
        b.increase(12);
        assertEquals(b.getDrawAmt(), 5);
        Barometer c = new Barometer();
        b.increase(14);
        assertEquals(b.getDrawAmt(), 6);
        c.setTick(0);
        assertEquals(c.getDrawAmt(), 2);
    }
}