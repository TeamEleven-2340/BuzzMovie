package edu.gaTech.teamElevenProject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines a movie to have a name
 * and a year in which it was released.
 */
public class Movie implements Serializable {

    /**
     * Name of the movie
     */
    private String name;

    /**
     * year of the movie released
     */
    private String year;

    /**
     * Genre of the movie
     */
    private String genre;

    /**
     * Actors in the movie
     */
    private String actors;

    /**
     * Length of the movie
     */
    private String length;

    /**
     * Year movie was released
     */
    private String released;

    /**
     * Ratings of the movie
     */
    private double rating;

    /**
     * Major of the people who rated the movie
     */
    private Map<String, Integer> majorPeopleRated = new HashMap<>();

    /**
     * Ratings of the movie by major
     */
    private Map<String, Double> majorRatings = new HashMap<>();

    /**
     * Number of the people who rated by majors
     */
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
     * @param setName a String of the movie's name
     */
    public void setName(String setName) {
        this.name = setName;
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
     * @param setYear a String of the movie's year
     */
    public void setYear(String setYear) {
        this.year = setYear;
    }

    /**
     * Set the genre of the movie with information we get
     * @param setGenre genre of the movie
     */
    public void setGenre(String setGenre) {
        this.genre = setGenre;
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
     * @param setActors actors in the movie
     */
    public void setActors(String setActors) {
        this.actors = setActors;
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
     * @param setLength length of the movie
     */
    public void setLength(String setLength) {
        this.length = setLength;
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
     * @param setReleased released date of the movie
     */
    public void setReleased(String setReleased) {
        this.released = setReleased;
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
     * @param setRating rating of the movie
     */
    public void setRating(double setRating) {
        this.rating = setRating;
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
     * @param setPeopleRated number of people who rated this movie so far
     */
    public void setPeopleRated(int setPeopleRated) {
        this.peopleRated = setPeopleRated;
    }

    /**
     * Get the number of the people who rated the movie
     * @return number of the people who rated this movie so far
     */
    public int getPeopleRated() {
        return peopleRated;
    }

    /**
     * Set the rating of the movie by the major
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
        return "Title: " + name + "\n"
                + "Year: " + year;
    }

    /**
     * Prints out the movies name and ratings by major.
     * @param major major that we want to show
     * @return the string with title rating and the major
     */
    public String toString2(String major) {
        return "Title: " + name + "\n" + "Ratings: "
                + getRatingByMajors().get(major);
    }

}
