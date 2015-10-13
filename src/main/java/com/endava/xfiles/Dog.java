package com.endava.xfiles;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by avas on 10/9/2015.
 */
public class Dog implements Serializable {

    private transient Collar collar;
    public String name;

    public Dog(String name, Collar collar) {
        this.name = name;
        this.collar = collar;
    }

    private void writeObject(ObjectOutputStream os) {
        try {
            os.defaultWriteObject(); // 2
            os.writeObject(collar.getColour()); // 3
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readObject(ObjectInputStream is) {
        try {
            is.defaultReadObject(); // 5
            collar = new Collar((String) is.readObject()); // 6
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Dog{" +
                "collar=" + collar +
                ", name='" + name + '\'' +
                '}';
    }
}
