package com.infs.catbreedfinder.models;

import com.google.gson.annotations.SerializedName;

public class Cat {

    @SerializedName("id")
    private String catID;

    @SerializedName("name")
    private String catBreed;

    private String description;
    private Weight weight;
    private String temperament;
    private String origin;
    @SerializedName("life_span")
    private String lifeSpan;
    @SerializedName("wikipedia_url")
    private String wikiLink;
    @SerializedName("dog_friendly")
    private String dogFriendLevel;

    public Cat(String catID, String catBreed, String description, Weight weight, String temperament,
               String origin, String lifeSpan, String wikiLink, String dogFriendLevel) {
        this.catID = catID;
        this.catBreed = catBreed;
        this.description = description;
        this.weight = weight;
        this.temperament = temperament;
        this.origin = origin;
        this.lifeSpan = lifeSpan;
        this.wikiLink = wikiLink;
        this.dogFriendLevel = dogFriendLevel;
    }

    public class Weight {
        String metric;

        public Weight(String metric) {
            this.metric = metric;
        }

        public String getMetric() {
            return metric;
        }
    }

    public String getCatID() {
        return catID;
    }

    public String getCatBreed() {
        return catBreed;
    }

    public String getDescription() {
        return description;
    }

    public Weight getWeight() {
        return weight;
    }

    public String getTemperament() {
        return temperament;
    }

    public String getOrigin() {
        return origin;
    }

    public String getLifeSpan() {
        return lifeSpan;
    }

    public String getWikiLink() {
        return wikiLink;
    }

    public String getDogFriendLevel() {
        return dogFriendLevel;
    }
}


