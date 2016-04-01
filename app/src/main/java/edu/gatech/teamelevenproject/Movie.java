package edu.gatech.teamelevenproject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines a movie to have a name and a year in which it was released.
 */
public class Movie implements Serializable {
    private String name;
    private String year;
    private String genre;
    private String actors;
    private String length;
    private String released;
    private double rating;
    private Map<String, Integer> majorPeopleRated = new HashMap<>();
    private Map<String, Double> majorRatings = new HashMap<>();
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
     * @param setname a String of the movie's name
     */
    public void setName(String setname) {
        this.name = setname;
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
     * @param setyear a String of the movie's year
     */
    public void setYear(String setyear) {
        this.year = setyear;
    }

    /**
     * Set the genre of the movie with information we get
     * @param setgenre genre of the movie
     */
    public void setGenre(String setgenre) {
        this.genre = setgenre;
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
     * @param setactors actors in the movie
     */
    public void setActors(String setactors) {
        this.actors = setactors;
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
     * @param setlength length of the movie
     */
    public void setLength(String setlength) {
        this.length = setlength;
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
     * @param setreleased released date of the movie
     */
    public void setReleased(String setreleased) {
        this.released = setreleased;
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
     * @param setrating rating of the movie
     */
    public void setRating(double setrating) {
        this.rating = setrating;
    }

    /**
     * Get the rating of the movie
     * @return rating of the movie
     */
    public double getRating() {
        return rating;
    }

    /**
     * Set the number of people who rated the movie
     * @param setpeopleRated number of people who rated this movie so far
     */
    public void setPeopleRated(int setpeopleRated) {
        this.peopleRated = setpeopleRated;
    }

    /**
     * Get the number of the people who rated the movie
     * @return number of the people who rated this movie so far
     */
    public int getPeopleRated() {
        return peopleRated;
    }

    /**
     * Set the rating of the moview by the major
     * @param key major of the rating
     * @param value rating
     */
    public void setRatingsByMajors(String key, double value) {
        majorRatings.put(key, value);
    }

    /**
     * Get the rating of the movie according to the major
     * @return the map of the rating and the major
     */
    public Map<String, Double> getRatingByMajors() {
        return majorRatings;
    }

    /**
     * Set the major of the people who rated the movie
     * @param key major of the people
     * @param i number of the people who rated
     */
    public void setPeopleByMajors(String key, int i) {
        majorPeopleRated.put(key, i);
    }

    /**
     * Get the major of people who rated the movie so far
     * @return map of the major and the number of the people who rated the movie
     */
    public Map<String, Integer> getPeopleByMajors() {
        return majorPeopleRated;
    }

    /**
     * Print out the movie's name and year.
     * @return the movie's name and year
     */
    public String toString() {
        return "Title: " + name + "\n" +  "Year: " + year;
    }

    /**
     * ToString method
     * @param major major that we want to show
     * @return the string with title rating and the major
     */
    public String toString2(String major) {
        return "Title: " + name + "\n" + "Ratings: " + getRatingByMajors().get(major);
    }

}
