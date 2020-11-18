package com.example.game_stash;

import com.google.gson.annotations.SerializedName;

public class MGame {
    /**
     * Member variables.
     */
    private static final String fName = VMainMenu.class.getSimpleName();
    private static final String TAG = fName + ":";

    // API variables.
    private String id;
    private String name;
    private @SerializedName("year_published") int yearPublished;
    private @SerializedName("min_players")  int minPlayers;
    private @SerializedName("max_players") int maxPlayers;
    private @SerializedName("min_playtime") int minPlayTime;
    private @SerializedName("max_playtime") int maxPlayTime;
    private @SerializedName("min_age") int minAge;
    private String description;
    private @SerializedName("image_url") String imageURL;
    private @SerializedName("thumb_url") String thumbURL;
    private String url;
    private float price;
    private float msrp;
    private @SerializedName("primary_publisher") String primaryPublisher;
    //private Map<String, String> mechanics;

    // Edited variables.
    private String editedName;
    private int editedYearPublished;
    private int editedMinPlayers;
    private int editedMaxPlayers;
    private int editedMinPlayTime;
    private int editedMaxPlayTime;
    private int editedMinAge;
    private String editedDescription;
    private String editedThumbURL;
    private String editedImageURL;
    private String editedPrimaryPublisher;

    // Constructors.

    /** This constructor will likely create a new game using GSON from API data. */
    public MGame(){}

    /** This constructor will be used when there is an empty list. */
    public MGame(String name){
        this.name = name;
    }

    /**
     * Use this constructor to create a game from API data.
     * Most likely not used.
     */
    public MGame(String id,
                 String name,
                 int yearPublished,
                 int minPlayers,
                 int maxPlayers,
                 int minPlayTime,
                 int maxPlayTime,
                 int minAge,
                 String description,
                 String thumbURL,
                 String imageURL,
                 String url,
                 float price,
                 float msrp,
                 String primaryPublisher) {
        this.id = id;
        this.name = name;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minPlayTime = minPlayTime;
        this.maxPlayTime = maxPlayTime;
        this.minAge = minAge;
        this.description = description;
        this.thumbURL = thumbURL;
        this.imageURL = imageURL;
        this.url = url;
        this.price = price;
        this.msrp = msrp;
        this.primaryPublisher = primaryPublisher;
    }

    /**
     * Use this to constructor to create a custom game.
     */
    public MGame(String editedName,
                 int editedYearPublished,
                 int editedMinPlayers,
                 int editedMaxPlayers,
                 int editedMinPlayTime,
                 int editedMaxPlayTime,
                 int editedMinAge,
                 String editedDescription,
                 String editedThumbURL,
                 String editedImageURL,
                 String editedPrimaryPublisher) {
        this.editedName = editedName;
        this.editedYearPublished = editedYearPublished;
        this.editedMinPlayers = editedMinPlayers;
        this.editedMaxPlayers = editedMaxPlayers;
        this.editedMinPlayTime = editedMinPlayTime;
        this.editedMaxPlayTime = editedMaxPlayTime;
        this.editedMinAge = editedMinAge;
        this.editedDescription = editedDescription;
        this.editedThumbURL = editedThumbURL;
        this.editedImageURL = editedImageURL;
        this.editedPrimaryPublisher = editedPrimaryPublisher;
    }

    // Methods.
    /**
     * Re-pull the JSON data to override the price.
     */
    public void refreshPrice() {

    }

    // Getters.
    public String getId() {return id;}

    public String getName() {return name;}

    public int getYearPublished() {return yearPublished;}

    public int getMinPlayers() {return minPlayers;}

    public int getMaxPlayers() {return maxPlayers;}

    public int getMinPlayTime() {return minPlayTime;}

    public int getMaxPlayTime() {return maxPlayTime;}

    public int getMinAge() {return minAge;}

    public String getDescription() {return description;}

    public String getThumbURL() {return thumbURL;}

    public String getImageURL() {return imageURL;}

    public String getUrl() {return url;}

    public float getPrice() {return price;}

    public float getMsrp() {return msrp;}

    public String getPrimaryPublisher() {return primaryPublisher;}

    public String getEditedName() {return editedName;}

    public int getEditedYearPublished() {return editedYearPublished;}

    public int getEditedMinPlayers() {return editedMinPlayers;}

    public int getEditedMaxPlayers() {return editedMaxPlayers;}

    public int getEditedMinPlayTime() {return editedMinPlayTime;}

    public int getEditedMaxPlayTime() {return editedMaxPlayTime;}

    public int getEditedMinAge() {return editedMinAge;}

    public String getEditedDescription() {return editedDescription;}

    public String getEditedThumbURL() {return editedThumbURL;}

    public String getEditedImageURL() {return editedImageURL;}

    public String getEditedPrimaryPublisher() {return editedPrimaryPublisher;}


    // Setters.
    public void setId(String id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setYearPublished(int yearPublished) {this.yearPublished = yearPublished;}

    public void setMinPlayers(int minPlayers) {this.minPlayers = minPlayers;}

    public void setMaxPlayers(int maxPlayers) {this.maxPlayers = maxPlayers;}

    public void setMinPlayTime(int minPlayTime) {this.minPlayTime = minPlayTime;}

    public void setMaxPlayTime(int maxPlayTime) {this.maxPlayTime = maxPlayTime;}

    public void setMinAge(int minAge) {this.minAge = minAge;}

    public void setDescription(String description) {this.description = description;}

    public void setThumbURL(String thumbURL) {this.thumbURL = thumbURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

    public void setUrl(String url) {this.url = url;}

    public void setPrice(float price) {this.price = price;}

    public void setMsrp(float msrp) {this.msrp = msrp;}

    public void setPrimaryPublisher(String primaryPublisher) {this.primaryPublisher = primaryPublisher;}

    public void setEditedName(String editedName) {this.editedName = editedName;}

    public void setEditedYearPublished(int editedYearPublished) {this.editedYearPublished = editedYearPublished;}

    public void setEditedMinPlayers(int editedMinPlayers) {this.editedMinPlayers = editedMinPlayers;}

    public void setEditedMaxPlayers(int editedMaxPlayers) {this.editedMaxPlayers = editedMaxPlayers;}

    public void setEditedMinPlayTime(int editedMinPlayTime) {this.editedMinPlayTime = editedMinPlayTime;}

    public void setEditedMaxPlayTime(int editedMaxPlayTime) {this.editedMaxPlayTime = editedMaxPlayTime;}

    public void setEditedMinAge(int editedMinAge) {this.editedMinAge = editedMinAge;}

    public void setEditedDescription(String editedDescription) {this.editedDescription = editedDescription;}

    public void setEditedThumbURL(String editedThumbURL) {this.editedThumbURL = editedThumbURL;}

    public void setEditedImageURL(String editedImageURL) {this.editedImageURL = editedImageURL;}

    public void setEditedPrimaryPublisher(String editedPrimaryPublisher) {this.editedPrimaryPublisher = editedPrimaryPublisher;}

}
