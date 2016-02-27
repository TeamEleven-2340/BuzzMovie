package edu.gatech.teamelevenproject;

import java.io.Serializable;

/**
 * Class defines a movie to have a name and a year in which it was released.
 */
public class Movie implements Serializable {
    private String name;
    private String year;
    private String genre;
    private String actors;
    private String length;
    private String released;
    private float rating;
    private int peopleRated = 0;


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
     * Set the genre of the movie with information we get
     * @param genre genre of the movie
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Get the genre of the movie
     * @return the genre of the movie
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Set the actors in the movie
     * @param actors actors in the movie
     */
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**
     * Get the actors in the movie
     * @return actors in the movie
     */
    public String getActors() {
        return actors;
    }

    /**
     * Set the length of the movie
     * @param length length of the movie
     */
    public void setLength(String length) {
        this.length = length;
    }

    /**
     * Get the length of the movie
     * @return length of the movie
     */
    public String getLength() {
        return length;
    }

    /**
     * Set the released date of the movie
     * @param released released date of the movie
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     * Get the released date of the movie
     * @return released date of the movie
     */
    public String getReleased() {
        return released;
    }

    /**
     * Set the rating of the movie
     * @param rating rating of the movie
     */
    public void setRating(float rating) {
        this.rating = rating;
    }

    /**
     * Get the rating of the movie
     * @return rating of the movie
     */
    public float getRating() {
        return rating;
    }

    /**
     * Set the number of people who rated the movie
     * @param peopleRated number of people who rated this movie so far
     */
    public void setPeopleRated(int peopleRated) {
        this.peopleRated = peopleRated;
    }

    /**
     * Get the number of the people who rated the movie
     * @return number of the people who rated this movie so far
     */
    public int getPeopleRated() {
        return peopleRated;
    }

    /**
     * Print out the movie's name and year.
     * @return the movie's name and year
     */
    public String toString() {
        return "Title: " + name + "\n" +  "Year: " + year;
    }

}
