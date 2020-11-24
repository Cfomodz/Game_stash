package com.gamestash.app;

import com.google.gson.annotations.SerializedName;

public class DGame {
    //Member variables.
    private static final String TAG = DGame.class.getSimpleName();

    // API variables.
    private @SerializedName("id") String gameID = "";
    private @SerializedName("name") String gameName = "";
    private @SerializedName("year_published") int yearPublished = 0;
    private @SerializedName("min_players")  int minPlayers = -1;
    private @SerializedName("max_players") int maxPlayers = -1;
    private @SerializedName("min_playtime") int minPlayTime = -1;
    private @SerializedName("max_playtime") int maxPlayTime = -1;
    private @SerializedName("min_age") int minAge = -1;
    private String description = "";
    private @SerializedName("image_url") String imageURL = "";
    private @SerializedName("thumb_url") String thumbURL = "";
    private String url = "";
    private float price = 0;
    private float msrp = 0;
    private @SerializedName("primary_publisher")
    DPublisher publisher = new DPublisher();
    //private Map<String, String> mechanics;

    // Edited variables.
    private String editedGameName;
    private int editedYearPublished;
    private int editedMinPlayers;
    private int editedMaxPlayers;
    private int editedMinPlayTime;
    private int editedMaxPlayTime;
    private int editedMinAge;
    private String editedDescription;
    private String editedThumbURL;
    private String editedImageURL;
    private DPublisher editedPublisher;

    //Owner's Storage Location
    private String location = "";

    //Promotional Content or expansion
    private Boolean expansion = false;

    // Constructors.

    /** This constructor will likely create a new game using GSON from API data. */
    public DGame(){}

    /** This constructor will be used when there is an empty list. */
    public DGame(String gameName){
        this.gameName = gameName;
    }

    /**
     * Use this constructor to create a game from API data.
     * Most likely not used.
     */
    public DGame(String gameID,
                 String gameName,
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
                 DPublisher publisher) {
        this.gameID = gameID;
        this.gameName = gameName;
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
        this.publisher = publisher;
    }

    /**
     * Use this to constructor to create a custom game.
     */
    public DGame(String editedGameName,
                 int editedYearPublished,
                 int editedMinPlayers,
                 int editedMaxPlayers,
                 int editedMinPlayTime,
                 int editedMaxPlayTime,
                 int editedMinAge,
                 String editedDescription,
                 String editedThumbURL,
                 String editedImageURL,
                 DPublisher editedPublisher,
                 String location) {
        this.editedGameName = editedGameName;
        this.editedYearPublished = editedYearPublished;
        this.editedMinPlayers = editedMinPlayers;
        this.editedMaxPlayers = editedMaxPlayers;
        this.editedMinPlayTime = editedMinPlayTime;
        this.editedMaxPlayTime = editedMaxPlayTime;
        this.editedMinAge = editedMinAge;
        this.editedDescription = editedDescription;
        this.editedThumbURL = editedThumbURL;
        this.editedImageURL = editedImageURL;
        this.editedPublisher = editedPublisher;
        this.location = location;
    }

    /**
     * Use this constructor to add a game... possibly with edits...
     */
    public DGame(String gameID,
                 String gameName,
                 int yearPublished,
                 int minPlayers,
                 int maxPlayers,
                 int minPlayTime,
                 int maxPlayTime,
                 int minAge,
                 String description,
                 String imageURL,
                 String thumbURL,
                 String url,
                 float price,
                 float msrp,
                 DPublisher publisher,
                 String editedGameName,
                 int editedYearPublished,
                 int editedMinPlayers,
                 int editedMaxPlayers,
                 int editedMinPlayTime,
                 int editedMaxPlayTime,
                 int editedMinAge,
                 String editedDescription,
                 String editedThumbURL,
                 String editedImageURL,
                 DPublisher editedPublisher,
                 String location) {
        this.gameID = gameID;
        this.gameName = gameName;
        this.yearPublished = yearPublished;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.minPlayTime = minPlayTime;
        this.maxPlayTime = maxPlayTime;
        this.minAge = minAge;
        this.description = description;
        this.imageURL = imageURL;
        this.thumbURL = thumbURL;
        this.url = url;
        this.price = price;
        this.msrp = msrp;
        this.publisher = publisher;
        this.editedGameName = editedGameName;
        this.editedYearPublished = editedYearPublished;
        this.editedMinPlayers = editedMinPlayers;
        this.editedMaxPlayers = editedMaxPlayers;
        this.editedMinPlayTime = editedMinPlayTime;
        this.editedMaxPlayTime = editedMaxPlayTime;
        this.editedMinAge = editedMinAge;
        this.editedDescription = editedDescription;
        this.editedThumbURL = editedThumbURL;
        this.editedImageURL = editedImageURL;
        this.editedPublisher = editedPublisher;
        this.location = location;
    }

    // Methods.
    /**
     * Re-pull the JSON data to override the price.
     */
    public void refreshPrice() {

    }

    // Setters.
    public void setGameID(String gameID) {this.gameID = gameID;}

    public void setGameName(String gameName) {this.gameName = gameName;}

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

    public void setPublisher(DPublisher publisher) {this.publisher = publisher;}

    public void setEditedGameName(String editedGameName) {this.editedGameName = editedGameName;}

    public void setEditedYearPublished(int editedYearPublished) {this.editedYearPublished = editedYearPublished;}

    public void setEditedMinPlayers(int editedMinPlayers) {this.editedMinPlayers = editedMinPlayers;}

    public void setEditedMaxPlayers(int editedMaxPlayers) {this.editedMaxPlayers = editedMaxPlayers;}

    public void setEditedMinPlayTime(int editedMinPlayTime) {this.editedMinPlayTime = editedMinPlayTime;}

    public void setEditedMaxPlayTime(int editedMaxPlayTime) {this.editedMaxPlayTime = editedMaxPlayTime;}

    public void setEditedMinAge(int editedMinAge) {this.editedMinAge = editedMinAge;}

    public void setEditedDescription(String editedDescription) {this.editedDescription = editedDescription;}

    public void setEditedThumbURL(String editedThumbURL) {this.editedThumbURL = editedThumbURL;}

    public void setEditedImageURL(String editedImageURL) {this.editedImageURL = editedImageURL;}

    public void setEditedPublisher(DPublisher editedPublisher) {this.editedPublisher = editedPublisher;}

    public void setLocation(String location) {this.location = location;}

    public void setExpansion(Boolean expansion) {this.expansion = expansion;}

    // Getters.
    public String getGameID() {return gameID;}

    public String getGameName() {return gameName;}

    public String getUrl() {return url;}

    public float getPrice() {return price;}

    public float getMsrp() {return msrp;}

    public int getYearPublished() {return yearPublished;}

    public int getMinPlayers() {return minPlayers;}

    public int getMaxPlayers() {return maxPlayers;}

    public int getMinPlayTime() {return minPlayTime;}

    public int getMaxPlayTime() {return maxPlayTime;}

    public int getMinAge() {return minAge;}

    public String getDescription() {return description;}

    public String getImageURL() {return imageURL;}

    public String getThumbURL() {return thumbURL;}

    public DPublisher getPublisher() {return publisher;}

    public String getEditedGameName() {return editedGameName;}

    public int getEditedYearPublished() {return editedYearPublished;}

    public int getEditedMinPlayers() {return editedMinPlayers;}

    public int getEditedMaxPlayers() {return editedMaxPlayers;}

    public int getEditedMinPlayTime() {return editedMinPlayTime;}

    public int getEditedMaxPlayTime() {return editedMaxPlayTime;}

    public int getEditedMinAge() {return editedMinAge;}

    public String getEditedDescription() {return editedDescription;}

    public String getEditedThumbURL() {return editedThumbURL;}

    public String getEditedImageURL() {return editedImageURL;}

    public DPublisher getEditedPublisher() {return editedPublisher;}

    public String getLocation() {return location;}

    public Boolean getExpansion() {return expansion;}

    //Getters that return edits over default api content...
    public String getVisibleGameName() {
        if (editedGameName != null && !editedGameName.equals("")) {
            return editedGameName;
        } else {
            return gameName;
        }
    }

    public int getVisibleYearPublished() {
        if(editedYearPublished > 0) {
            return editedYearPublished;
        } else {
            return yearPublished;
        }
    }

    public int getVisibleMinPlayers() {
        if(editedMinPlayers > -1) {
            return editedMinPlayers;
        } else {
            return minPlayers;
        }
    }

    public int getVisibleMaxPlayers() {
        if(editedMaxPlayers > -1) {
            return editedMaxPlayers;
        } else {
            return maxPlayers;
        }
    }

    public int getVisibleMinPlayTime() {
        if(editedMinPlayTime > -1) {
            return editedMinPlayTime;
        } else {
            return minPlayTime;
        }
    }

    public int getVisibleMaxPlayTime() {
        if (editedMaxPlayTime > -1) {
            return editedMaxPlayTime;
        } else {
            return maxPlayTime;
        }
    }

    public int getVisibleMinAge() {
        if (editedMinAge > -1) {
            return editedMinAge;
        } else {
            return minAge;
        }
    }

    public String getVisibleDescription() {
        if (editedDescription != null && !editedDescription.equals("")) {
            return editedDescription;
        } else {
            return description;
        }
    }

    public String getVisibleThumbURL() {
        if (editedThumbURL != null && !editedThumbURL.equals("")) {
            return editedThumbURL;
        } else {
            return thumbURL;
        }
    }

    public String getVisibleImageURL() {
        if (editedImageURL != null && !editedImageURL.equals("")) {
            return editedImageURL;
        } else {
            return imageURL;
        }
    }

    public DPublisher getVisiblePublisher() {
        if (editedPublisher != null) {
            return editedPublisher;
        } else {
            return publisher;
        }
    }
}