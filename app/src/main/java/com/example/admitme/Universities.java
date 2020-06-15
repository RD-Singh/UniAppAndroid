package com.example.admitme;

public class Universities {

    private String mUniversityName;
    private String mUniversityLocation;
    private double mAcceptanceRate;
    private int bookmarkResource;
    private UndergradMajor undergraduateMajor;

    public Universities(String universityName, String universityLocation, double acceptanceRate, int bookmark){

        mUniversityLocation = universityLocation;
        mUniversityName = universityName;
        mAcceptanceRate = acceptanceRate;
        bookmarkResource = bookmark;
    }

    public String getUniversityName() {
        return mUniversityName;
    }

    public String getUniversityLocation() {
        return mUniversityLocation;
    }

    public double getAcceptanceRate() {
        return mAcceptanceRate;
    }

    public int getBookmarkResource() {
        return bookmarkResource;
    }

    public UndergradMajor getUndergraduateMajor() {
        return undergraduateMajor;
    }

    public class UndergradMajor {

        private String mMajor;
        private double mMinGPA;
        private double mTuition;
        private String mDescription;
        private int mMinGrade12Courses;
        private boolean mCOOP;
        private boolean mHonours;
        private String mDegreeType;

        public UndergradMajor(String major, String degreeType, String description, int minGrade12Courses,
                                  boolean COOP, boolean honours, double tuition, double minGPA) {
            mMajor = major;
            mMinGrade12Courses = minGrade12Courses;
            mCOOP = COOP;
            mHonours = honours;
            mDegreeType = degreeType;
            mDescription = description;
            mTuition = tuition;
            mMinGPA = minGPA;
        }

        public String getMajor() {
            return mMajor;
        }

        public double getMinGPA() {
            return mMinGPA;
        }

        public double getTuition() {
            return mTuition;
        }

        public String getDescription() {
            return mDescription;
        }

        public int getMinGrade12Courses() {
            return mMinGrade12Courses;
        }

        public boolean isCOOP() {
            return mCOOP;
        }

        public boolean isHonours() {
            return mHonours;
        }

        public String getDegreeType() {
            return mDegreeType;
        }
    }


}
