package com.endava.xexercises;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Created by avas on 10/12/2015.
 * Open a text file so that you can read the file one line at a time.
 * Read each line as a String and place that String object into a LinkedList.
 * Print all of the lines in the LinkedList in reverse order.
 */
public class Exercise1 {

    public static void main(String[] args) {
        read();
    }

    static void read() {
        LinkedList<String> linkedList = new LinkedList<String>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("exercise1.txt")));
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                linkedList.add(s);
            }

        } catch (Exception e) {

        }
        for (int i = linkedList.size()-1; i >= 0; i--) {
            System.out.println(linkedList.get(i));
        }
    }

}
