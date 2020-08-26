package com.example.game_.other01_app.PopupDialogs;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.game_.other01_app.R;

public class GeneralErrorDialog extends Dialog {
    public GeneralErrorDialog(@NonNull Context context, String message) {
        super(context, R.style.Theme_AppCompat_Light);
        setContentView(R.layout.dialog_general_error);
        TextView errorTv = findViewById(R.id.dialog_general_error_message);
        errorTv.setText(message);

        Button closeButton = findViewById(R.id.dialog_general_error_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
