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

import com.example.game_.other01_app.Adapters.ReminderListAdapter;
import com.example.game_.other01_app.Database.entities.Reminder;
import com.example.game_.other01_app.R;
import com.example.game_.other01_app.ViewModels.ReminderViewModel;

import java.util.List;
import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RemindersListActivity extends AppCompatActivity {

    public static final int REMINDERS_LIST_REQUEST_CODE = 5;
    private ReminderViewModel mReminderViewModel;
    private static final int NEW_REMINDER_ACTIVITY_REQUEST_CODE = 1;
    private SharedPreferences mSharedPreferences;

    private boolean tutorial;

    private Menu menu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminders_page);

        setTitle("Reminders");

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);

        //Get the viewmodel from the viewmodel provider
        mReminderViewModel = ViewModelProviders.of(this).get(ReminderViewModel.class);

        RecyclerView reminderRecyclerView = findViewById(R.id.reminder_recycler_view);
        final ReminderListAdapter adapter = new ReminderListAdapter(this, mReminderViewModel);
        reminderRecyclerView.setAdapter(adapter);
        reminderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //Add an observer for the LiveData returned by getAllReminders()
       /* mReminderViewModel.getAllRemidners().observe(this, new Observer<List<Reminder>>() {
            @Override
            public void onChanged(List<Reminder> reminders) {
                //Update the cached copy of the reminders in the adapter.
                adapter.setReminders(reminders);
            }
        });*/

        if(tutorial) {
            showTutorialDialog();
        }
    }

    private void disableButtons(){
        menu.findItem(R.id.reminder_back_btn).setEnabled(false);
    }

    private void enableButtons(){
        menu.findItem(R.id.reminder_back_btn).setEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_reminders, menu);
        this.menu = menu;
        if (tutorial){
            disableButtons();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reminder_back_btn:
                setResult(RESULT_OK);
                finish();
                return true;
            case R.id.reminder_add_btn:
                Intent intent = new Intent(this, NewReminderActivity.class);
                startActivityForResult(intent, NEW_REMINDER_ACTIVITY_REQUEST_CODE);
                enableButtons();
                return true;
            case R.id.reminder_help_btn:
                showTutorialDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK) {
            Reminder reminder = Objects.requireNonNull(data).getParcelableExtra(NewReminderActivity.EXTRA_REPLY);
            mReminderViewModel.insert(reminder);
            if(tutorial) {
                showAfterAddReminder();
            }
        } else if(requestCode == NewReminderActivity.EDIT_REMINDER_ACTIVITY_REQUEST_CODE
        && resultCode == RESULT_OK) {
            Reminder reminder = Objects.requireNonNull(data).getParcelableExtra(NewReminderActivity.EXTRA_REPLY);
            mReminderViewModel.insert(reminder);
        }
        else {
            Toast.makeText(
                    getApplicationContext(),
                    "Reminder Not Saved.",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void showTutorialDialog(){
        final Dialog dialog = new Dialog(RemindersListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Press ADD to set your first Reminder.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                if(!tutorial){
                    showAfterAddReminder();
                }
            }
        });

        dialog.show();
    }

    public void showAfterAddReminder(){
        final Dialog dialog = new Dialog(RemindersListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("You can DELETE or EDIT the time of the Reminder here, or press ADD to add another.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                showAfterSecond();
            }
        });
        dialog.show();
    }

    public void showAfterSecond(){
        final Dialog dialog = new Dialog(RemindersListActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Press HOME to go back.");
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
