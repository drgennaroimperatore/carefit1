package com.example.game_.other01_app.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.Fragments.CreateReminderFragment;
import com.example.game_.other01_app.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NewReminderActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "someReply";
    public static final int EDIT_REMINDER_ACTIVITY_REQUEST_CODE = 2;
    private boolean editing = false;
    private int id = 0;
    private SharedPreferences mSharedPreferences;

    private boolean tutorial;

    private CreateReminderFragment createReminderFragment;

    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reminder);

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);

        setTitle("New Reminder");

        createReminderFragment = new CreateReminderFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.new_reminder_fragment_zone, createReminderFragment).commit();

        Intent intent = getIntent();
        if(intent.hasExtra("editing")){
            setTitle("Edit Reminder");
            editing = intent.getBooleanExtra("editing", false);
        }
        if(intent.hasExtra("id")){
            id = intent.getIntExtra("id", 0);
        }

        if(tutorial) {
            showTutorialDialog();
        }

    }

    private void disableButtons(){
        menu.findItem(R.id.new_reminder_back_btn).setEnabled(false);
    }

    private void enableButtons(){
        menu.findItem(R.id.new_reminder_back_btn).setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_new_reminder, menu);
        this.menu = menu;
        if(tutorial){
            disableButtons();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_reminder_back_btn:
                Intent replyIntent = new Intent();
                setResult(RESULT_CANCELED, replyIntent);
                finish();
                return true;
            case R.id.new_reminder_help_btn:
                showTutorialDialog();
                return true;
            case R.id.new_reminder_save_btn:
                if(createReminderFragment.getCheckedDays().equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.error_select_day,
                            Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Intent saveIntent = new Intent();
                    Reminder reminder = createReminderFragment.createReminderEntitiy();
                    if (editing) {
                        reminder.setId(id);
                    }
                    saveIntent.putExtra(EXTRA_REPLY, reminder);
                    setResult(RESULT_OK, saveIntent);
                    enableButtons();
                    finish();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showTutorialDialog(){
        final Dialog dialog = new Dialog(NewReminderActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Reminders will repeat once per week. You can select the time and days to " +
                "repeat on here.\n\nPress SAVE to finish.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });

        dialog.show();
    }
}
