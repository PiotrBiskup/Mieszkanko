package com.example.mieszkanko.ScheduleFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mieszkanko.R;

import java.util.ArrayList;
import java.util.List;

public class NextScheduleFragment extends Fragment {

    private GridView gridView;
    private List<String> nicks = new ArrayList<>();
    private List<String> rooms = new ArrayList<>();
    private List<Boolean> status = new ArrayList<>();
    private TextView title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_next_schedule, null);
        title = rootView.findViewById(R.id.NextScheduleID);

        nicks.add("Piotr");
        nicks.add("Czarek");
        nicks.add("Jedrzej");
        nicks.add("Piotr");
        nicks.add("Czarek");
        nicks.add("Jedrzej");

        rooms.add("kitchen");
        rooms.add("bathroom");
        rooms.add("living room");
        rooms.add("kitchen");
        rooms.add("bathroom");
        rooms.add("living room");

        status.add(true);
        status.add(false);
        status.add(true);
        status.add(true);
        status.add(false);
        status.add(true);

        gridView = rootView.findViewById(R.id.GridViewNextSchedule);
        CustomAdapter customAdapter = new CustomAdapter();
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
}
