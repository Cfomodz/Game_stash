package com.gamestash.app;

public class DFilter {

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
