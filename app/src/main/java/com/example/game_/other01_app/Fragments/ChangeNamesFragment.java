package com.example.game_.other01_app.Fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.game_.other01_app.Adapters.NamesFragmentAdapter;
import com.example.game_.other01_app.Database.entities.User;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.UserViewModel;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChangeNamesFragment extends Fragment {

    private NamesFragmentAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_name_preferences, container, false);
        //Get the ViewModel
        UserViewModel mUserViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(UserViewModel.class);
        //Add the RecyclerView
        RecyclerView recyclerView = rootView.findViewById(R.id.name_preference_recyclerview);
        adapter = new NamesFragmentAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Add an observer for the LiveData returned by getUser
        mUserViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                //Update the cached copy of the user in the adapter.
                adapter.setUser(user);
            }
        });
        return rootView;
    }

    public String getUserName() {
        return adapter.getUserName();
    }

    public String getPatientName(){
        return adapter.getPatientName();
    }

    public boolean getExerciseTogether(){
        return adapter.getTogether();
    }

}
