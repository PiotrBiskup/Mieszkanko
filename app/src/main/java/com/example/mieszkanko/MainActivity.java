package com.example.mieszkanko;

import com.example.mieszkanko.Models.Apartment;
import com.example.mieszkanko.Models.Period;
import com.example.mieszkanko.Models.Purchased;
import com.example.mieszkanko.Models.Schedule;
import com.example.mieszkanko.Models.User;
import com.example.mieszkanko.Models.ShoppingList;
import com.example.mieszkanko.ShoppingFragments.ToBuyFragment;
import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.BottomNavigationBarFragments.ProfileFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ScheduleFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ShoppingListFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.StatisticsFragment;
import com.example.mieszkanko.Models.Product;
import com.example.mieszkanko.ShoppingFragments.ToBuyFragment;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MOJE INFO !!!!!";

    DatabaseReference mRootRef;
    DatabaseReference mShoppingListRef;

    private AdView mAdView;
    String userIdOfThisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //modul reklamowy

        MobileAds.initialize(this, "ca-app-pub-4965283920341222~7075139777");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mShoppingListRef = mRootRef.child("shopping_list");

        Bundle bundle = getIntent().getExtras();
        userIdOfThisUser = bundle.getString("messageUserId");
        Log.w(TAG, "UUUUUUUUUUUUUUSER IIIIIIID  ++++++++ " +  userIdOfThisUser);

        mRootRef.child("users").child(userIdOfThisUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                currentUser.setKey(userIdOfThisUser);
                AccountSettings.setUser(currentUser);
                Log.w(TAG, "++++++++++++ NNNNNNIIIIIIIICCCCCCCCKKKKKKK  ++++++++ " +  AccountSettings.getUser().getNick());
                Log.w(TAG, "++++++++++++ AAAAAAAAAPPPPARTMENT FROM UUUUUSER  ++++++++ " +  AccountSettings.getUser().getApartment());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });



        //get settings from database

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        loadFragment(new ProfileFragment());
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



    @Override
    protected void onStart() {
        super.onStart();

        mShoppingListRef.child("to_buy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Product> toBuyList = new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Product prod = ds.getValue(Product.class);
                    prod.setKeyId(ds.getKey());
//                    Log.w(TAG, "KEY IDDDDDDDDDDDD ++++++++ " +  prod.getKeyId());
                    toBuyList.add(prod);
                }

                Collections.reverse(toBuyList);
                AccountSettings.getShoppingList().setToBuy(toBuyList);

                try {
                    FragmentManager fm = getSupportFragmentManager();
                    ShoppingListFragment fragment = (ShoppingListFragment) fm.findFragmentById(R.id.fragment_container);
                    fragment.getToBuyFragment().notifyAdapter();
//                    fragment.getShoppingListHistoryFragment().notifyAdapter();
                }
                catch (Exception e)
                { }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        mShoppingListRef.child("purchased").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                List<Purchased> purchasedList = new ArrayList<>();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Purchased purch = ds.getValue(Purchased.class);
//                    purch.setKeyId(ds.getKey());
//                    Log.w(TAG, "KEY IDDDDDDDDDDDD ++++++++ " +  prod.getKeyId());
                    purchasedList.add(purch);
                }

                Collections.reverse(purchasedList);
                AccountSettings.getShoppingList().setPurchased(purchasedList);

                try {
                    FragmentManager fm = getSupportFragmentManager();
                    ShoppingListFragment fragment = (ShoppingListFragment) fm.findFragmentById(R.id.fragment_container);
                    fragment.getShoppingListHistoryFragment().notifyAdapter();
                }
                catch (Exception e)
                { }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

}
