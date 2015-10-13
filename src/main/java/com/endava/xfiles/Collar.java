package com.endava.xfiles;

/**
 * Created by avas on 10/9/2015.
 */
public class Collar {

    private String colour = "";

    public Collar(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return "Collar{" +
                "colour='" + colour + '\'' +
                '}';
    }
}
