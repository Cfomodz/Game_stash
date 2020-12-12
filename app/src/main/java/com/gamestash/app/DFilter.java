package com.gamestash.app;

public class DFilter {

    /**
     * DFilter allows for the semi-permanent storage of filter selections
     * within the user game list view -> filter view. These variables are
     * set by the spinners' listener functions and are gotten by the logic
     * functions determining which list of games should be returned for
     * any specific filter.
     */

    private static String category = "";
    private static String selection = "";

    public static String getCategory() {
        return category;
    }

    public static void setCategory(String category) {
        DFilter.category = category;
        DFilter.selection = "";
    }

    public static String getSelection() {
        return selection;
    }

    public static void setSelection(String selection) {
        DFilter.selection = selection;
    }
}
