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
import com.example.mieszkanko.Models.Roomsschedule;
import com.example.mieszkanko.Models.Schedule;
import com.example.mieszkanko.Models.User;
import com.example.mieszkanko.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleStatFragment extends Fragment {


    private GridView gridView;
    private CustomAdapter customAdapter = new CustomAdapter();
    private Spinner periodsSpinner;


    private List<NickAndAmountOfCleanedUp> summarySList = new ArrayList<>();
    private int totalAppearance;
    private int totalAmounts;

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

        for(int i = 0; i < AccountSettings.getSchedule().getPeriodList().size() - 1; i++)
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
                countStatistics();
                customAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        gridView.setAdapter(customAdapter);

        return viewRoot;
    }

    class CustomAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return summarySList.size() + 1;
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

            TextView nickTV = customView.findViewById(R.id.nickSStatTextView);
            TextView statTV = customView.findViewById(R.id.ratioSStatTextView);

            if(getCount() == 1)
            {

                nickTV.setText("No previous periods");
                statTV.setText("");

            } else {

                Double ratio = 0.0;

                if (position == 0)
                {
                    customView.setBackground(getResources().getDrawable(R.drawable.shadow_primary_color));
                    nickTV.setText("In total");
                    ratio = Double.valueOf(totalAmounts) / Double.valueOf(totalAppearance);
                    statTV.setText(Integer.toString(totalAmounts) + " / " + Integer.toString(totalAppearance)
                            + "  ->  " + String.format ("%.2f", (ratio * 100)) + "%");

                } else {

                    String nick = summarySList.get(position - 1).getNick();
                    int usr_amounts = summarySList.get(position - 1).getAmount();
                    int usr_appearance = summarySList.get(position - 1).getApperance();
                    ratio = Double.valueOf(usr_amounts) / Double.valueOf(usr_appearance);

                    nickTV.setText(nick);
                    statTV.setText(Integer.toString(usr_amounts) + " / " + Integer.toString(usr_appearance)
                            + "  ->  " + String.format ("%.2f", (ratio * 100)) + "%");


                }
            }




            return customView;
        }
    }

    private void countStatistics()
    {
        clearUsersAmounts();
        int tempTotalAppearance = 0;
        int tempTotalAmounts = 0;
        List<NickAndAmountOfCleanedUp> tempList = summarySList;
        int numberOfPeriods = periodsSpinner.getSelectedItemPosition() + 1;

        for (int i = AccountSettings.getSchedule().getPeriodList().size() - 2;
             i >= AccountSettings.getSchedule().getPeriodList().size() - (numberOfPeriods + 1);
             i--)
        {

            for(Roomsschedule room : AccountSettings.getSchedule().getPeriodList().get(i).getRoomsschedule())
            {

                String room_user_nick = AccountSettings.getApartment().findUserByKey(room.getUser());

                for (NickAndAmountOfCleanedUp user : tempList){

                    if(user.getNick().equals(room_user_nick))
                    {
                        user.addToApperance();
                        tempTotalAppearance += 1;

                        if(room.getStatus())
                        {
                            user.addToAmount();
                            tempTotalAmounts += 1;
                        }

                        break;
                    }
                }
            }

        }

        summarySList = tempList;
        totalAmounts = tempTotalAmounts;
        totalAppearance = tempTotalAppearance;

    }

    private void clearUsersAmounts(){

        for (NickAndAmountOfCleanedUp x: summarySList){
            x.clearAmount();
        }
    }

    class NickAndAmountOfCleanedUp {

        private String nick;
        private int amount = 0;
        private int appearance = 0;

        NickAndAmountOfCleanedUp(String nick){
            this.nick = nick;
        }

        public String getNick() {
            return nick;
        }

        public int getAmount() {
            return amount;
        }

        public int getApperance() {
            return appearance;
        }

        public void addToAmount()
        {
            this.amount += 1;
        }

        public void addToApperance(){
            this.appearance += 1;
        }

        public void clearAmount(){
            this.amount = 0;
            this.appearance = 0;
        }
    }
}
