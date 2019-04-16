package com.example.mieszkanko;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShoppingListFragment extends Fragment {


    private SectionsShoppingPageAdapter mSectionsShoppingPageAdapter;
    private ViewPager mViewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("tagus", "wchodze do on create view! w shopping");
        View view = inflater.inflate(R.layout.fragment_shopping_list, null);

        mViewPager = view.findViewById(R.id.fragment_container_shopping);
        setupViewPager(mViewPager);

        TabLayout tabLayout = view.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        return view;
    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsShoppingPageAdapter adapter = new SectionsShoppingPageAdapter(getChildFragmentManager());
        adapter.addFragment(new ToBuyFragment(), "To buy");
        adapter.addFragment(new ShoppingListHistoryFragment(), "History");
        viewPager.setAdapter(adapter);

    }


}



