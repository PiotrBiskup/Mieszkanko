package com.example.mieszkanko.BottomNavigationBarFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.MainActivity;
import com.example.mieszkanko.Models.Room;
import com.example.mieszkanko.Models.User;
import com.example.mieszkanko.R;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    private ArrayAdapter<String> userAdapter;
    private ArrayAdapter<String> roomsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, null);

        ScrollView scrollview = view.findViewById(R.id.scrollViewProfile);
        EditText nickname = view.findViewById(R.id.nickNameEditText);
        TextView flatname = view.findViewById(R.id.textViewFlatName);
        ListView flatmates = view.findViewById(R.id.listViewFlatMates);
        ListView rooms = view.findViewById(R.id.listViewRooms);

        ArrayList<String> userNicknames = new ArrayList<>();
        for(User userek : AccountSettings.getApartment().getRoommatesUser()) {
            userNicknames.add(userek.getNick());
        }
        userAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, userNicknames );
        flatmates.setAdapter(userAdapter);

        ArrayList<String> roomNames = new ArrayList<>();
        for(Room roomik: AccountSettings.getApartment().getRooms()) {
            roomNames.add(roomik.getName());
        }
        roomsAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, roomNames );
        rooms.setAdapter(roomsAdapter);

        expandListViewHeight(flatmates);
        expandListViewHeight(rooms);

        flatname.setText(AccountSettings.getApartment().getName());
        nickname.setText(AccountSettings.getUser().getNick());

        return view;
    }

    public static void expandListViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        listView.measure(0, 0);
        params.height = listView.getMeasuredHeight() * listAdapter.getCount() + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}
