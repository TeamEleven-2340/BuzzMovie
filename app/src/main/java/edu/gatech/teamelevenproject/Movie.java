package edu.gatech.teamelevenproject;

import java.io.Serializable;

/**
 * Class defines a movie to have a name and a year in which it was released.
 */
public class Movie implements Serializable {
    private String name;
    private String year;


    /**
     * Return the name of a movie.
     * @return the movie's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of a movie.
     * @param name a String of the movie's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the year that a movie was released.
     * @return the movie's year
     */
    public String getYear() {
        return year;
    }

    /**
     * Set the year that a movie was released
     * @param year a String of the movie's year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Print out the movie's name and year.
     * @return the movie's name and year
     */
    public String toString() {
        return "Title: " + name + "\n" +  "Year: " + year;
    }

}
