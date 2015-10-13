package com.endava.xexercises;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Created by avas on 10/12/2015.
 * Modify Exercise 1 so that the name of the file you read is provided as a command-line argument.
 */
public class Exercise2 {

    public static void main(String[] args) {
        read();
    }

    static void read() {
        LinkedList<String> linkedList = new LinkedList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the file you want to read from: ");
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(scanner.next())));
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
