package com.example.admitme.POJO;

import com.example.admitme.Funnel.UnisFrag;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Universities {

    private String mUniversityName;
    private String mUniversityLocation;
    private HashMap<String, String> industryPrograms = new HashMap<>();
    private ArrayList<String> programs;

    private String mUniversityIndustry;

    public static ArrayList<Universities> universities = new ArrayList<>();

    // private double mAcceptanceRate;
    //private UndergradMajor undergraduateMajor;
    public Universities(String universityName, String universityLocation, Set<String> keys, String universityIndustry){
        mUniversityLocation = universityLocation;
        mUniversityName = universityName;
        programs = new ArrayList<>(keys);
        mUniversityIndustry = universityIndustry;
    }

    public String getUniversityName() {
        return mUniversityName;
    }

    public String getUniversityIndustry() {
        return mUniversityIndustry;
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

    public static ArrayList<Universities> getUniversities() {
        return universities;
    }

    public static void addUniversities(Universities uni) {
        universities.add(uni);
    }
}
