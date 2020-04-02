package com.example.game_.other01_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.game_.other01_app.Utility.KeyboardUtility;
import com.example.game_.other01_app.FragmentChangeListener;
import com.example.game_.other01_app.Fragments.SetNamesFragment;
import com.example.game_.other01_app.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SetPreferencesActivity extends FragmentActivity implements
        FragmentChangeListener {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_preferences);

        mSharedPreferences = getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

        if (mSharedPreferences.getBoolean("isFirstTime", true)) {
            mEditor.putBoolean("isFirstTime", false);
            mEditor.apply();
        } else {
            Intent intent = new Intent(this, ExerciseListActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            //finish();
        }

        //Check that the activity is using the layout version with
        //the preference_frag_container LinearLayout
        if(findViewById(R.id.preference_frag_container) != null) {
            //However, if we're being restored from a previous state, then we
            //dont need to do anything and should return or else we could end
            //up with overlapping fragments
            if (savedInstanceState != null) {
                return;
            }
            //Create new ChangeNamesFragment to be placed in the activity layout
            SetNamesFragment firstFragment = new SetNamesFragment();

            //In case this activity was started with special instructions from an Intent,
            //pass the Intent's extras to the fragment as arguments
            //firstFragment.setArguments(getIntent().getExtras());
            //Add the fragment to the 'preference_frag_container' LinearLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.preference_frag_container, firstFragment).commit();

        }


    }

    @Override
    public void replaceFragment(Fragment fragment) {
        Bundle args = new Bundle();
        args.putBoolean("firstTime", true);
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Note: replace requires the id of the view who is holding the fragments inside.
        fragmentTransaction.replace(R.id.preference_frag_container, fragment, fragment.toString());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        KeyboardUtility.hideKeyboardFromActivity(this);
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            Log.i("SetPreferencesActivity", "popping backstack");
            fm.popBackStack();
        }
    }
}
