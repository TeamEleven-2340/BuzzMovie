package edu.gatech.teamelevenproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class Movies {

    /**
     * An array of Movie objects.
     */
    public static final List<Movie> ITEMS = new ArrayList<>();

    /**
     * A map of states  by Name.
     */
    public static final Map<String, Movie> ITEM_MAP = new HashMap<>();

    /**
     * Add items to Movies array
     * @param item
     */

    public static void addItem(Movie item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getName(), item);
    }

}
