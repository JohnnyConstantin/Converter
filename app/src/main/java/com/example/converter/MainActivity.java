package com.example.converter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.converter.ui.history.HistoryFragment;
import com.example.converter.ui.home.HomeFragment;
import com.example.converter.ui.profile.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Main Activity
 * initialization of the navigation menu
 * @author Vadim
 */
public class MainActivity extends AppCompatActivity {
    private String userId;
    private String login;
    private String[] curs = null;

    public String[] getCurs() {
        return curs;
    }

    public void setCurs(String[] curs) {
        this.curs = curs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_profile, R.id.navigation_history)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Bundle extras = getIntent().getExtras();
        login = extras.getString("pLogin");
        userId = extras.getString("userId");

    }

    /**
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     *
     * @return
     */
    public String getLogin() {
        return login;
    }
}