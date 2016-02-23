package edu.gatech.teamelevenproject;

import java.io.Serializable;

/**
 * Created by robertwaters on 2/13/16.
 */
public class Movie implements Serializable {
    private String name;
    private String year;

    /**
     * Get the title of the movie
     * @return the title of the movie
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the movie for a movie item
     * @param name Name of the movie
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the released year of a movie
     * @return the released year
     */
    public String getYear() {
        return year;
    }

    /**
     * Set the released year of a movie
     * @param year the released year of a movie
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Put the title and year together so we can display them together to the users
     * @return string that displays a title and a year
     */
    public String toString() {
        return "Title: " + name + "\n" +  "Year: " + year;
    }

}
