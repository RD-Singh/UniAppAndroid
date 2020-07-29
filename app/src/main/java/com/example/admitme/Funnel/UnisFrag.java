package com.example.admitme.Funnel;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.admitme.LoginRegister.LoginFrag;
import com.example.admitme.Main.HomeFrag;
import com.example.admitme.Main.StartupActivity;
import com.example.admitme.POJO.Accounts;
import com.example.admitme.POJO.Universities;
import com.example.admitme.R;
import com.example.admitme.RIASEC.RiasecFrag;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.bson.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.realm.mongodb.App;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.iterable.FindIterable;
import io.realm.mongodb.mongo.iterable.MongoCursor;
import io.realm.mongodb.mongo.result.UpdateResult;

public class UnisFrag extends Fragment {
    private static final int ONE_REQ = 1;
    private static final int TWO_REQ = 2;
    private static final int THREE_REQ = 3;
    private static final int FOUR_REQ = 4;

    public RecyclerView mRecyclerView;
    public UniversityAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext());
    public String progType;
    private MongoCollection<Document> uniCollection, acctCollection;
    public static Universities university;
    public static ArrayList<String> resultStr = new ArrayList<>();
    public static boolean isProg = false;
    ArrayList<UniversityItem> universityList = new ArrayList<>();

    private Button continueHome;
    public ImageView star;

    public interface UniversityCallback{
        void onCallBack(ArrayList<Universities> unis);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_unis, container, false);

        setupUI(v);
        System.out.println(LoginFrag.app.currentUser().getId());

        final MongoClient client = LoginFrag.app.currentUser().getMongoClient("mongodb-atlas");
        uniCollection = client.getDatabase("AdmitU").getCollection("Universities");
        uniSearch(new UniversityCallback() {
            @Override
            public void onCallBack(ArrayList<Universities> unis) {
                universityList.clear();

                for(Universities uni : unis){
                    ArrayList<String> keys = new ArrayList<>(uni.getIndustryPrograms().keySet());

                    for(String key : keys){
                        universityList.add(new UniversityItem(uni.getUniversityName(), uni.getUniversityLocation(), uni.getUniversityIndustry(), key, uni.getIndustryPrograms(), false));
                        System.out.println("Uni industry in for loop: " + uni.getUniversityIndustry() + ", Uni location in for loop " + uni.getUniversityLocation());
                    }
                }
                Set<UniversityItem> clearDuplicates = new HashSet<>(universityList);
                universityList.clear();
                universityList.addAll(clearDuplicates);

                System.out.println("Print unis " + universityList);
                mAdapter = new UniversityAdapter(universityList);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setAdapter(mAdapter);

                mAdapter.setOnItemClickListener(new UniversityAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, int position) {
                        UniversityAdapter.UniversityViewHolder.item = universityList.get(position);
                        if(UniversityAdapter.UniversityViewHolder.item.isSelected()){
                            HomeFrag.uniList.remove(universityList.get(position));
                            universityList.get(position).setSelected(false);
                            System.out.println("Remove");
                        } else {
                            HomeFrag.uniList.add(universityList.get(position));
                            universityList.get(position).setSelected(true);
                            System.out.println("Add");
                        }
                    }
                });
            }
        });
//TODO fix the issue with requirements for math
        continueHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acctCollection = client.getDatabase("AdmitU").getCollection("Accounts");
                        Document updateDoc = new Document("user_id", LoginFrag.app.currentUser().getId()).append("email", LoginFrag.account.email)
                                .append("password", LoginFrag.account.password).append("full_name", LoginFrag.account.fullName).append("first_time_login", LoginFrag.account.firstTimeLogin)

                                .append(("University 1"),
                                        new Document(HomeFrag.uniList.get(0).getUniNameStr(),
                                                new Document(HomeFrag.uniList.get(0).getUniIndustryStr(),
                                                        new Document(HomeFrag.uniList.get(0).getUniProgram(),
                                                                new Document("Duration", HomeFrag.uniList.get(0).getIndustryPrograms().
                                                                        get(HomeFrag.uniList.get(0).getUniProgram()))))))

                                .append("University 2", new Document(HomeFrag.uniList.get(1).getUniNameStr(),
                                        new Document(HomeFrag.uniList.get(1).getUniIndustryStr(),
                                                new Document(HomeFrag.uniList.get(1).getUniProgram(),
                                                        new Document("Duration", HomeFrag.uniList.get(1).getIndustryPrograms()
                                                                .get(HomeFrag.uniList.get(1).getUniProgram()))))))

                                .append("University 3", new Document(HomeFrag.uniList.get(2).getUniNameStr(),
                                        new Document(HomeFrag.uniList.get(2).getUniIndustryStr(),
                                                new Document(HomeFrag.uniList.get(2).getUniProgram(),
                                                        new Document("Duration", HomeFrag.uniList.get(2).getIndustryPrograms()
                                                                .get(HomeFrag.uniList.get(2).getUniProgram()))))));
                        Task<UpdateResult> task = acctCollection.updateOne(new Document("user_id", LoginFrag.app.currentUser().getId()), updateDoc);

                        task.addOnCompleteListener(new OnCompleteListener<UpdateResult>() {
                            @Override
                            public void onComplete(@NonNull Task<UpdateResult> task) {
                                if (task.isSuccessful()) {
                                    System.out.println("Success: " + task.getResult().toString());
                                    goToHomePage();
                                } else {
                                    Log.e("ERROR", "Error: " + task.getException().toString());
                                }
                            }
                        });
                    }
                });
        return v;
    }

    private void uniSearch(final UniversityCallback universityCallback) {
        for (final String industry : RiasecFrag.industries) {
            Document project = new Document("University Name", 1).append("Industries", 1).append(industry, 1)
                    .append("Certificate Programs", 1).append("Diplomas", 1).append("Bachelors", 1).append("Address", 1);
            FindIterable<Document> fi = uniCollection.find().filter(new Document("Industries", industry)).projection(project);
            fi.iterator().addOnCompleteListener(new OnCompleteListener<MongoCursor<Document>>() {
                @Override
                public void onComplete(@NonNull Task<MongoCursor<Document>> task) {
                    String inIndustry = industry;
                    if (task.isSuccessful()) {
                        MongoCursor<Document> doc = task.getResult();
                        try {
                            int i = 0;
                            while (doc.hasNext()) {
                                Document document = doc.next();
                                resultStr.add(i, document.getString("University Name"));
                                String uniName = document.getString("University Name");
                                String location = document.getString("Address");
                                if(document.containsKey(inIndustry)){
                                    Document industry = (Document) document.get(inIndustry);

                                    if (FunnelPreferencesFrag.isLessOne) {
                                        progType = "Certificate Programs";
                                    } else if (FunnelPreferencesFrag.isOneToTwo) {
                                        progType = "Diplomas";
                                    } else if (FunnelPreferencesFrag.isMoreTwo) {
                                        progType = "Bachelors";
                                    }

                                    if (industry.containsKey(progType)) {
                                        Document type = (Document) industry.get(progType);
                                        university = new Universities(uniName, location, type.keySet(), inIndustry);
                                        for (String prog : university.getPrograms()) {
                                            Document programInfo = (Document) type.get(prog);
                                            if (programInfo.containsKey("Requirements")) {
                                                ArrayList<Double> requirements = (ArrayList<Double>) programInfo.get("Requirements");
                                                if ((requirements.size() + 1) == ONE_REQ) {
                                                    if (englishRequirments(Double.parseDouble(AcademicsFrag.englishMark), requirements.get(0))) {
                                                        university.addIndustryProgram(prog, programInfo.getString("Duration"));
                                                        isProg = true;
                                                    }
                                                } else if ((requirements.size() + 1) == TWO_REQ) {
                                                    if (englishRequirments(Double.parseDouble(AcademicsFrag.englishMark), requirements.get(0))
                                                            && mathRequirements(Double.parseDouble(AcademicsFrag.precalcMark), requirements.get(0))) {
                                                        university.addIndustryProgram(prog, programInfo.getString("Duration"));
                                                        isProg = true;
                                                    }
                                                }
                                                System.out.println("Industry programs: " + university.getIndustryPrograms().toString());
                                            }
                                            if (isProg) {
                                                Universities.addUniversities(university);
                                                isProg = false;
                                            }
                                        }
                                    }
                                }
                                i++;
                            }
                            universityCallback.onCallBack(Universities.getUniversities());
                        } finally {
                            doc.close();
                        }
                    } else {
                        Log.e("TAG", "Error: ", task.getException());
                    }
                }
            });
            System.out.println("One cycle complete");
        }
    }

    private boolean englishRequirments(double englishMark, double uniRequirement) {
        return englishMark > uniRequirement;
    }

    private boolean mathRequirements(double mathMark, double uniRequirement) {
        return mathMark > uniRequirement;
    }

    public void goToHomePage(){
        Intent gotoHomePage = new Intent(UnisFrag.this.getContext(), StartupActivity.class);
        startActivity(gotoHomePage);
    }

    private void setupUI(View v){
        mRecyclerView = v.findViewById(R.id.uni_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        continueHome = v.findViewById(R.id.continueHome);
        star = v.findViewById(R.id.image_star);
    }

}