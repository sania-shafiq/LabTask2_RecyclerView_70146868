package com.example.labsevenrecyclerviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {

    /**
     * Tells the screen (MainActivity) which card was tapped.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private final ArrayList<Course> courses;
    private final OnItemClickListener listener;

    public CourseAdapter(ArrayList<Course> courses, OnItemClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courses.get(position);
        holder.tvCode.setText(course.getCode());
        holder.tvTitle.setText(course.getTitle());
        holder.tvCredits.setText(course.getCreditHours() + " Credit Hours");
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    /**
     * Keeps the card's views, so they are found only once (in the constructor).
     */
    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        TextView tvCode, tvTitle, tvCredits;

        public CourseViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            tvCode = itemView.findViewById(R.id.tvCode);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvCredits = itemView.findViewById(R.id.tvCredits);

            // Report the position of the tapped card
            itemView.setOnClickListener(v -> {
                int position = getBindingAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(position);
                }
            });
        }
    }
}
