package com.example.converter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.converter.ui.history.HistoryFragment;
import com.example.converter.ui.home.HomeFragment;
import com.example.converter.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Главная активность
 * инициализация навигационного меню
 * @author Vadim
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            ProfileFragment profileFragment = new ProfileFragment();
            HistoryFragment historyFragment = new HistoryFragment();
            HomeFragment homeFragment = new HomeFragment();

            profileFragment.setArguments(extras);
            historyFragment.setArguments(extras);
            homeFragment.setArguments(extras);
        }

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_history)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



    }
}