package com.example.mieszkanko.ShoppingFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.MainActivity;
import com.example.mieszkanko.Models.Product;
import com.example.mieszkanko.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ToBuyFragment extends Fragment {

//    List<String> productsList;
//    List<String> descriptionList;

    EditText productNameEdit;
    EditText descriptionEdit;
    ImageView addProductImage;
    FloatingActionButton buyButton;
    FloatingActionButton deleteButton;

    DatabaseReference mRootRef;
    DatabaseReference mShoppingListRef;

    CustomAdapter customAdapter;
    ListView toBuyListView;

    int selectedItemIndex;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        selectedItemIndex = -1;

        View view = inflater.inflate(R.layout.fragment_shopping_list_to_buy, null);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mShoppingListRef = mRootRef.child("shopping_list");

        buyButton = view.findViewById(R.id.floatingButtonBuy);
        deleteButton = view.findViewById(R.id.floatingButtonDelete);
        productNameEdit = view.findViewById(R.id.productNameEditText);
        descriptionEdit = view.findViewById(R.id.descriptionEditText);
        addProductImage = view.findViewById(R.id.addProductImageView);

        toBuyListView = view.findViewById(R.id.toBuyListView);

        customAdapter = new CustomAdapter();
        toBuyListView.setAdapter(customAdapter);

        toBuyListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == selectedItemIndex) {
                    selectedItemIndex = -1;
                    view.setBackgroundColor(Color.TRANSPARENT);
                    ((TextView)view.findViewById(R.id.productDescriptionTextView)).setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ((TextView)view.findViewById(R.id.productNameTextView)).setTextColor(getResources().getColor(android.R.color.tab_indicator_text));

                } else {
                    selectedItemIndex = position;
                    for (int j = 0; j < parent.getChildCount(); j++){
                        parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                        ((TextView)parent.getChildAt(j).findViewById(R.id.productNameTextView)).setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                        ((TextView)parent.getChildAt(j).findViewById(R.id.productDescriptionTextView)).setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    }

                    ((TextView)view.findViewById(R.id.productDescriptionTextView)).setTextColor(Color.WHITE);
                    ((TextView)view.findViewById(R.id.productNameTextView)).setTextColor(Color.WHITE);
                    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getContext(), "buy: " + selectedItemIndex, Toast.LENGTH_SHORT).show();
                //tutaj dodawanie do listy kupionych history
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "delete: " + selectedItemIndex, Toast.LENGTH_SHORT).show();
                //tutaj tylko usuwanie
            }
        });



        addProductImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(productNameEdit.getText().toString().length() != 0)
                {

                    String productName = productNameEdit.getText().toString();
                    String productDescription = descriptionEdit.getText().toString();

                    if(productDescription.length() == 0) {
                        productDescription = "No description";
                    }

                    Product product = new Product(productName, productDescription);
                    DatabaseReference newRef = mShoppingListRef.child("to_buy").push();
                    newRef.setValue(product);


                    descriptionEdit.getText().clear();
                    productNameEdit.getText().clear();

                } else {
                    Toast.makeText(getActivity(), "Enter product name",
                            Toast.LENGTH_LONG).show();
                }


                ((MainActivity)getActivity()).hideKeyboard();


            }
        });

        return view;
    }

    public void notifyAdapter()
    {
        customAdapter.notifyDataSetChanged();
    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return AccountSettings.getShoppingList().getToBuy().size();
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
            convertView = getLayoutInflater().inflate(R.layout.to_buy_list_custom_layout, null);
            TextView productName = convertView.findViewById(R.id.productNameTextView);
            TextView description = convertView.findViewById(R.id.productDescriptionTextView);
            productName.setText(AccountSettings.getShoppingList().getToBuy().get(position).getName());
            description.setText(AccountSettings.getShoppingList().getToBuy().get(position).getDescription());

            return convertView;
        }
    }

}

