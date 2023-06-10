package com.niit.recommended.domain;

public class Trailer {

    String key;
    String site;

    String id;

    public Trailer(String key, String site, String id) {
        this.key = key;
        this.site = site;
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Trailer{" +
                "key='" + key + '\'' +
                ", site='" + site + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
