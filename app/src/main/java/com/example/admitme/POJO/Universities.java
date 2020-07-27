package com.example.admitme.POJO;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Universities {

    private String mUniversityName;
    private String mUniversityLocation;
    private HashMap<String, String> industryPrograms = new HashMap<>();
    private ArrayList<String> programs;
   // private double mAcceptanceRate;
    //private UndergradMajor undergraduateMajor;

    public Universities(String universityName, String universityLocation, Set<String> keys){
        mUniversityLocation = universityLocation;
        mUniversityName = universityName;
        programs = new ArrayList<>(keys);
    }

    public String getUniversityName() {
        return mUniversityName;
    }

    public String getUniversityLocation() {
        return mUniversityLocation;
    }

    public void addIndustryProgram(String industryPrograms, String duration){
        this.industryPrograms.put(industryPrograms, duration);
    }

    public void addProgramInfo(String programs){
        this.programs.add(programs);
    }

    public void removeProgram(String programs){
        this.programs.remove(programs);
    }
    public void removeIndustryProgram(String industryPrograms){
        this.industryPrograms.remove(industryPrograms);
    }

    public HashMap<String, String> getIndustryPrograms(){
        return industryPrograms;
    }

    public ArrayList<String> getPrograms(){
        return programs;
    }


}
