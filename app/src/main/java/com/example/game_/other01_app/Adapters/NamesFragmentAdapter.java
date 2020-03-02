package com.example.game_.other01_app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NamesFragmentAdapter extends RecyclerView.Adapter<NamesFragmentAdapter.NamesViewHolder> {

    private EditText userName;
    private EditText patient;
    private CheckBox together;


    class NamesViewHolder extends RecyclerView.ViewHolder {
        private final EditText userNameEditText;
        private final EditText patientNameEditText;
        private final CheckBox togetherCheckBox;

        private NamesViewHolder(View itemView) {
            super(itemView);
            userNameEditText = itemView.findViewById(R.id.enter_your_name);
            patientNameEditText = itemView.findViewById(R.id.enter_their_name);
            togetherCheckBox = itemView.findViewById(R.id.together_checkbox);
        }
    }

    private final LayoutInflater mInflater;
    private User mUser; //Cached copy of the user

    public NamesFragmentAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NamesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.user_recyclerview_item, parent,false);
        return new NamesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NamesViewHolder holder, int position) {
        if(mUser != null) {
            User user = mUser;
            holder.userNameEditText.setText(user.getUserName());
            holder.patientNameEditText.setText(user.getCareName());
            holder.togetherCheckBox.setChecked(user.isExerciseWith());
        } else {
            holder.userNameEditText.setHint("Please enter name");
        }
        userName = holder.userNameEditText;
        patient = holder.patientNameEditText;
        together = holder.togetherCheckBox;
    }

    public String getUserName(){
        return userName.getText().toString();
    }

    public String getPatientName() {
        return patient.getText().toString();
    }

    public boolean getTogether() {
        return together.isChecked();
    }

    public void setUser(User user){
        mUser = user;
        notifyDataSetChanged();
    }

    //getItemCount() is called many times, and when it is first called,
    //mUser has not been updated (means initially, it's null, and we can't return null)

    @Override
    public int getItemCount() {
        if(mUser!=null)
            return 1;
        else return 0;
    }
}
