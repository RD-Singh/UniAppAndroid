package com.example.admitme;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private ArrayList<Universities> mUniversities;
    private FirebaseFirestore fStore;
    private FirebaseAuth fAuth;
    public boolean toggled = false;

    public static class UniversityViewHolder extends RecyclerView.ViewHolder {

        public TextView mUniversityName;
        public TextView mLocation;
        public ImageView mBookmarkImage;
        public TextView mAcceptanceRate;
        public CardView mItemView;

        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            mUniversityName = itemView.findViewById(R.id.university_name);
            mLocation = itemView.findViewById(R.id.location);
            mAcceptanceRate = itemView.findViewById(R.id.acceptance_rate);
            mBookmarkImage = itemView.findViewById(R.id.bookmark_image);
            mItemView = itemView.findViewById(R.id.university_item);

        }

    }

    public UniversityAdapter(ArrayList<Universities> universities) {

        mUniversities = universities;

    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.university_item, parent, false);

        UniversityViewHolder universityViewHolder = new UniversityViewHolder(v);

        return universityViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final UniversityViewHolder holder, int position) {

        final Universities currentItem = mUniversities.get(position);

        fAuth = FirebaseAuth.getInstance();

        holder.mUniversityName.setText(currentItem.getUniversityName());
        holder.mLocation.setText(currentItem.getUniversityLocation());
        holder.mAcceptanceRate.setText(String.valueOf(currentItem.getAcceptanceRate()));
        holder.mBookmarkImage.setImageResource(currentItem.getBookmarkResource());

        holder.mBookmarkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference dRef = fStore.getInstance().collection("ACCOUNTS").document(fAuth.getCurrentUser().getUid());

                if (toggled) {
                    toggled = false;
                    dRef.collection("SELECTED UNIVERSITIES").document(currentItem.getUniversityName()).delete();
                    holder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_not_filled);
                } else {
                    toggled = true;
                    Map<String, Object> info = new HashMap<>();
                    info.put("AcceptanceRate", currentItem.getAcceptanceRate());
                    info.put("Location", currentItem.getUniversityLocation());
                    dRef.collection("SELECTED UNIVERSITIES").document(currentItem.getUniversityName()).set(info);
                    holder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_filled);
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return mUniversities.size();

    }

    public void filterList(ArrayList<Universities> filteredList) {

        mUniversities = filteredList;
        notifyDataSetChanged();

    }

}
