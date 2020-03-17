package com.example.game_.other01_app.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.game_.other01_app.AssistanceClasses.ListAssist;
import com.example.game_.other01_app.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ExerciseInstructionBoxFragment extends Fragment {

    private TextView instructionsTextView;
    private TextView pageCountTextView;
    private ImageButton backButton;
    private ImageButton nextButton;
    private int maxPageCount;
    private int currentPageCount;

    //Fills the instructions box with the correct text for each exercise
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instructions_box, container, false);

        String instructions = "Instructions Not Found";

        Intent intent = getActivity().getIntent();
        if(intent.hasExtra("exerciseDesc")){
            instructions = intent.getStringExtra("exerciseDesc");
        }

        List<String> instructionsList = ListAssist.convertInstructionsToListOfStrings(instructions);
        maxPageCount = instructionsList.size();
        currentPageCount = 1;
        instructionsTextView = rootView.findViewById(R.id.instructions_text_view);
        pageCountTextView = rootView.findViewById(R.id.instructions_page_count);


        //button for clicking forwards through the instructions
        backButton = rootView.findViewById(R.id.instructions_text_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPageCount = currentPageCount - 1;
                changeThePage(instructionsList);
            }
        });

        //button for clicking forwards through the instructions
        nextButton = rootView.findViewById(R.id.instructions_text_next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPageCount = currentPageCount + 1;
                changeThePage(instructionsList);
            }
        });

        changeThePage(instructionsList);

        return rootView;
    }

    private void changeThePage(List<String> instructionsList){
        backButton.setEnabled(true);
        nextButton.setEnabled(true);
        if(currentPageCount == 1){
            backButton.setEnabled(false);
        }
        if(currentPageCount == maxPageCount){
            nextButton.setEnabled(false);
        }
        instructionsTextView.setText(
                instructionsList.get(currentPageCount - 1)
        );
        setPageCountTextView(currentPageCount, maxPageCount);
    }

    private void setPageCountTextView(int currentPage, int maxPage){
        String pageCountString = currentPage + "/" + maxPage;
        pageCountTextView.setText(pageCountString);
    }
}
