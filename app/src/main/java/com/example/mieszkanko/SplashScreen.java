package com.example.mieszkanko;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.BottomNavigationBarFragments.ScheduleFragment;
import com.example.mieszkanko.Models.Apartment;
import com.example.mieszkanko.Models.Period;
import com.example.mieszkanko.Models.Schedule;
import com.example.mieszkanko.Models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SplashScreen extends AppCompatActivity {

    DatabaseReference mRootRef;
    DatabaseReference mShoppingListRef;
    String TAG = "Splash screen";
    String userIdOfThisUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRootRef = FirebaseDatabase.getInstance().getReference();
        mShoppingListRef = mRootRef.child("shopping_list");

        setContentView(R.layout.activity_splash_screen);
        Bundle bundle = getIntent().getExtras();
        userIdOfThisUser = bundle.getString("messageUserId");
        mRootRef.child("users").child(userIdOfThisUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                AccountSettings.setUser(currentUser);
                Log.w(TAG, "++++++++++++ NNNNNNIIIIIIIICCCCCCCCKKKKKKK  ++++++++ " +  AccountSettings.getUser().getNick());
                Log.w(TAG, "++++++++++++ AAAAAAAAAPPPPARTMENT FROM UUUUUSER  ++++++++ " +  AccountSettings.getUser().getApartment());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {

                    sleep(2000);
                    getApartmentFromDB();
                    sleep(2000);




                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        myThread.start();
    }

    private void getUserFromDB(String userId) {

        mRootRef.child("users").child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User userek = dataSnapshot.getValue(User.class);
                userek.setKey(dataSnapshot.getKey());
                AccountSettings.getApartment().addRoomatesUser(userek);
                Log.w(TAG, "++++++++++++ add new user  ++++++++ " +  userek.getNick());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }



    private void getScheduleFromDB(final String apartmentId){
        mRootRef.child("schedule").child(apartmentId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User userek = dataSnapshot.getValue(User.class);
                Schedule schedule = new Schedule();
                List<Period> periodList= new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Period period = ds.getValue(Period.class);
                    periodList.add(period);
                }
                schedule.setPeriodList(periodList);
                Calendar c = Calendar.getInstance();
                if(schedule.getPeriodList().isEmpty())
                {
                    Period x = schedule.choosePersonToClean(AccountSettings.getApartment(),schedule);
                    DatabaseReference Ref = mRootRef.child("schedule").child(apartmentId).push();
                    Ref.setValue(x);
                }
                else if (schedule.getPeriodList().get(schedule.getPeriodList().size() - 1).getTimestamp() < c.getTimeInMillis()) {
                        Period x = schedule.choosePersonToClean(AccountSettings.getApartment(), schedule);
                        DatabaseReference Ref = mRootRef.child("schedule").child(apartmentId).push();
                        Ref.setValue(x);
                    }
                    AccountSettings.setSchedule(schedule);

                    Log.w(TAG, "++++++++++++ add new Schedule  ++++++++ " + AccountSettings.getSchedule().getPeriodList().size());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }

    private void getApartmentFromDB() {
        final String apartmentId = AccountSettings.getUser().getApartment();
        Log.w(TAG, "---------------------- AAAAAAAAAPPPPARTMENTTTTTTTTT  ------------- " +  apartmentId);

        mRootRef.child("apartments").child(apartmentId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Apartment currentApartment = dataSnapshot.getValue(Apartment.class);
                AccountSettings.setApartment(currentApartment);
                Log.w(TAG, "++++++++++++ apartment  ++++++++ " +  AccountSettings.getApartment().getName());
                for(String idik : AccountSettings.getApartment().getRoommates()) {
                    Log.w(TAG, "++++++++++++ roommie  ++++++++ " +  idik);
                    getUserFromDB(idik);
                }
                getScheduleFromDB(apartmentId);

                try {
                    Log.w(TAG, "TRY START 1" );
                    FragmentManager fm = getSupportFragmentManager();
                    Log.w(TAG, "TRY START 2" );
                    ScheduleFragment fragment = (ScheduleFragment) fm.findFragmentById(R.id.fragment_container);
                    Log.w(TAG, "TRY START 3" );
                    fragment.getNextScheduleFragment().setLists();
                    Log.w(TAG, "TRY START 4" );
                    fragment.getNextScheduleFragment().notifyAdapter();
                    Log.w(TAG, "TRY END " );
                }
                catch (Exception e)
                {
                    Log.w(TAG, "TRY EXXXXXXX " + e.getMessage());

                }
                Intent intent = new Intent(SplashScreen.this,MainActivity.class);
                intent.putExtra("messageUserId", userIdOfThisUser);
                startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }
}
