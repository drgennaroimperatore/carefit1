package com.example.game_.other01_app.Activities;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.game_.other01_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * This is the Home Screen ACTIVITY
 */

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(HomeScreenActivity.this, "Home Screen clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.progress:
                        Toast.makeText(HomeScreenActivity.this, "Progress screen clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.exercise_list:
                        Toast.makeText(HomeScreenActivity.this, "Exercise screen clicked", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.profile:
                        Toast.makeText(HomeScreenActivity.this, "Profile screen clicked", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}
//    public void openExercise(View view){
//        Intent intent = new Intent(this, ExerciseListActivity.class);
//        startActivity(intent);
//    }
//    @Override
//    public void onBackPressed() {
//        //Do nothing.
//    }

