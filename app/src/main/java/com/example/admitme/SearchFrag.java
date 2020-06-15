package com.example.admitme;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.concurrent.Executor;

public class SearchFrag extends Fragment {

    private RecyclerView mRecyclerView;
    private UniversityAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static final String TAG = "TAG";
    private EditText mSearchBar;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    private int bookmark;
    private String uid;
    ArrayList<Universities> mUniversities;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        createUniversityList();

        setUpUI(v);
        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mSearchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                filter(editable.toString());

            }
        });

        buildRecyclerView(v);

        return v;
    }

    public void filter(String university) {

        ArrayList<Universities> filteredList = new ArrayList<>();

        for (Universities uni : mUniversities) {

            if (uni.getUniversityName().toLowerCase().contains(university.toLowerCase())) {

                filteredList.add(uni);

            }

        }

        mAdapter.filterList(filteredList);

    }

    public void createUniversityList() {

        mUniversities = new ArrayList<>();


        getAllFromFireStore(new OnCompleteListener<QuerySnapshot>() {
            String universityName;
            double acceptanceRate;
            String universityLocation;

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document : task.getResult()) {

                        universityName = document.getId();
                        bookmarkResource(universityName);
                        acceptanceRate = document.getDouble("AcceptanceRate");
                        universityLocation = document.getString("Location");

                        mUniversities.add(new Universities(universityName, universityLocation, acceptanceRate, bookmark));

                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

    }

    public int setBookmark(int bookmark) {
        this.bookmark = bookmark;
        return this.bookmark;
    }
//hhhh
    public void bookmarkResource(String universityName) {

        DocumentReference dRef = fStore.collection("UNIVERSITIES").document(universityName);

        dRef.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            int bMark;

            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (documentSnapshot.getBoolean("bookmarkToggled")) {
                    bMark = R.drawable.ic_bookmark_filled;
                } else {
                    bMark = R.drawable.ic_bookmark_not_filled;
                }
                setBookmark(bMark);
            }
        });
    }

    public static void getAllFromFireStore(OnCompleteListener<QuerySnapshot> listener) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("UNIVERSITIES").get().addOnCompleteListener(listener);
    }

    public void buildRecyclerView(View v) {

        mRecyclerView = v.findViewById(R.id.university_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(SearchFrag.this.getContext());
        mAdapter = new UniversityAdapter(mUniversities);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void setUpUI(View v) {

        mSearchBar = v.findViewById(R.id.search_edit_text);

    }

}