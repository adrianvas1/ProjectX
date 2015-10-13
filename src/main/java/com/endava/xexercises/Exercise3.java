package com.endava.xexercises;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by avas on 10/12/2015.
 * Modify Exercise 2 to also open a text file so you can write text into it.
 * Write the lines in the ArrayList, along with line numbers (do not attempt to use the “LineNumber” classes), out to the file.
 * Modify Exercise 2 to force all the lines in the ArrayList to uppercase and send the results to System.out.
 */
public class Exercise3 {

    public static void main(String[] args) {

        read();

    }

    static void read() {
        LinkedList<String> linkedList = new LinkedList<String>();
        Scanner scanner = new Scanner(System.in);
        Runtime rt = Runtime.getRuntime();
        System.out.println("Enter the name of the file you want to read from: ");
        String scan = scanner.next();
        String s;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(scan)));
            rt.exec("notepad " + scan);
            while ((s = bufferedReader.readLine()) != null) {
                linkedList.add(s);
            }

        } catch (Exception e) {

        }
        for (int i = linkedList.size()-1; i >= 0; i--) {
            System.out.println(linkedList.get(i).toUpperCase());
        }
    }

}
