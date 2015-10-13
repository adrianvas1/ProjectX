package com.endava.xfiles;


import java.io.*;

/**
 * Created by avas on 10/8/2015.
 */
public class Writer1 {
    public static void main(String[] args) {
        Collar collar = new Collar("red");
        Dog dog = new Dog("coco", collar);

        //System.out.println(dog);

        File file = new File("write1.txt");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dog);

        }catch (Exception e) {
            System.out.println(e);
        }

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            System.out.print(ois.readObject());

        }catch (Exception e) {

        }

    }
}
