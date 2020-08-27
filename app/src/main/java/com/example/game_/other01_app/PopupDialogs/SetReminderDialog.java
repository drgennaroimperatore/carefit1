package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.R;

public class SetReminderDialog extends Dialog {
    public SetReminderDialog(@NonNull Context context) {
        super(context, R.style.Theme_AppCompat_Light);
        setContentView(R.layout.dialog_set_reminder);
    }
}
