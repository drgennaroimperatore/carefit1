package com.example.game_.other01_app.Activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.game_.other01_app.Fragments.ExerciseInstructionBoxFragment;
import com.example.game_.other01_app.R;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ExerciseInstructionsActivity extends AppCompatActivity {

    public static final int EXERCISE_INSTRUCTIONS_ACTIVITY_REQUEST_CODE = 2;
    public static final String EXERCISE_NAME_REPLY = "instructionNameReply";
    public static final String EXERCISE_INTENSITY_REPLY = "instructionIntensityReply";
    public static final String EXERCISE_LONG_REPLY = "instructionLongReply";

    private long MilliTime, StartTime, TimeBuffer, UpdateTime = 0L;
    private int Seconds, Minutes, MilliSeconds;
    private TextView timer;
    private Button startBtn;
    private Button pauseBtn;
    private SharedPreferences mSharedPreferences;

    public Handler getHandler() {
        return handler;
    }

    private Handler handler;
    private String exerciseName;
    private String exerciseIntensity;
    private MenuItem backBtn;

    private boolean tutorial;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);

        tutorial = mSharedPreferences.getBoolean("needsTutorial", true);
        
        setContentView(R.layout.activity_exercise_instructions);
        exerciseName = getIntent().getStringExtra("exerciseName");
        String exerciseDesc = getIntent().getStringExtra("exerciseDesc");
        exerciseIntensity = getIntent().getStringExtra("exerciseIntensity");

        setUpAesthetics(exerciseName, exerciseDesc);

        timer = findViewById(R.id.instructions_timer);
        startBtn = findViewById(R.id.instructions_startBtn);
        pauseBtn = findViewById(R.id.instructions_pauseBtn);
        handler = new Handler();

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(true);
                backBtn.setEnabled(false);
            }
        });

        pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuffer += MilliTime;
                handler.removeCallbacks(runnable);
                startBtn.setEnabled(true);
                pauseBtn.setEnabled(false);
                backBtn.setEnabled(true);
            }
        });
        if(tutorial) {
            showDialog();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_instructions, menu);
        backBtn = menu.findItem(R.id.instructions_back);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.instructions_back:
                handler.removeCallbacks(runnable);
                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXERCISE_NAME_REPLY, exerciseName);
                replyIntent.putExtra(EXERCISE_INTENSITY_REPLY, exerciseIntensity);
                replyIntent.putExtra(EXERCISE_LONG_REPLY, TimeBuffer);
                setResult(RESULT_OK, replyIntent);
                finish();
                return true;
            case R.id.instructions_help:
                showDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Starts the thread which runs the exercise timer
     */
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MilliTime = SystemClock.uptimeMillis() - StartTime;
            UpdateTime = TimeBuffer + MilliTime;
            Seconds = (int) (UpdateTime/1000);
            Minutes = Seconds/60;
            Seconds = Seconds % 60;
            MilliSeconds = (int) (UpdateTime % 1000);
            timer.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%03d", MilliSeconds));
            handler.postDelayed(this, 0);
            if (Minutes == 30) {
                handler.removeCallbacks(this);
                startBtn.setEnabled(false);
                pauseBtn.setEnabled(false);
            }
        }
    };


    private void setUpAesthetics(String exerciseName, String exerciseDesc){
        setTitle(exerciseName);

        ExerciseInstructionBoxFragment boxFragment = new ExerciseInstructionBoxFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.instructions_frag_holder, boxFragment).commit();

        ImageView image = findViewById(R.id.instructions_image);
        image.setImageResource(getApplicationContext().getResources().getIdentifier(
                exerciseName.replaceAll("\\s+", "").toLowerCase(),
                "drawable",
                getPackageName()
        ));
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(ExerciseInstructionsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("This is " + exerciseName + ", a " + exerciseIntensity +
                " level exercise.\nTo get the most out of these exercises, you " +
                "should perform them until you feel you can't do another.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                showSecondDialog();
            }
        });
        dialog.show();
    }

    private void showSecondDialog(){
        final Dialog dialog = new Dialog(ExerciseInstructionsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Before you start the exercise, you should read the\nInstructions.");
        Button dialogBtn = dialog.findViewById(R.id.custom_exercise_button);
        dialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
                showThirdDialog();
            }
        });
        dialog.show();
    }

    private void showThirdDialog() {
        final Dialog dialog = new Dialog(ExerciseInstructionsActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_first_exercise);
        TextView text = dialog.findViewById(R.id.custom_exercise_text);
        text.setText("Once you have read them, START the timer and perform as many " +
                "repetitions of the exercise as you can. Then press PAUSE and FINISH " +
                "to finish.");
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
