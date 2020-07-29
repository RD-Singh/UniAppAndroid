package com.example.admitme.RIASEC;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admitme.Funnel.AcademicsFrag;
import com.example.admitme.Funnel.FunnelPreferencesFrag;
import com.example.admitme.Funnel.UnisFrag;
import com.example.admitme.LoginRegister.LoginFrag;
import com.example.admitme.POJO.Universities;
import com.example.admitme.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.iterable.FindIterable;
import io.realm.mongodb.mongo.iterable.MongoCursor;

public class RiasecFrag extends Fragment {

    public static HashMap<String, Integer> riasec = new HashMap<>();
    private TextView result;
    private Button nextUni;
    public static int first, second, third, fourth, fifth, sixth;
    private String firstStr, secondStr, thirdStr, fourthStr, fifthStr, sixthStr;
    char f, s, t;
    public ArrayList<String> riasecCode;
    int i = 0;
    public ArrayList<Integer> indeces = new ArrayList<>();
    public static ArrayList<String> industries = new ArrayList<>();

    List<String> code = new ArrayList<>();
    List<String> industry = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_riasec, container, false);

        setupUI(v);
        riasecCode = new ArrayList<>();

        riasec.put("Realistic", Realistic.realisticCount);
        riasec.put("Investigative", Investigative.investigateCount);
        riasec.put("Artistic", Artistic.artisticCount);
        riasec.put("Social", Social.socialCount);
        riasec.put("Enterprising", Enterprising.enterprisingCount);
        riasec.put("Conventional", Conventional.conventionalCount);
        System.out.println(RiasecFrag.industries);

        Object[] a = riasec.entrySet().toArray();
        Arrays.sort(a, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Map.Entry<String, Integer>) o2).getValue()
                        .compareTo(((Map.Entry<String, Integer>) o1).getValue());
            }
        });
        for (Object e : a) {
            if (i == 0) {
                firstStr = ((Map.Entry<String, Integer>) e).getKey();
                first = ((Map.Entry<String, Integer>) e).getValue();
                i++;
            } else if (i == 1) {
                secondStr = ((Map.Entry<String, Integer>) e).getKey();
                second = ((Map.Entry<String, Integer>) e).getValue();
                i++;
            } else if (i == 2) {
                thirdStr = ((Map.Entry<String, Integer>) e).getKey();
                third = ((Map.Entry<String, Integer>) e).getValue();
                i++;
            } else if (i == 3) {
                fourthStr = ((Map.Entry<String, Integer>) e).getKey();
                fourth = ((Map.Entry<String, Integer>) e).getValue();
                i++;
            } else if (i == 4) {
                fifthStr = ((Map.Entry<String, Integer>) e).getKey();
                fifth = ((Map.Entry<String, Integer>) e).getValue();
                i++;
            } else if (i == 5) {
                sixthStr = ((Map.Entry<String, Integer>) e).getKey();
                sixth = ((Map.Entry<String, Integer>) e).getValue();
                i = 0;
            }
        }

        allRiasecCodes();
        allMatchedIndustries();

        checkIndustries();
        result.setText(industries.toString().replace("[", "").replace("]", ""));

        nextUni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToUnis();
            }
        });

        return v;
    }

    private void goToUnis(){
        UnisFrag unisFrag = new UnisFrag();
        FragmentTransaction fragmentTransaction = getParentFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.funnel_container, unisFrag);
        fragmentTransaction.commit();
    }

    public void checkIndustries() {
        if (industries.size() > 4) {
            Iterator<String> iterator = industries.iterator();

            while (iterator.hasNext()) {
                String industry = iterator.next();

                if (!AcademicsFrag.subjectIndustries.contains(industry))
                    iterator.remove();
            }
        }
    }

    private void allRiasecCodes() {
        f = firstStr.charAt(0);
        s = secondStr.charAt(0);
        t = thirdStr.charAt(0);
        riasecCode.add((String.valueOf(f) + s + t));
        riasecCode.add((String.valueOf(f) + t + s));

        if (second == third) {
            s = thirdStr.charAt(0);
            t = fourthStr.charAt(0);
            riasecCode.add((String.valueOf(f) + s + t));
            riasecCode.add((String.valueOf(f) + t + s));

            s = secondStr.charAt(0);
            t = fourthStr.charAt(0);
            riasecCode.add((String.valueOf(f) + s + t));
            riasecCode.add((String.valueOf(f) + t + s));
            if (second == fourth) {
                s = fourthStr.charAt(0);
                t = fifthStr.charAt(0);
                riasecCode.add((String.valueOf(f) + s + t));
                riasecCode.add((String.valueOf(f) + t + s));
            }
            if (fifth == sixth) {
                s = fourthStr.charAt(0);
                t = sixthStr.charAt(0);
                riasecCode.add((String.valueOf(f) + s + t));
                riasecCode.add((String.valueOf(f) + t + s));
            }
            if (second == fifth) {
                s = fifthStr.charAt(0);
                t = sixthStr.charAt(0);
                riasecCode.add((String.valueOf(f) + s + t));
                riasecCode.add((String.valueOf(f) + t + s));
            }
            if (fourth == fifth) {
                s = thirdStr.charAt(0);
                t = fifthStr.charAt(0);
                riasecCode.add((String.valueOf(f) + s + t));
                riasecCode.add((String.valueOf(f) + t + s));
            }
            if (fourth == sixth) {
                s = thirdStr.charAt(0);
                t = sixthStr.charAt(0);
                riasecCode.add((String.valueOf(f) + s + t));
                riasecCode.add((String.valueOf(f) + t + s));
            }
        }
        if (third == fourth) {
            s = secondStr.charAt(0);
            t = fourthStr.charAt(0);
            riasecCode.add((String.valueOf(f) + s + t));
            riasecCode.add((String.valueOf(f) + t + s));
        }
        if (third == fifth) {
            s = secondStr.charAt(0);
            t = fifthStr.charAt(0);
            riasecCode.add((String.valueOf(f) + t + s));
            riasecCode.add((String.valueOf(f) + s + t));
        }
        if (third == sixth) {
            s = secondStr.charAt(0);
            t = sixthStr.charAt(0);
            riasecCode.add((String.valueOf(f) + s + t));
            riasecCode.add((String.valueOf(f) + t + s));
        }
    }

    private void getKey(ArrayList<String> value) {
        for (int i = 0; i < code.size(); i++) {
            for (int j = 0; j < value.size(); j++) {
                if (code.get(i).equals(value.get(j))) {
                    indeces.add(i);
                }
            }
        }
        for (int k = 0; k < indeces.size(); k++) {
            industries.add(industry.get(indeces.get(k)));
        }
    }

    private void allMatchedIndustries() {
        switch (firstStr) {
            case "Realistic":
                addRealistic();
                getKey(riasecCode);
                break;
            case "Investigative":
                addInvestigative();
                getKey(riasecCode);
                break;
            case "Artistic":
                addArtistic();
                getKey(riasecCode);
                break;
            case "Social":
                addSocial();
                getKey(riasecCode);
                break;
            case "Enterprising":
                addEnterprising();
                getKey(riasecCode);
                break;
            case "Conventional":
                addConventional();
                getKey(riasecCode);
                break;
        }
    }

    private void addRealistic() {
        code.add(0, "RAS");
        industry.add(0, "Arts");
        code.add(1, "RAE");
        industry.add(1, "Music");
        code.add(2, "RSI");
        industry.add(2, "Social Studies");
        code.add(3, "RIA");
        industry.add(3, "Science");
        code.add(4, "RIC");
        industry.add(4, "Engineering");
        code.add(5, "RCI");
        industry.add(5, "Computers, Software and Web Development");
        code.add(6, "RSA");
        industry.add(6, "Environmental Studies");
        code.add(7, "RSC");
        industry.add(7, "Healthcare");
        code.add(8, "REA");
        industry.add(8, "Film, Photography, and Media");
        code.add(9, "RIC");
        industry.add(9, "Chemistry");
        code.add(10, "RAE");
        industry.add(10, "Architecture");
        code.add(11, "RAE");
        industry.add(11, "Drafting and Design");
        code.add(12, "RIE");
        industry.add(12, "Agriculture, Animals and Zoology");
        code.add(13, "RAE");
        industry.add(13, "Fashion Design");
        code.add(14, "RIC");
        industry.add(14, "Trades");
        code.add(15, "RIC");
        industry.add(15, "Aviation");
        code.add(16, "RIA");
        industry.add(16, "Anthropology and Archaeology");
        code.add(17, "RSE");
        industry.add(17, "Cosmetics");
        code.add(18, "RCE");
        industry.add(18, "Sales and Real Estate");
        //TODO Add Immigration to this list
        //TODO Add Language to this list
    }

    private void addInvestigative() {
        code.add(0, "ICE");
        industry.add(0, "Business");
        code.add(1, "ISC");
        industry.add(1, "Education");
        code.add(2, "IAS");
        industry.add(2, "Humanities");
        code.add(3, "IRA");
        industry.add(3, "Science");
        code.add(4, "IRC");
        industry.add(4, "Engineering");
        code.add(5, "IRC");
        industry.add(5, "Computers, Software and Web Development");
        code.add(6, "IES");
        industry.add(6, "Law and Criminal Justice");
        code.add(7, "IRS");
        industry.add(7, "Healthcare");
        code.add(8, "IRC");
        industry.add(8, "Chemistry");
        code.add(9, "ICS");
        industry.add(9, "Economics");
        code.add(10, "IAC");
        industry.add(10, "Mathematics");
        code.add(11, "ISE");
        industry.add(11, "Tourism and Hospitality");
        code.add(12, "ICR");
        industry.add(12, "Biotechnology");
        code.add(13, "ISC");
        industry.add(13, "Pharmacy");
        code.add(14, "IEC");
        industry.add(14, "Finance");
        code.add(15, "IRS");
        industry.add(15, "Agriculture, Animals and Zoology");
        code.add(16, "ISA");
        industry.add(16, "Psychology");
        code.add(17, "IEA");
        industry.add(17, "Therapeutics");
        code.add(18, "ICR");
        industry.add(18, "Trades");
        code.add(19, "IRE");
        industry.add(19, "Environmental Studies");
        code.add(20, "IRE");
        industry.add(20, "Sales and Real Estate");
        code.add(21, "ISE");
        industry.add(21, "Housing and Community Services");
    }

    private void addArtistic() {
        code.add(0, "ARS");
        industry.add(0, "Arts");
        code.add(1, "AER");
        industry.add(1, "Music");
        code.add(2, "ARC");
        industry.add(2, "Computers, Software and Web Development");
        code.add(3, "AIS");
        industry.add(3, "Humanities");
        code.add(4, "ASI");
        industry.add(4, "Psychology");
        code.add(5, "ASR");
        industry.add(5, "Tourism and Hospitality");
        code.add(6, "AES");
        industry.add(6, "Environmental Studies");
        code.add(7, "ARE");
        industry.add(7, "Architecture");
        code.add(8, "AER");
        industry.add(8, "Drafting and Design");
        code.add(9, "AER");
        industry.add(9, "Fashion Design");
        code.add(10, "AIR");
        industry.add(10, "Biotechnology");
        code.add(11, "ASE");
        industry.add(11, "Drama and Professional Acting");
        code.add(12, "AIC");
        industry.add(12, "Writer");
        code.add(13, "AIE");
        industry.add(13, "Reporter, News and Journalism");
        code.add(14, "ASE");
        industry.add(14, "Public Relations and Human Resources");
        code.add(15, "AIS");
        industry.add(15, "Politics");
        code.add(16, "ARS");
        industry.add(16, "Dancer or Choreographer");
        code.add(17, "ARI");
        industry.add(17, "Anthropology and Archaeology");
        code.add(18, "AEC");
        industry.add(18, "Sales and Real Estate");
        code.add(19, "ASC");
        industry.add(19, "Cosmetics");
        code.add(20, "ASR");
        industry.add(20, "Housing and Community Services");

    }

    private void addSocial() {
        code.add(0, "SIA");
        industry.add(0, "Social Studies");
        code.add(1, "SCA");
        industry.add(1, "Education");
        code.add(2, "SAI");
        industry.add(2, "Humanities");
        code.add(3, "SIE");
        industry.add(3, "Law and Criminal Justice");
        code.add(4, "SIA");
        industry.add(4, "Psychology");
        code.add(5, "SRA");
        industry.add(5, "Environmental Studies");
        code.add(6, "SCR");
        industry.add(6, "Healthcare");
        code.add(7, "SIC");
        industry.add(7, "Economics");
        code.add(8, "SIE");
        industry.add(8, "Tourism and Hospitality");
        code.add(9, "SIC");
        industry.add(9, "Pharmacy");
        code.add(10, "SEC");
        industry.add(10, "Cosmetics");
        code.add(11, "SEA");
        industry.add(11, "Public Relations and Human Resources");//
        code.add(12, "SRI");
        industry.add(12, "Agriculture, Animals and Zoology");
        code.add(13, "SIE");
        industry.add(13, "Therapeutics");
        code.add(14, "SRE");
        industry.add(14, "Politics");
        code.add(15, "SIE");
        industry.add(15, "Housing and Community Services");
    }

    private void addEnterprising() {
        code.add(0, "EIC");
        industry.add(0, "Business");
        code.add(1, "ESC");
        industry.add(1, "Education");
        code.add(2, "EIS");
        industry.add(2, "Law and Criminal Justice");
        code.add(3, "ECI");
        industry.add(3, "Finance");
        code.add(4, "ECS");
        industry.add(4, "Economics");
        code.add(5, "EIS");
        industry.add(5, "Tourism and Hospitality");
        code.add(6, "EAR");
        industry.add(6, "Film, Photography, and Media");
        code.add(7, "EAI");
        industry.add(7, "Architecture");
        code.add(8, "EAR");
        industry.add(8, "Drafting and Design");
        code.add(9, "EIC");
        industry.add(9, "Data Science and Analytics");
        code.add(10, "EAR");
        industry.add(10, "Fashion Design");
        code.add(11, "ECA");
        industry.add(11, "Sales and Real Estate");
        code.add(12, "ECI");
        industry.add(12, "Accounting");
        code.add(13, "EIR");
        industry.add(13, "Engineering");
        code.add(14, "ESA");
        industry.add(14, "Cosmetics");
        code.add(15, "ERI");
        industry.add(15, "Environmental Studies");
        code.add(16, "EIC");
        industry.add(16, "Information Technology and Studies");
        code.add(17, "ERC");
        industry.add(17, "Trades");
        code.add(18, "ERA");
        industry.add(18, "Arts");
        code.add(19, "ECS");
        industry.add(19, "Finance");
        code.add(20, "EAC");
        industry.add(20, "Web and Graphic Design");
        code.add(21, "ECI");
        industry.add(21, "Data Science and Analytics");
        code.add(22, "EAS");
        industry.add(22, "Public Relations and Human Resources");
        code.add(23, "EAI");
        industry.add(23, "Reporter, News and Journalism");
        code.add(24, "EIS");
        industry.add(24, "Housing and Community Services");
    }

    private void addConventional() {
        code.add(0, "CIE");
        industry.add(0, "Business");
        code.add(1, "CIR");
        industry.add(1, "Engineering");
        code.add(2, "CEI");
        industry.add(2, "Accounting");
        code.add(3, "CIR");
        industry.add(3, "Computers, Software and Web Development");
        code.add(4, "CSA");
        industry.add(4, "Education");
        code.add(5, "CEI");
        industry.add(5, "Finance");
        code.add(6, "CRS");
        industry.add(6, "Healthcare");
        code.add(7, "CIS");
        industry.add(7, "Economics");
        code.add(8, "CIR");
        industry.add(8, "Chemistry");
        code.add(9, "CIR");
        industry.add(9, "Biotechnology");
        code.add(10, "CSI");
        industry.add(10, "Pharmacy");
        code.add(11, "CRI");
        industry.add(11, "Data Science and Analytics");
        code.add(12, "CRA");
        industry.add(12, "Trades");
        code.add(13, "CEA");
        industry.add(13, "Sales and Real Estate");
        code.add(14, "CIR");
        industry.add(14, "Agriculture, Animals and Zoology");
        code.add(15, "CRE");
        industry.add(15, "Aviation");
        code.add(16, "CES");
        industry.add(16, "Public Relations and Human Resources");
        code.add(17, "CIA");
        industry.add(17, "Mathematician");
        code.add(18, "CES");
        industry.add(18, "Immigration");
    }

    private void setupUI(View v) {
        result = v.findViewById(R.id.top_result);
        nextUni = v.findViewById(R.id.nextUni);
    }
}