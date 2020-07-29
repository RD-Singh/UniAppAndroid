package com.example.admitme.Funnel;

import android.media.Image;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class UniversityItem {
    private boolean selected;
    private String uniName;
    private String uniLocation;
    private String uniIndustry;
    private HashMap<String, String> industryPrograms;
    private String uniProgram;

    public UniversityItem(String uniName, String uniLocation, String uniIndustry, String uniProgram, HashMap<String, String> industryPrograms, boolean selected){
        this.uniIndustry = uniIndustry;
        this.uniLocation = uniLocation;
        this.uniName = uniName;
        this.industryPrograms = industryPrograms;
        this.uniProgram = uniProgram;
        this.selected = selected;
    }

    public String getUniNameStr() {
        return uniName;
    }

    public String getUniProgram() {
        return uniProgram;
    }

    public String getUniLocationStr() {
        return uniLocation;
    }

    public String getUniIndustryStr() {
        return uniIndustry;
    }

    public HashMap<String, String> getIndustryPrograms() {
        return industryPrograms;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
