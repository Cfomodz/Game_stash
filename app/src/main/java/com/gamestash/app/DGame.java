package com.gamestash.app;

import com.google.gson.annotations.SerializedName;

public class DGame {
    //Member variables.
    private static final String TAG = DGame.class.getSimpleName();

    // API variables.
    private @SerializedName("id") String gameID = "";
    private @SerializedName("name") String gameName = "";
    private @SerializedName("year_published") Integer yearPublished = -1;
    private @SerializedName("min_players")  Integer minPlayers = -1;
    private @SerializedName("max_players") Integer maxPlayers = -1;
    private @SerializedName("min_playtime") Integer minPlayTime = -1;
    private @SerializedName("max_playtime") Integer maxPlayTime = -1;
    private @SerializedName("min_age") Integer minAge = -1;
    private String description = "";
    private @SerializedName("image_url") String imageURL = "";
    private @SerializedName("thumb_url") String thumbURL = "";
    private String url = "";
    private Float price = 0f;
    private Float msrp = 0f;
    private @SerializedName("primary_publisher")
    DPublisher publisher = new DPublisher();

    // Edited variables.
    private String editedGameName = "";
    private Integer editedYearPublished = -1;
    private Integer editedMinPlayers = -1;
    private Integer editedMaxPlayers = -1;
    private Integer editedMinPlayTime = -1;
    private Integer editedMaxPlayTime = -1;
    private Integer editedMinAge = -1;
    private String editedDescription = "";
    private String editedThumbURL = "";
    private String editedImageURL = "";
    private DPublisher editedPublisher = new DPublisher();

    //General User Variables
    private Boolean isUserCreated = false;
    private Boolean favorite = false;
    private Boolean expansion = false;
    private String location = "";

    // Constructors.

    /** This constructor will likely create a new game using GSON from API data. */
    public DGame(){}

    /** This constructor will be used when there is an empty list. */
    public DGame(boolean err404){
        if(err404) {
            this.gameID = "err404";
            this.gameName = "GAME NOT FOUND";
            this.publisher.setName("Nobody Ever");
            this.minPlayers = 0;
            this.maxPlayers = 0;
            this.minAge = 404;
            this.location = "Missing Box 404";
        }
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

    public void setYearPublished(Integer yearPublished) {this.yearPublished = yearPublished;}

    public void setMinPlayers(Integer minPlayers) {this.minPlayers = minPlayers;}

    public void setMaxPlayers(Integer maxPlayers) {this.maxPlayers = maxPlayers;}

    public void setMinPlayTime(Integer minPlayTime) {this.minPlayTime = minPlayTime;}

    public void setMaxPlayTime(Integer maxPlayTime) {this.maxPlayTime = maxPlayTime;}

    public void setMinAge(Integer minAge) {this.minAge = minAge;}

    public void setDescription(String description) {this.description = description;}

    public void setThumbURL(String thumbURL) {this.thumbURL = thumbURL;}

    public void setImageURL(String imageURL) {this.imageURL = imageURL;}

    public void setUrl(String url) {this.url = url;}

    public void setPrice(Float price) {this.price = price;}

    public void setMsrp(Float msrp) {this.msrp = msrp;}

    public void setPublisher(DPublisher publisher) {this.publisher = publisher;}

    public void setEditedGameName(String editedGameName) {this.editedGameName = editedGameName;}

    public void setEditedYearPublished(Integer editedYearPublished) {this.editedYearPublished = editedYearPublished;}

    public void setEditedMinPlayers(Integer editedMinPlayers) {this.editedMinPlayers = editedMinPlayers;}

    public void setEditedMaxPlayers(Integer editedMaxPlayers) {this.editedMaxPlayers = editedMaxPlayers;}

    public void setEditedMinPlayTime(Integer editedMinPlayTime) {this.editedMinPlayTime = editedMinPlayTime;}

    public void setEditedMaxPlayTime(Integer editedMaxPlayTime) {this.editedMaxPlayTime = editedMaxPlayTime;}

    public void setEditedMinAge(Integer editedMinAge) {this.editedMinAge = editedMinAge;}

    public void setEditedDescription(String editedDescription) {this.editedDescription = editedDescription;}

    public void setEditedThumbURL(String editedThumbURL) {this.editedThumbURL = editedThumbURL;}

    public void setEditedImageURL(String editedImageURL) {this.editedImageURL = editedImageURL;}

    public void setEditedPublisher(DPublisher editedPublisher) {this.editedPublisher = editedPublisher;}

    public void setLocation(String location) {this.location = location;}

    public void setExpansion(Boolean expansion) {this.expansion = expansion;}

    public void setFavorite(Boolean favorite) {this.favorite = favorite;}

    public void setIsUserCreated(Boolean isUserCreated) {this.isUserCreated = isUserCreated;}

    // Getters.
    public String getGameID() {return gameID;}

    public String getGameName() {return gameName;}

    public String getUrl() {return url;}

    public Float getPrice() {return price;}

    public Float getMsrp() {return msrp;}

    public Integer getYearPublished() {return yearPublished;}

    public Integer getMinPlayers() {return minPlayers;}

    public Integer getMaxPlayers() {return maxPlayers;}

    public Integer getMinPlayTime() {return minPlayTime;}

    public Integer getMaxPlayTime() {return maxPlayTime;}

    public Integer getMinAge() {return minAge;}

    public String getDescription() {return description;}

    public String getImageURL() {return imageURL;}

    public String getThumbURL() {return thumbURL;}

    public DPublisher getPublisher() {return publisher;}

    public String getEditedGameName() {return editedGameName;}

    public Integer getEditedYearPublished() {return editedYearPublished;}

    public Integer getEditedMinPlayers() {return editedMinPlayers;}

    public Integer getEditedMaxPlayers() {return editedMaxPlayers;}

    public Integer getEditedMinPlayTime() {return editedMinPlayTime;}

    public Integer getEditedMaxPlayTime() {return editedMaxPlayTime;}

    public Integer getEditedMinAge() {return editedMinAge;}

    public String getEditedDescription() {return editedDescription;}

    public String getEditedThumbURL() {return editedThumbURL;}

    public String getEditedImageURL() {return editedImageURL;}

    public DPublisher getEditedPublisher() {return editedPublisher;}

    public String getLocation() {return location;}

    public Boolean getExpansion() {return expansion;}

    public Boolean getFavorite() {return favorite;}

    public Boolean getIsUserCreated() {return isUserCreated;}

    //Getters that return edits over default api content...
    public String getVisibleGameName() {
        if (editedGameName != null && !editedGameName.equals("")) {
            return editedGameName;
        } else {
            return gameName;
        }
    }

    // Required for sorting. The toLowerCase() return ensures that case is ignored when sorting
    // the user list. If getVisibleGameName is used instead then comparator.comparing groups
    // uppercase letters together, then lowercase letters. Note: GROUPS not sorts...
    // Example of sort without using this function:
    // Game Name: Apples to Apples
    // Game Name: Zenolyth
    // Game Name: adam and eve
    public String compareVisibleGameName() {
        if (editedGameName != null && !editedGameName.equals("")) {
            return editedGameName.toLowerCase();
        } else {
            return gameName.toLowerCase();
        }
    }

    public Integer getVisibleYearPublished() {
        if(editedYearPublished > 0) {
            return editedYearPublished;
        } else {
            return yearPublished;
        }
    }

    public Integer getVisibleMinPlayers() {
        if(editedMinPlayers > -1) {
            return editedMinPlayers;
        } else {
            return minPlayers;
        }
    }

    public Integer getVisibleMaxPlayers() {
        if(editedMaxPlayers > -1) {
            return editedMaxPlayers;
        } else {
            return maxPlayers;
        }
    }

    public Integer getVisibleMinPlayTime() {
        if(editedMinPlayTime > -1) {
            return editedMinPlayTime;
        } else {
            return minPlayTime;
        }
    }

    public Integer getVisibleMaxPlayTime() {
        if (editedMaxPlayTime > -1) {
            return editedMaxPlayTime;
        } else {
            return maxPlayTime;
        }
    }

    public Integer getVisibleMinAge() {
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
        if (editedPublisher != null && editedPublisher.getName() != null && !editedPublisher.getName().equals("")) {
            return editedPublisher;
        } else {
            return publisher;
        }
    }

    public void resetUserValues() {
        if(!isUserCreated) {
            this.editedGameName = "";
            this.editedYearPublished = -1;
            this.editedMinPlayers = -1;
            this.editedMaxPlayers = -1;
            this.editedMinPlayTime = -1;
            this.editedMaxPlayTime = -1;
            this.editedMinAge = -1;
            this.editedDescription = "";
            this.editedThumbURL = "";
            this.editedImageURL = "";
            this.editedPublisher = new DPublisher();
        }
    }

    public void resetNullValues() {
        // API variables.
        if(this.gameID == null){
            this.gameID = "";
        }
        if(this.gameName == null){
            this.gameName = "";
        }
        if(this.yearPublished == null) {
            this.yearPublished = -1;
        }
        if(this.minPlayers == null) {
            this.minPlayers = -1;
        }
        if(this.maxPlayers == null) {
            this.maxPlayers = -1;
        }
        if(this.minPlayTime == null) {
            this.minPlayTime = -1;
        }
        if(this.maxPlayTime == null) {
            this.maxPlayTime = -1;
        }
        if(this.minAge == null) {
            this.minAge = -1;
        }
        if(this.description == null) {
            this.description = "";
        }
        if(this.imageURL == null) {
            this.imageURL = "";
        }
        if(this.thumbURL == null) {
            this.thumbURL = "";
        }
        if(this.url == null) {
            this.url = "";
        }
        if(this.price == null) {
            this.price = 0f;
        }
        if(this.msrp == null) {
            this.msrp = 0f;
        }
        if(this.publisher == null) {
            this.publisher = new DPublisher();
        }

        // Edited variables.
        if(this.editedGameName == null) {
            this.editedGameName = "";
        }
        if(this.editedYearPublished == null) {
            this.editedYearPublished = -1;
        }
        if(this.editedMinPlayers == null) {
            this.editedMinPlayers = -1;
        }
        if(this.editedMaxPlayers == null) {
            this.editedMaxPlayers = -1;
        }
        if(this.editedMinPlayTime == null) {
            this.editedMinPlayTime = -1;
        }
        if(this.editedMaxPlayTime == null) {
            this.editedMaxPlayTime = -1;
        }
        if(this.editedMinAge == null) {
            this.editedMinAge = -1;
        }
        if(this.editedDescription == null) {
            this.editedDescription = "";
        }
        if(this.editedThumbURL == null) {
            this.editedThumbURL = "";
        }
        if(this.editedImageURL == null) {
            this.editedImageURL = "";
        }
        if(this.editedPublisher == null) {
            this.editedPublisher = new DPublisher();
        }

        //General User Variables
        if(this.isUserCreated == null) {
            this.isUserCreated = false;
        }
        if(this.favorite == null) {
            this.favorite = false;
        }
        if(this.expansion == null) {
            this.expansion = false;
        }
        if(this.location == null) {
            this.location = "";
        }
    }
}
