package com.example.game_.other01_app.Fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.EducationalViewModel;

public class EducationalFragment extends Fragment {

    private EducationalViewModel mViewModel;

    public static EducationalFragment newInstance() {
        return new EducationalFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(EducationalViewModel.class);
        // TODO: Use the ViewModel
    }

}