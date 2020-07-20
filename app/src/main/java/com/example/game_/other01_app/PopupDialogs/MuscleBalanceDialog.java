package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.R;

public class MuscleBalanceDialog extends Dialog {
    public MuscleBalanceDialog(@NonNull Context context) {

        super(context);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_add_muscle);
    }
}
