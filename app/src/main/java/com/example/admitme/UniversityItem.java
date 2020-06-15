package com.example.admitme;

public class UniversityItem {

    private String universityName;
    private String location;
    private int bookmarkResource;

    public UniversityItem(String universityName, String location, int bookmarkResource) {

        this.universityName = universityName;
        this.bookmarkResource = bookmarkResource;
        this.location = location;
    }

    public String getUniversityName() {

        return universityName;

    }

    public int getBookmarkResource() {

        return bookmarkResource;

    }

    public String getLocation() {

        return location;
    }
}
