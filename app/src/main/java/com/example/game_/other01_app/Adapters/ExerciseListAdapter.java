package com.example.game_.other01_app.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game_.other01_app.Activities.ExerciseListActivity;
import com.example.game_.other01_app.Database.entities.Exercise;
import com.example.game_.other01_app.Activities.ExerciseInstructionsActivity;
import com.example.game_.other01_app.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ExerciseListAdapter extends RecyclerView.Adapter<ExerciseListAdapter.ExerciseViewHolder>{

    private final Context mContext;
    private TextView warningTextView;

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        private final ImageButton exerciseImageButton;
        private final TextView exerciseTextView;
        private ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseImageButton = itemView.findViewById(R.id.exerciseImageButton);
            exerciseTextView = itemView.findViewById(R.id.exerciseNameText);
        }
    }

    private final LayoutInflater mInflater;
    private List<Exercise> mExercises; //cached copy of exercises

    public ExerciseListAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.exerciselist_recyclerview_item, parent, false);
        return new ExerciseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        if(mExercises != null ) {
            Exercise current = mExercises.get(position);
                holder.exerciseImageButton.setImageResource(mContext.getResources().getIdentifier(
                        current.getImage(),
                        "drawable",
                        mContext.getPackageName()
                ));
            switch (current.getIntensity()) {
                case "low":
                    setBackgroundForImgButton(holder.exerciseImageButton, "greenbg");
                    break;
                case "mid":
                    setBackgroundForImgButton(holder.exerciseImageButton, "orangebg");
                    break;
                case "high":
                    setBackgroundForImgButton(holder.exerciseImageButton, "redbg");
                    break;
            }
                holder.exerciseImageButton.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                holder.exerciseImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openRelatedExercisePage(current.getName(), current.getDesc(), current.getIntensity());
                    }
                });
                holder.exerciseTextView.setText(current.getName());
        } else {
            // Covers the case of data not being ready yet.

        }
    }


    public void setExercises(List<Exercise> exercises) {
        mExercises = exercises;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mExercises != null) {
            return mExercises.size();
        }
        else { return 0; }
    }

    private void setBackgroundForImgButton(ImageButton imgButton, String idName) {
        imgButton.setBackgroundResource(mContext.getResources().getIdentifier(
                idName, "drawable", mContext.getPackageName()
        ));
    }

    private void openRelatedExercisePage(String name, String desc, String intensity) {
        Intent exercisePageIntent = new Intent(mContext,
                ExerciseInstructionsActivity.class);
        exercisePageIntent.putExtra("exerciseName", name);
        exercisePageIntent.putExtra("exerciseDesc", desc);
        exercisePageIntent.putExtra("exerciseIntensity", intensity);
        ((ExerciseListActivity)mContext).startActivityForResult(exercisePageIntent,
                ExerciseInstructionsActivity.EXERCISE_INSTRUCTIONS_ACTIVITY_REQUEST_CODE);
    }
}
