package org.cis1200.twentyfourtyeight;
import java.util.*;

public class Tile implements Comparable<Tile> {

    private int value;
    private String color;

    public Tile(int value, String color) {
        if ((value >= 2 || value == 0) && value % 2 == 0 && color != null) {
            this.value = value;
            this.color = color;
        } else {
            throw new IllegalArgumentException();
        }

    }
    public void doubleValue() {
        this.value = 2 * this.value;
        this.color = Integer.toString(value);
    }

    public int getValue() {
        return this.value;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    @Override
    public int compareTo(Tile o) {
        if (o.getValue() == this.value) {
            return 0;
        } else if (o.getValue() < this.value) {
            return 1;
        } else {
            return -1;
        }
    }

    public boolean equals(Tile o) {
        return (o.getValue() == this.value);
    }
}
