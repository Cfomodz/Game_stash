package com.gamestash.app;

/**
 * <h1>DPublisher</h1>
 * This class is used in the creation of a new game obj. It sets up the publisher.
 * It is separate because the board game atlas api returns an object.
 */
public class DPublisher {
    private String name = "";
    private String url = "";

    public DPublisher() {}

    public DPublisher(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
