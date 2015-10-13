package com.endava.xexercises;

import java.io.IOException;

/**
 * Created by avas on 10/13/2015.
 */
public class Exercise4 {

    public Exercise4() throws IOException {
        int ch;
        ch = System.in.read(); //reads one byte as an integer from the system window
        System.out.println(ch); // converts the int to a charater value
        //not converting the value would give the character value as an integer
    }

    public static void main(String args[]) throws IOException {
        new Exercise4();
    }

}
