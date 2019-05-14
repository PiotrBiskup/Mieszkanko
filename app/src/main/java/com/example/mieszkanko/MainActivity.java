package com.example.mieszkanko;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.mieszkanko.BottomNavigationBarFragments.ProfileFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ScheduleFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ShoppingListFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.StatisticsFragment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    //ShoppingList fragment variables
    public List<String> productsNameToBuyList;
    public List<String> productsDescriptionToBuyList;
    public List<String> boughtProductsNameList;
    public List<Double> productsPriceList;
    public List<Date> shoppingDateList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsNameToBuyList = new ArrayList<>();
        productsNameToBuyList.add("Papier toaletowy");
        productsNameToBuyList.add("Olej");
        productsNameToBuyList.add("Mop");

        productsDescriptionToBuyList = new ArrayList<>();
        productsDescriptionToBuyList.add("opis papier toaletowy");
        productsDescriptionToBuyList.add("opis olej");
        productsDescriptionToBuyList.add("opis mop");


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

    public void hideKeyboard()
    {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
