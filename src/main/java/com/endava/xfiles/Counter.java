package com.endava.xfiles;

import java.io.*;

/**
 * Created by avas on 10/12/2015.
 * <p/>
 * Program that counts the number of appearances of a character typed from the console, in a .txt file
 */
public class Counter {

    public static void main(String[] args) throws IOException {
        System.out.println("Character counter; Enter character to count, enter '00' to quit;");


        InputStreamReader character = new InputStreamReader(System.in);
      /*  do {
            System.out.print("Enter character: ");
            char s = (char) character.read();
            System.out.println("Number of character " + s + ": " + count(s));
        }
        while ((char) character.read() != '0');*/
        enter((char) character.read());


    }

    static int count(char c) {
        int counter = 0;
        try {
            FileInputStream fileInput = new FileInputStream("text.txt");
            int r;
            while ((r = fileInput.read()) != -1) {
                if ((char) r == c) {
                    counter += 1;
                }
            }
            fileInput.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return counter;
    }

    static void enter(char c) {
        try {
            FileWriter fileWriter = new FileWriter("text.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(c);
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }


}
