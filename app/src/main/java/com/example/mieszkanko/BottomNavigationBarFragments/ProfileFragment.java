package com.example.mieszkanko.BottomNavigationBarFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.MainActivity;
import com.example.mieszkanko.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ArrayAdapter<String> userAdapter;
    private ArrayAdapter<String> roomsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, null);

        EditText nickname = view.findViewById(R.id.nickNameEditText);
        TextView flatname = view.findViewById(R.id.textViewFlatName);
        ListView flatmates = view.findViewById(R.id.listViewFlatMates);
        ListView rooms = view.findViewById(R.id.listViewRooms);
        EditText interval = view.findViewById(R.id.textViewInterval);

        ArrayList<String> dupa = new ArrayList<>();
        dupa.add("troll");
        dupa.add("kon");

        userAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, dupa );
        flatmates.setAdapter(userAdapter);

        rooms.setAdapter(userAdapter);


        return view;
    }


}
