package edu.gatech.teamelevenproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class States {

    /**
     * An array of State objects.
     */
    public static final List<State> ITEMS = new ArrayList<>();

    /**
     * A map of states  by Name.
     */
    public static final Map<String, State> ITEM_MAP = new HashMap<>();

    public static void addItem(State item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getName(), item);
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about State: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
