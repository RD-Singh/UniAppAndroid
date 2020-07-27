package com.example.admitme.POJO;

public class UndergradMajor {

    public String major;
    public String durationStr;
    public int duration;
    public String degreeType;

    public UndergradMajor(String major, String degreeType, int duration, String durationStr) {
        this.major = major;
        this.degreeType = degreeType;
        this.duration = duration;
        this.durationStr = durationStr;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDurationStr() {
        return durationStr;
    }

    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }
}
