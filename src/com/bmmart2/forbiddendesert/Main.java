package com.bmmart2.forbiddendesert;


import com.bmmart2.forbiddendesert.Components.Board;

import java.util.Collections;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
                System.out.println("test");
                System.out.println("Hello World");
                Board b = new Board();
                LinkedList<Integer> q = new LinkedList<Integer>();
                q.add(1);
                q.add(2);
                q.add(3);
                q.add(4);
                q.add(5);
                for (int i = 6; i < 1000; i++)
                    q.add(i);
                Collections.shuffle(q);
                System.out.println(q.pollFirst());
        System.out.println(q.pollFirst());
        System.out.println(q.pollFirst());
        System.out.println(q.pollFirst());
    }
}
