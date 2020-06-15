package com.example.admitme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private ArrayList<Universities> mUniversities;
    private FirebaseFirestore fStore;

    public static class UniversityViewHolder extends RecyclerView.ViewHolder {

        public TextView mUniversityName;
        public TextView mLocation;
        public ImageView mBookmarkImage;
        public boolean counter;

        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            mUniversityName = itemView.findViewById(R.id.university_name);
            mLocation = itemView.findViewById(R.id.location);
            mBookmarkImage = itemView.findViewById(R.id.bookmark_image);
            counter = false;
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

        holder.mUniversityName.setText(currentItem.getUniversityName());
        holder.mLocation.setText(currentItem.getUniversityLocation());
        holder.mBookmarkImage.setImageResource(currentItem.getBookmarkResource());

        holder.mBookmarkImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DocumentReference dRef = fStore.getInstance().collection("UNIVERSITIES").document(currentItem.getUniversityName());

                if (holder.counter) {

                    holder.counter = false;
                    dRef.update("bookmarkToggled", holder.counter);
                    holder.mBookmarkImage.setImageResource(R.drawable.ic_bookmark_not_filled);

                } else {

                    holder.counter = true;
                    dRef.update("bookmarkToggled", holder.counter);
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
