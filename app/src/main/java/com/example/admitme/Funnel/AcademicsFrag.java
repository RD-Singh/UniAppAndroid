package com.example.admitme.Funnel;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admitme.LoginRegister.SignUp;
import com.example.admitme.R;
import com.example.admitme.RIASEC.RiasecFrag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AcademicsFrag extends Fragment {

    private ArrayList<String> subjects = new ArrayList<>();
    private AutoCompleteTextView subject1, subject2, subject3, subject4, subject5, subject6, subject7, subject8;
    private EditText mark1, mark2, mark3, mark4, mark5, mark6, mark7, mark8;
    public static String englishMark, precalcMark, mark3Str, mark4Str, mark5Str, mark6Str, mark7Str, mark8Str;
    public static ArrayList<String> subjectIndustries = new ArrayList<>();
    int i = 0;
    public String firstSub, secondSub, thirdSub;
    double first, second, third;
    private HashMap<String, Double> marksAndSubjects = new HashMap<>();
    private Button submit, addSubjects, removeSubject;
    public static boolean check = false;
    public static double average;
    public static boolean requirement0, requirement1, requirement2, requirement3;
    public int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_academics, container, false);

        setupUI(v);
        addSubjects();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(),
                R.layout.custom_list_item, R.id.text_view_list_item, subjects);

        subject1.setAdapter(adapter);
        subject2.setAdapter(adapter);
        subject3.setAdapter(adapter);
        subject4.setAdapter(adapter);
        subject5.setAdapter(adapter);
        subject6.setAdapter(adapter);
        subject7.setAdapter(adapter);
        subject8.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkData();
            }
        });

        addSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubject();
            }
        });

        removeSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeSubject();
            }
        });

        return v;
    }

    private void addSubjects() {
        subjects.add(0, "Biology");
        subjects.add(1, "Chemistry");
        subjects.add(2, "Physics");
        subjects.add(3, "Calculus");
        subjects.add(4, "Pre-Calculus");
        subjects.add(5, "English");
        subjects.add(6, "French");
        subjects.add(7, "Spanish");
        subjects.add(8, "Music");
        subjects.add(9, "History");
        subjects.add(10, "Law");
        subjects.add(11, "Philosophy");
        subjects.add(12, "Political Science");
        subjects.add(13, "Physical Ed.");
        subjects.add(14, "Psychology");
        subjects.add(15, "Economics");
        subjects.add(16, "Entrepreneurship");
        subjects.add(17, "Financial Accounting");
        subjects.add(18, "Computer Programming");
        subjects.add(19, "Media and Photography");
        subjects.add(20, "Culinary Arts");
        subjects.add(21, "Food Studies");
        subjects.add(22, "Drafting");
        subjects.add(23, "Engineering");
        subjects.add(24, "Woodwork");
        subjects.add(25, "Metalwork");
        subjects.add(26, "Robotics");
        subjects.add(27, "Graphic Design");
        subjects.add(28, "Drama");
        subjects.add(29, "Film and Television");
        subjects.add(30, "Automotive Technology");
        subjects.add(31, "Art");
        subjects.add(32, "Textiles");
        subjects.add("Dance");
    }

    private void addSubject() {
        if (subject5.getVisibility() == View.GONE) {
            subject5.setVisibility(View.VISIBLE);
            mark5.setVisibility(View.VISIBLE);
            counter = 1;
        } else if (subject6.getVisibility() == View.GONE) {
            subject6.setVisibility(View.VISIBLE);
            mark6.setVisibility(View.VISIBLE);
            counter = 2;
        } else if (subject7.getVisibility() == View.GONE) {
            subject7.setVisibility(View.VISIBLE);
            mark7.setVisibility(View.VISIBLE);
            counter = 3;
        } else if (subject8.getVisibility() == View.GONE) {
            subject8.setVisibility(View.VISIBLE);
            mark8.setVisibility(View.VISIBLE);
            counter = 4;
        }
    }

    private void removeSubject() {
        if (subject8.getVisibility() == View.VISIBLE) {
            subject8.setText("");
            mark8.setText("");
            subject8.setVisibility(View.GONE);
            mark8.setVisibility(View.GONE);
            counter = 0;
        } else if (subject7.getVisibility() == View.VISIBLE) {
            subject7.setText("");
            mark7.setText("");
            subject7.setVisibility(View.GONE);
            mark7.setVisibility(View.GONE);
            counter = 1;
        } else if (subject6.getVisibility() == View.VISIBLE) {
            subject6.setText("");
            mark6.setText("");
            subject6.setVisibility(View.GONE);
            mark6.setVisibility(View.GONE);
            counter = 2;
        } else if (subject5.getVisibility() == View.VISIBLE) {
            subject5.setText("");
            mark5.setText("");
            subject5.setVisibility(View.GONE);
            mark5.setVisibility(View.GONE);
            counter = 3;
        }
    }

    public void subjectIndustries(String subject) {
        switch (subject) {
            case "biology":
                subjectIndustries.add("Science");
                subjectIndustries.add("Healthcare");
                subjectIndustries.add("Environmental Studies");
                subjectIndustries.add("Agriculture, Animals and Zoology");
                subjectIndustries.add("Biotechnology");
                subjectIndustries.add("Pharmacy");
                break;
            case "chemistry":
                subjectIndustries.add("Chemistry");
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Pharmacy");
                subjectIndustries.add("Biotechnology");
                break;
            case "physics":
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Computer, Software and Web Development");
                subjectIndustries.add("Architecture");
                subjectIndustries.add("Aviation");
                break;
            case "calculus":
            case "pre-Calculus":
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Data Science and Analytics");
                subjectIndustries.add("Computer, Software and Web Development");
                subjectIndustries.add("Architecture");
                subjectIndustries.add("Mathematics");
                subjectIndustries.add("Economics");
                subjectIndustries.add("Finance");
                subjectIndustries.add("Accounting");
                subjectIndustries.add("Aviation");
                break;
            case "english":
                subjectIndustries.add("Arts");
                subjectIndustries.add("Education");
                subjectIndustries.add("Humanities");
                subjectIndustries.add("Law and Criminal Justice");
                subjectIndustries.add("Writer");
                subjectIndustries.add("Reporting, News and Journalism");
                subjectIndustries.add("Anthropology and Archaeology");
                break;
            case "spanish":
            case "french":
                subjectIndustries.add("Language");
                subjectIndustries.add("Education");
                break;
            case "music":
                subjectIndustries.add("Music");
                subjectIndustries.add("Arts");
                break;
            case "history":
            case "political Science":
            case "philosophy":
                subjectIndustries.add("Arts");
                subjectIndustries.add("Humanities");
                subjectIndustries.add("Social Studies");
                subjectIndustries.add("Law and Criminal Justice");
                subjectIndustries.add("Politics");
                subjectIndustries.add("Anthropology and Archaeology");
                subjectIndustries.add("Immigration");
                break;
            case "law":
                subjectIndustries.add("Law and Criminal Justice");
                break;
            case "physical ed.":
                subjectIndustries.add("Education");
                subjectIndustries.add("Trades");
                break;
            case "psychology":
                subjectIndustries.add("Psychology");
                subjectIndustries.add("Healthcare");
                subjectIndustries.add("Therapeutics");
                subjectIndustries.add("Science");
                subjectIndustries.add("Social Studies");
                subjectIndustries.add("Public Relations and Human Resources");
                subjectIndustries.add("Anthropology and Archaeology");
                subjectIndustries.add("Immigration");
                break;
            case "economics":
                subjectIndustries.add("Economics");
                subjectIndustries.add("Finance");
                subjectIndustries.add("Accounting");
                subjectIndustries.add("Business");
                subjectIndustries.add("Data Science and Analytics");
                break;
            case "entrepreneurship":
                subjectIndustries.add("Business");
                subjectIndustries.add("Tourism and Hospitality");
                subjectIndustries.add("Sales and Real Estate");
                subjectIndustries.add("Housing and Community Services");
                subjectIndustries.add("Public Relations and Human Resources");
                break;
            case "financial accounting":
                subjectIndustries.add("Finance");
                subjectIndustries.add("Accounting");
                subjectIndustries.add("Business");
                break;
            case "computer programming":
                subjectIndustries.add("Computers, Software and Web Development");
                subjectIndustries.add("Data Science and Analytics");
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Web and Graphic Design");
                subjectIndustries.add("Information Technology and Studies");
                break;
            case "media and photography":
            case "drama":
            case "film and television":
                subjectIndustries.add("Film, Photography, and Media");
                subjectIndustries.add("Arts");
                subjectIndustries.add("Drama and Professional Acting");
                subjectIndustries.add("Reporting, News and Journalism");
                break;
            case "food studies":
                subjectIndustries.add("Agriculture, Animals and Zoology");
                subjectIndustries.add("Environmental Studies");
                break;
            case "drafting":
                subjectIndustries.add("Drafting and Design");
                subjectIndustries.add("Web and Graphic Design");
                subjectIndustries.add("Architecture");
                subjectIndustries.add("Engineering");
                break;
            case "engineering":
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Computers, Software and Web Development");
                subjectIndustries.add("Drafting and Design");
                subjectIndustries.add("Web and Graphic Design");
                subjectIndustries.add("Biotechnology");
                break;
            case "woodwork":
            case "metalwork":
            case "automotive Technology":
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Trades");
                break;
            case "robotics":
                subjectIndustries.add("Engineering");
                subjectIndustries.add("Computers, Software and Development");
                subjectIndustries.add("Drafting and Design");
                break;
            case "graphic Design":
                subjectIndustries.add("Web and Graphic Design");
                subjectIndustries.add("Drafting and Design");
                subjectIndustries.add("Arts");
                break;
            case "art":
                subjectIndustries.add("Arts");
                subjectIndustries.add("Web and Graphic Design");
                subjectIndustries.add("Music");
                subjectIndustries.add("Humanities");
                break;
            case "textiles":
                subjectIndustries.add("Arts");
                subjectIndustries.add("Fashion Design");
                break;
            case "dance":
                subjectIndustries.add("Dancing and Choreography");
                subjectIndustries.add("Arts");
                subjectIndustries.add("Drama and Professional Acting");
                subjectIndustries.add("Film, Photography and Media");
                break;
        }
    }

    private boolean checkIncorrect(AutoCompleteTextView textView) {
        for (String subject : subjects) {
            if (textView.getText().toString().equals(subject)) {
                return true;
            }
        }
        return false;
    }

    public double average(){
        double sum = 0;
        for(double value : marksAndSubjects.values()){
            sum+=value;
        }
        return sum/(marksAndSubjects.size() + 1);
    }

    private boolean checkEmpty(EditText textView) {
        return textView.getText().toString().isEmpty();
    }

    private boolean checkOptionalSubjects() {
        if (counter == 1) {
            if (checkEmpty(subject5) || checkEmpty(mark5)) {
                Toast.makeText(AcademicsFrag.this.getContext(), "Please make sure to fill out all empty added subjects and grades", Toast.LENGTH_SHORT).show();
                System.out.println("Working till here");
                return true;
            } else if(!checkIncorrect(subject5)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Invalid Subject Selection", Toast.LENGTH_SHORT).show();
                return true;
            }else if(checkMark(mark5)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Please enter a valid grade", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                mark5Str = mark5.getText().toString();
                marksAndSubjects.put(subject5.getText().toString().trim().toLowerCase(), Double.parseDouble(mark5Str));
            }
        } else if (counter == 2) {
            if (checkEmpty(subject5) || checkEmpty(subject6) || checkEmpty(mark5) || checkEmpty(mark6)) {
                Toast.makeText(AcademicsFrag.this.getContext(), "Please make sure to fill out all empty added subjects and grades", Toast.LENGTH_SHORT).show();
                return true;
            } else if(!checkIncorrect(subject5) || !checkIncorrect(subject6)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Invalid Subject Selection", Toast.LENGTH_SHORT).show();
                return true;
            }else if(checkMark(mark5) || checkMark(mark6)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Please enter a valid grade", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                mark5Str = mark5.getText().toString();
                mark6Str = mark6.getText().toString();
                marksAndSubjects.put(subject5.getText().toString().trim().toLowerCase(), Double.parseDouble(mark5Str));
                marksAndSubjects.put(subject6.getText().toString().trim().toLowerCase(), Double.parseDouble(mark6Str));
            }
        } else if (counter == 3) {
            if (checkEmpty(subject5) || checkEmpty(subject6) || checkEmpty(subject7) || checkEmpty(mark5) || checkEmpty(mark6) || checkEmpty(mark7)) {
                Toast.makeText(AcademicsFrag.this.getContext(), "Please make sure to fill out all empty added subjects and grades", Toast.LENGTH_SHORT).show();
                return true;
            } else if(!checkIncorrect(subject5) || !checkIncorrect(subject6) || !checkIncorrect(subject7)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Invalid Subject Selection", Toast.LENGTH_SHORT).show();
                return true;
            }else if(checkMark(mark5) || checkMark(mark6) || checkMark(mark7)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Please enter a valid grade", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                mark5Str = mark5.getText().toString();
                mark6Str = mark6.getText().toString();
                mark7Str = mark7.getText().toString();
                marksAndSubjects.put(subject5.getText().toString().trim().toLowerCase(), Double.parseDouble(mark5Str));
                marksAndSubjects.put(subject6.getText().toString().trim().toLowerCase(), Double.parseDouble(mark6Str));
                marksAndSubjects.put(subject7.getText().toString().trim().toLowerCase(), Double.parseDouble(mark7Str));
            }
        } else if (counter == 4) {
            if (checkEmpty(subject5) || checkEmpty(subject6) || checkEmpty(subject7) || checkEmpty(subject8) || checkEmpty(mark5) || checkEmpty(mark6) || checkEmpty(mark7) || checkEmpty(mark8)) {
                Toast.makeText(AcademicsFrag.this.getContext(), "Please make sure to fill out all empty added subjects and grades", Toast.LENGTH_SHORT).show();
                return true;
            } else if(!checkIncorrect(subject5) || !checkIncorrect(subject6) || !checkIncorrect(subject7) || !checkIncorrect(subject8)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Invalid Subject Selection", Toast.LENGTH_SHORT).show();
                return true;
            }else if(checkMark(mark5) || checkMark(mark6) || checkMark(mark7) || checkMark(mark8)){
                Toast.makeText(AcademicsFrag.this.getContext(), "Please enter a valid grade", Toast.LENGTH_SHORT).show();
                return true;
            } else {
                mark5Str = mark5.getText().toString();
                mark6Str = mark6.getText().toString();
                mark7Str = mark7.getText().toString();
                mark8Str = mark8.getText().toString();
                marksAndSubjects.put(subject5.getText().toString().trim().toLowerCase(), Double.parseDouble(mark5Str));
                marksAndSubjects.put(subject6.getText().toString().trim().toLowerCase(), Double.parseDouble(mark6Str));
                marksAndSubjects.put(subject7.getText().toString().trim().toLowerCase(), Double.parseDouble(mark7Str));
                marksAndSubjects.put(subject8.getText().toString().trim().toLowerCase(), Double.parseDouble(mark8Str));
            }
        }
        return false;
    }

    private void checkData() {
        if (checkEmpty(subject1) || checkEmpty(subject2) || checkEmpty(subject3) || checkEmpty(subject4)) {
            Toast.makeText(AcademicsFrag.this.getContext(), "Please make sure to fill out all empty subject fields", Toast.LENGTH_SHORT).show();
        } else if (!checkIncorrect(subject1) || !checkIncorrect(subject2) || !checkIncorrect(subject3) || !checkIncorrect(subject4)) {
            Toast.makeText(AcademicsFrag.this.getContext(), "Invalid subject selection", Toast.LENGTH_SHORT).show();
        } else if (checkEmpty(mark1) || checkEmpty(mark2) || checkEmpty(mark3) || checkEmpty(mark4)) {
            Toast.makeText(AcademicsFrag.this.getContext(), "Please make sure to fill out all empty mark fields", Toast.LENGTH_SHORT).show();
        } else if (checkMark(mark1) || checkMark(mark2) || checkMark(mark3) || checkMark(mark4)) {
            Toast.makeText(AcademicsFrag.this.getContext(), "Please enter a valid grade.", Toast.LENGTH_SHORT).show();
        } else if (checkOptionalSubjects()) {

        } else {
            setupStr();
            Object[] a = marksAndSubjects.entrySet().toArray();
            Arrays.sort(a, new Comparator() {
                public int compare(Object o1, Object o2) {
                    return ((Map.Entry<String, Double>) o2).getValue()
                            .compareTo(((Map.Entry<String, Double>) o1).getValue());
                }
            });
            for (Object e : a) {
                if (i == 0) {
                    firstSub = ((Map.Entry<String, Double>) e).getKey();
                    first = ((Map.Entry<String, Double>) e).getValue();
                    i++;
                } else if (i == 1) {
                    secondSub = ((Map.Entry<String, Double>) e).getKey();
                    second = ((Map.Entry<String, Double>) e).getValue();
                    i++;
                } else if (i == 2) {
                    thirdSub = ((Map.Entry<String, Double>) e).getKey();
                    third = ((Map.Entry<String, Double>) e).getValue();
                    i++;
                }
            }
            subjectIndustries(firstSub);
            subjectIndustries(secondSub);
            Set<String> clearDuplicates = new HashSet<>(subjectIndustries);
            subjectIndustries.clear();
            subjectIndustries.addAll(clearDuplicates);
            System.out.println(subjectIndustries);
            average = average();
            goToFunnelPref();
        }
    }

    private void requirement1(double englishMark){
        if(englishMark > 55.0){
            requirement1 = true;
        } else {
            requirement0 = true;
            requirement1 = false;
        }
    }

    private void requirement2(double mathMark){
        if(requirement1){
            if(mathMark > 55.0){
                requirement2 = true;
            }
        }
    }

    private void goToFunnelPref(){
        FunnelPreferencesFrag funnelPreferencesFrag = new FunnelPreferencesFrag();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.funnel_container, funnelPreferencesFrag);
        fragmentTransaction.commit();
    }

    private boolean checkMark(EditText mark) {
        if (Double.parseDouble(mark.getText().toString()) > 110) {
            check = true;
        } else {
            check = false;
        }

        return check;
    }

    private void setupStr() {
        englishMark = mark1.getText().toString();
        precalcMark = mark2.getText().toString();
        mark3Str = mark3.getText().toString();
        mark4Str = mark4.getText().toString();
        marksAndSubjects.put(subject1.getText().toString().trim().toLowerCase(), Double.parseDouble(englishMark));
        marksAndSubjects.put(subject2.getText().toString().trim().toLowerCase(), Double.parseDouble(precalcMark));
        marksAndSubjects.put(subject3.getText().toString().trim().toLowerCase(), Double.parseDouble(mark3Str));
        marksAndSubjects.put(subject4.getText().toString().trim().toLowerCase(), Double.parseDouble(mark4Str));
    }

    private void setupUI(View v) {
        subject1 = v.findViewById(R.id.subject1);
        mark1 = v.findViewById(R.id.mark1);
        subject2 = v.findViewById(R.id.subject2);
        mark2 = v.findViewById(R.id.mark2);
        subject3 = v.findViewById(R.id.subject3);
        mark3 = v.findViewById(R.id.mark3);
        subject4 = v.findViewById(R.id.subject4);
        mark4 = v.findViewById(R.id.mark4);
        subject5 = v.findViewById(R.id.subject5);
        mark5 = v.findViewById(R.id.mark5);
        subject6 = v.findViewById(R.id.subject6);
        mark6 = v.findViewById(R.id.mark6);
        subject7 = v.findViewById(R.id.subject7);
        mark7 = v.findViewById(R.id.mark7);
        subject8 = v.findViewById(R.id.subject8);
        mark8 = v.findViewById(R.id.mark8);

        submit = v.findViewById(R.id.submit_academics);
        addSubjects = v.findViewById(R.id.addSubject);
        removeSubject = v.findViewById(R.id.removeSubject);
    }
}