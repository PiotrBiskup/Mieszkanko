package com.example.mieszkanko.StatisticFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.Models.Schedule;
import com.example.mieszkanko.Models.User;
import com.example.mieszkanko.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleStatFragment extends Fragment {


    private GridView gridView;
    private Spinner periodsSpinner;

    private List<NickAndAmountOfCleanedUp> summarySList = new ArrayList<>();

    private List<String> spinnerList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewRoot = (ViewGroup) inflater.inflate(R.layout.fragment_stat_schedule, null);

        periodsSpinner = viewRoot.findViewById(R.id.PeriodsSpinner);
        gridView = viewRoot.findViewById(R.id.GridViewScheduleStat);

        Schedule schedule = new Schedule();

        for (User user : AccountSettings.getApartment().getRoommatesUser()){
            summarySList.add(new NickAndAmountOfCleanedUp(user.getNick()));
        }

        for(int i = 0; i < AccountSettings.getApartment().getRoommatesUser().size(); i++)
        {
            spinnerList.add(Integer.toString(i + 1));
        }


        spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, spinnerList);
        periodsSpinner.setAdapter(spinnerAdapter);

        periodsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorAccent));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });



        return viewRoot;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
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
            View customView = getLayoutInflater().inflate(R.layout.schedule_stat_custom_layout, null);



            return customView;
        }
    }

    class NickAndAmountOfCleanedUp {

        private String nick;
        private int amount = 0;

        NickAndAmountOfCleanedUp(String nick){
            this.nick = nick;
        }

        public String getNick() {
            return nick;
        }

        public int getAmount() {
            return amount;
        }

        public void addToAmount()
        {
            this.amount += 1;
        }
    }
}
