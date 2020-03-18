package com.example.game_.other01_app.Activities;
import android.os.Bundle;

import com.example.game_.other01_app.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.home, R.id.navigation_progress, R.id.navigation_exercise, R.id.navigation_profile )
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

}






























//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
////import com.example.game_.other01_app.Fragments.homeFragment;
//import com.example.game_.other01_app.R;
//import com.google.android.material.bottomnavigation.BottomNavigationView;
//
//public class MainActivity extends AppCompatActivity {
//
//    // BottomNavigationView bottomNavigation;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //getSupportActionBar().hide();
//        setContentView(R.layout.activity_main);
//
//        // bottomNavigation = findViewById(R.id.navigation);
//
//
////        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
////        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
////        NavigationUI.setupWithNavController(bottomNav, navController);
//
////        BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
////                new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//
//        BottomNavigationView bar = findViewById(R.id.bottom_navigation);
//
//        bar.setOnNavigationItemSelectedListener(item -> {
//            //Fragment fragment=null;
//            switch (item.getItemId()) {
//                case R.id.home:
//                    Toast.makeText(MainActivity.this, "home", Toast.LENGTH_SHORT).show();
//                    break;
//
//                case R.id.progress:
//                    Toast.makeText(MainActivity.this, "progress", Toast.LENGTH_SHORT).show();
//                    break;
//
//                case R.id.exercise_list:
//                    Toast.makeText(MainActivity.this, "exercise", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(this, ExerciseListActivity.class);
//                    startActivity(intent);
//                    break;
//
//                case R.id.profile:
//                    Toast.makeText(MainActivity.this, "profile", Toast.LENGTH_SHORT).show();
//                    break;
//
//
//            }
//            return true;
//        });
//    }
//}
//
//
//
//
//
//
//
//
//
//
////
////    private boolean LoadFragment(Fragment fragment) {
////        if (fragment != null) {
////            getSupportFragmentManager()
////                    .beginTransaction()
////                    .replace(R.id.nav_host_fragment, fragment)
////                    .commit();
////            return true;
////        }
////        return false;
////    }
//
//



