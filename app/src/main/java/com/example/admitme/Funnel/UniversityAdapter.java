package com.example.admitme.Funnel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.admitme.R;

import java.util.ArrayList;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private ArrayList<UniversityItem> mUniList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener = listener;
    }

    public static class UniversityViewHolder extends RecyclerView.ViewHolder {

        public TextView uniName, uniLocation, uniIndustry, uniProgram;
        public ImageView star;
        public static UniversityItem item;

        public UniversityViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            uniName = itemView.findViewById(R.id.uni_name);
            uniLocation = itemView.findViewById(R.id.uni_location);
            uniIndustry = itemView.findViewById(R.id.uni_industry);
            uniProgram = itemView.findViewById(R.id.uni_program);
            star = itemView.findViewById(R.id.image_star);

            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(v, position);
                        if(item.isSelected()){
                            star.setImageResource(R.drawable.ic_filled_star);
                        } else{
                            star.setImageResource(R.drawable.ic_not_filled_star);
                        }
                    }
                }
            });
        }
    }

    public UniversityAdapter(ArrayList<UniversityItem> uniList) {
        mUniList = uniList;
    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.uni_item, parent, false);
        UniversityViewHolder uvh = new UniversityViewHolder(v, onItemClickListener);

        return uvh;
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {

        UniversityItem currentItem = mUniList.get(position);
        holder.uniName.setText(currentItem.getUniNameStr());
        holder.uniIndustry.setText(currentItem.getUniIndustryStr());
        holder.uniLocation.setText(currentItem.getUniLocationStr());
        holder.uniProgram.setText(currentItem.getUniProgram());
        holder.star.setImageResource(R.drawable.ic_not_filled_star);
    }

    @Override
    public int getItemCount() {
        return mUniList.size();
    }
}
