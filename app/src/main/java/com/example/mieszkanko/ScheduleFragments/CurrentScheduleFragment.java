package com.example.mieszkanko.ScheduleFragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.Models.Period;
import com.example.mieszkanko.Models.Roomsschedule;
import com.example.mieszkanko.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.collection.LLRBNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrentScheduleFragment extends Fragment {
    DatabaseReference mRootRef;
    private GridView gridView;
    private List<String> nicks = new ArrayList<>();
    private List<String> rooms = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootRef = FirebaseDatabase.getInstance().getReference();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_current_schedule, null);

        Period period = AccountSettings.getSchedule().getPeriodList().get(AccountSettings.getSchedule().getPeriodList().size()-1);
        for(Roomsschedule roomsschedule : period.getRoomsschedule())
        {
            Log.w("MOJE", "USER 111POBIERANYff " + roomsschedule.getUser());
            //addUserFromDatabase(roomsschedule.getUser());
            nicks.add(AccountSettings.getApartment().findUserByKey(roomsschedule.getUser()));
            Log.w("MOJE", "USER 111POBIERANYff " + roomsschedule.getUser());
            rooms.add(roomsschedule.getRoom());
            Log.w("MOJE", "USER 111POBIERANYff " + roomsschedule.getUser());
            status.add(roomsschedule.getStatus());

        }

        for(int i = 0; i < nicks.size(); i++){
            if(AccountSettings.getUser().getNick().equals(nicks.get(i))){
                Collections.swap(nicks, 0, i);
                Collections.swap(rooms, 0, i);
                Collections.swap(status, 0, i);
            }
        }

        gridView = rootView.findViewById(R.id.GridViewCurrentSchedule);
        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);

        return rootView;
    }

    private class CustomAdapter extends BaseAdapter{

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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View customView = getLayoutInflater().inflate(R.layout.schedule_row, null);

            final TextView nick = customView.findViewById(R.id.TextViewNickSchedule);
            final TextView room = customView.findViewById(R.id.TextViewRoomSchedule);
            final TextView roomStatus = customView.findViewById(R.id.TextViewCleanStatus);
            final FloatingActionButton button = customView.findViewById(R.id.ScheduleFloatingActionButton);

            if(position != 0) {
                button.hide();

            } else {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    Period currentPeriod = AccountSettings.getSchedule().getPeriodList().get(AccountSettings.getSchedule().getPeriodList().size()-1);
                    String periodKey = currentPeriod.getKey();
                    String apartmentId = AccountSettings.getUser().getApartment();
                    int userIndex = AccountSettings.getSchedule().findIndexOfUserInLastPeriod(AccountSettings.getUser().getKey());
                    List<Roomsschedule> rs = currentPeriod.getRoomsschedule();
                    Roomsschedule newState = rs.get(userIndex);
                    newState.setStatus(true);
                    rs.set(userIndex, newState);

                    if (!status.get(position)) {
                        button.setImageResource(R.drawable.ic_clear_white_24dp);
                        roomStatus.setText(getString(R.string.cleanedUp));
                        roomStatus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                        status.set(position, true);
                        newState.setStatus(true);
                        rs.set(userIndex, newState);
                        mRootRef.child("schedule").child(apartmentId).child(periodKey).child("roomsschedule").setValue(rs);
                    } else
                    {
                        button.setImageResource(R.drawable.ic_done_white_24dp);
                        roomStatus.setText(getString(R.string.notCleanedUp));
                        roomStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));
                        status.set(position, false);
                        newState.setStatus(false);
                        rs.set(userIndex, newState);
                        mRootRef.child("schedule").child(apartmentId).child(periodKey).child("roomsschedule").setValue(rs);
                    }
                    }
                });
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
//                roomStatus.setTextColor(Color.GREEN);
                roomStatus.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            } else if (!status.get(position)){
                button.setImageResource(R.drawable.ic_done_white_24dp);
                roomStatus.setText(getString(R.string.notCleanedUp));
                roomStatus.setTextColor(getResources().getColor(android.R.color.holo_red_light));
            }


            return customView;
        }
    }

}
