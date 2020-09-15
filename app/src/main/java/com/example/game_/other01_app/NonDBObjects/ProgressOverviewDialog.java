package com.example.game_.other01_app.NonDBObjects;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.R;

public class ProgressOverviewDialog extends Dialog {
    public ProgressOverviewDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Light);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_progress_overview);
    }
}
