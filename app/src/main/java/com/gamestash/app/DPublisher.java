package com.gamestash.app;

public class DPublisher {
    private String name;
    private String url;

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