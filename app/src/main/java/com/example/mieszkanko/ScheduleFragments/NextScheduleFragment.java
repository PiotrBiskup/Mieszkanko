package com.example.mieszkanko.ScheduleFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.BottomNavigationBarFragments.ScheduleFragment;
import com.example.mieszkanko.Models.Period;
import com.example.mieszkanko.Models.Roomsschedule;
import com.example.mieszkanko.Models.Schedule;
import com.example.mieszkanko.Models.User;
import com.example.mieszkanko.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class NextScheduleFragment extends Fragment {
    private GridView gridView;
    private List<String> nicks = new ArrayList<>();
    private List<String> rooms = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();
    private TextView title;
    CustomAdapter customAdapter;
    LayoutInflater inf;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inf = inflater;
        Period period = AccountSettings.getSchedule().choosePersonToClean(AccountSettings.getApartment(),AccountSettings.getSchedule());
        for(Roomsschedule roomsschedule : period.getRoomsschedule())
        {
            Log.w("MOJE", "USER 1POBIERANYff " + roomsschedule.getUser());
            //addUserFromDatabase(roomsschedule.getUser());
            nicks.add(AccountSettings.getApartment().findUserByKey(roomsschedule.getUser()));
            Log.w("MOJE", "USER 1POBIERANYff " + roomsschedule.getUser());
            rooms.add(roomsschedule.getRoom());
            Log.w("MOJE", "USER 1POBIERANYff " + roomsschedule.getUser());
            status.add(roomsschedule.getStatus());

        }
//         List<String> nicks1 = new ArrayList<>();
//        for(int i=0;i<nicks.size();i++)
//        {
//            if(AccountSettings.getUser().getNick().equals(nicks.get(i))){
//               nicks1.add(nicks.get(i));
//            }
//        }
//        for(int i=0;i<nicks.size();i++)
//        {if(!AccountSettings.getUser().getNick().equals(nicks.get(i))){
//            nicks1.add(nicks.get(i));
//        }
//
//        }
//        nicks=nicks1;
        for(int i = 0; i < nicks.size(); i++){
            if(AccountSettings.getUser().getNick().equals(nicks.get(i))){
                Collections.swap(nicks, 0, i);
                Collections.swap(rooms, 0, i);
                Collections.swap(status, 0, i);
            }
        }
        Log.w("MOJE", "Kriejt" );
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_next_schedule, null);
        title = rootView.findViewById(R.id.NextScheduleID);
        gridView = rootView.findViewById(R.id.GridViewNextSchedule);
        customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);


        return rootView;
    }

    private class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return nicks.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View customView = getLayoutInflater().inflate(R.layout.schedule_row, null);

            TextView nick = customView.findViewById(R.id.TextViewNickSchedule);
            TextView room = customView.findViewById(R.id.TextViewRoomSchedule);
            TextView roomStatus = customView.findViewById(R.id.TextViewCleanStatus);
            FloatingActionButton button = customView.findViewById(R.id.ScheduleFloatingActionButton);

            button.hide();

            if(position == 0) {

                customView.setBackground(getResources().getDrawable(R.drawable.shadow_primary_color));

            }

            nick.setText(nicks.get(position));
            if(rooms.get(position)== null)
            {
                room.setText(getString(R.string.noRoom));
            } else
            {
                room.setText(rooms.get(position));
            }

            if(status.get(position) == null){
                roomStatus.setText("");
            } else if (status.get(position)){
                button.setImageResource(R.drawable.ic_clear_white_24dp);
                roomStatus.setText(getString(R.string.cleanedUp));
                roomStatus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            } else if (!status.get(position)){
                button.setImageResource(R.drawable.ic_done_white_24dp);
                roomStatus.setText(getString(R.string.notCleanedUp));
                roomStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));

            }

            return customView;
        }

    }

    public void notifyAdapter()
    {
        customAdapter.notifyDataSetChanged();
    }

    public void setLists() {
//        nicks.clear();
//        rooms.clear();
//        status.clear();

//        gridView.setAdapter(null);

        Period period = AccountSettings.getSchedule().choosePersonToClean(AccountSettings.getApartment(),AccountSettings.getSchedule());
        for(Roomsschedule roomsschedule : period.getRoomsschedule())
        {
            Log.w("MOJE", "USER POBIERANYff " + roomsschedule.getUser());
            //addUserFromDatabase(roomsschedule.getUser());
            nicks.add(AccountSettings.getApartment().findUserByKey(roomsschedule.getUser()));
            Log.w("MOJE", "USER POBIERANYff " + roomsschedule.getUser());
            rooms.add(roomsschedule.getRoom());
            Log.w("MOJE", "USER POBIERANYff " + roomsschedule.getUser());
            status.add(roomsschedule.getStatus());

        }

//        nicks.add(0, "Marek");
//        rooms.add(0, "laznia");
//        status.add(0, false);


        ViewGroup rootView = (ViewGroup) inf.inflate(R.layout.fragment_next_schedule, null);
        title = rootView.findViewById(R.id.NextScheduleID);
        gridView = rootView.findViewById(R.id.GridViewNextSchedule);
        customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
    }
}
