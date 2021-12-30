package com.uc.sej11.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uc.sej11.R;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";
    BottomNavigationView bottomNavigationView;
    Fragment fragment;
    FragmentManager fragmentManager;
    NavHostFragment navHostFragment;
    //Toolbar toolbar;

    //Fragment playFragment;
    Fragment materiFragment;
    Fragment scoreboardFragment;
    Fragment optionFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav_main_menu);
        //toolbar = findViewById(R.id.toolbar_main_menu);
        //setSupportActionBar(toolbar);

        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main_menu);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.PlayFragment,
                R.id.MateriFragment, R.id.ScoreboardFragment, R.id.OptionFragment).build();

        //NavigationUI.setupActionBarWithNavController(MenuActivity.this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }
}