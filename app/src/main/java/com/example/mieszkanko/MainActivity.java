package com.example.mieszkanko;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mieszkanko.BottomNavigationBarFragments.ProfileFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ScheduleFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.ShoppingListFragment;
import com.example.mieszkanko.BottomNavigationBarFragments.StatisticsFragment;
import com.example.mieszkanko.Models.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public List<String> productsNameToBuyList;
    public List<String> productsDescriptionToBuyList;

    DatabaseReference mRootRef;
    DatabaseReference mShoppingListRef;

    String userIdOfThisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mShoppingListRef = mRootRef.child("shopping_list");

        Bundle bundle = getIntent().getExtras();
        userIdOfThisUser = bundle.getString("messageUserId");

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
        //hide keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mShoppingListRef.child("to_buy").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Product> list = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Product prod = ds.getValue(Product.class);
                    String name = prod.getName();
                    String description = prod.getDescription();
//                    list.add(userId + " / "  + keyId);
//                    Log.d("TAG", userId + " / " +  keyId);
                }
//                ListView listView = (ListView) findViewById(R.id.list_view);
//                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
//                listView.setAdapter(arrayAdapter);
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
