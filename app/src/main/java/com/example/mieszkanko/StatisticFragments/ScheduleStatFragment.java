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
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mieszkanko.R;

import java.util.ArrayList;
import java.util.List;

public class ScheduleStatFragment extends Fragment {


    private GridView gridView;
    private Spinner periodsSpinner;

    private List<String> tempList = new ArrayList<>();
    private ArrayAdapter<String> spinnerAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewRoot = (ViewGroup) inflater.inflate(R.layout.fragment_stat_schedule, null);

        periodsSpinner = viewRoot.findViewById(R.id.PeriodsSpinner);

        tempList.add("1");
        tempList.add("2");
        tempList.add("3");
        tempList.add("4");


        spinnerAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, tempList);
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
}
