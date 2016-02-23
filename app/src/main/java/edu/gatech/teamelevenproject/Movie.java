package edu.gatech.teamelevenproject;

import java.io.Serializable;

/**
 * Created by robertwaters on 2/13/16.
 */
public class Movie implements Serializable {
    private String name;
    private String year;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String toString() {
        return "Title: " + name + "\n" +  "Year: " + year;
    }

}
