package com.example.mieszkanko.BottomNavigationBarFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mieszkanko.Adapter.SlidePagerAdapter;
import com.example.mieszkanko.R;
import com.example.mieszkanko.ScheduleFragments.CurrentScheduleFragment;
import com.example.mieszkanko.ScheduleFragments.NextScheduleFragment;
import com.example.mieszkanko.StatisticFragments.ScheduleStatFragment;
import com.example.mieszkanko.StatisticFragments.ShoppingListStatFragment;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {

    private ViewPager pager;
    private PagerAdapter pagerAdapter;
    private ImageView indicator1;
    private ImageView indicator2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_statistics, null);

        indicator1 = view.findViewById(R.id.statisticsIndicator1);
        indicator2 = view.findViewById(R.id.statisticsIndicator2);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new ScheduleStatFragment());
        fragmentList.add(new ShoppingListStatFragment());

        pager = view.findViewById(R.id.statisticsViewPager);
        pagerAdapter = new SlidePagerAdapter(getChildFragmentManager(), fragmentList);

        pager.setAdapter(pagerAdapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        indicator1.setImageResource(R.drawable.tab_indicator_selected);
                        indicator2.setImageResource(R.drawable.tab_indicator_default);
                        break;
                    case 1: {
                        indicator1.setImageResource(R.drawable.tab_indicator_default);
                        indicator2.setImageResource(R.drawable.tab_indicator_selected);
                    }


                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        return view;
    }
}
