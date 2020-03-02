package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.game_.other01_app.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class NeutralPopUpDialog extends AppCompatDialogFragment {

    public NeutralPopUpDialog() {

    }

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String dialogType = Objects.requireNonNull(getArguments()).getString("dialogType");
        AlertDialog.Builder builder = new AlertDialog.Builder(Objects.requireNonNull(getActivity()));
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_neutral_pop_up, null);
        builder.setView(view)
                .setTitle("Welcome")
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //do nothing
                    }
                });

        TextView dialogTextView = view.findViewById(R.id.pop_up_txt_view);

        setDialogText(dialogTextView, Objects.requireNonNull(dialogType));

        return builder.create();
    }

    private void setDialogText(TextView dialogTextView, String dialogType) {
        if(dialogType.equals("welcome")){
            dialogTextView.setText(getString(R.string.welcome_message, Objects.requireNonNull(getArguments()).getString("userName")));
        }
    }


}
