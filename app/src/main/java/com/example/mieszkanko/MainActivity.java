package com.example.mieszkanko;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.mieszkanko.BottomNavigationBarFragments.ProfileFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ScheduleFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ShoppingListFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.StatisticsFragment;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new ScheduleFragment());
    }

    private boolean loadFragment(Fragment fragment)
    {
        if(fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();


            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_schedule:
                fragment = new ScheduleFragment();
                break;
            case R.id.navigation_shopping_list:
                fragment = new ShoppingListFragment();
                break;
            case R.id.navigation_statistics:
                fragment = new StatisticsFragment();
                break;
            case R.id.navigation_settings:
                fragment = new ProfileFragment();
                break;
        }

        return loadFragment(fragment);
    }
}
