package com.example.game_.other01_app.Activities;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import com.example.game_.other01_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;


/**
 * This is the com.example.game_.other01_app.Activities.UserInterface.Home Screen ACTIVITY
 */

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        TextView title = (TextView) findViewById(R.id.hometext);
        title.setText("HOME PAGE");




        BottomNavigationView bottomView = (BottomNavigationView) findViewById(R.id.bottomNavView_bar);

        Menu menu = bottomView.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

        bottomView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {

                    @Override

                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                //openHome();
                                break;
                            case R.id.navigation_progress:
                                openProgress();
                                break;
                            case R.id.navigation_exercise:
                                openExercise();
                                break;
                            case R.id.navigation_profile:
                                openProfile();
                                break;
                        }
                        return false;

                    }
                });
    }


    public void openHome(){
        Intent intent1 = new Intent(HomeScreenActivity.this, HomeScreenActivity.class);
        startActivity(intent1);
    }

    public void openProgress(){
        Intent intent2 = new Intent(HomeScreenActivity.this, RecordsActivity.class);
        startActivity(intent2);
    }

    public void openExercise(){
        Intent intent3 = new Intent(HomeScreenActivity.this, ExerciseListActivity.class);
        startActivity(intent3);
    }

    public void openProfile(){
        Intent intent4 = new Intent(HomeScreenActivity.this, ProfileScreenActivity.class);
        startActivityForResult(intent4,1);
    }




}