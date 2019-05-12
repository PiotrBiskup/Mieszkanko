package com.example.mieszkanko.ShoppingFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mieszkanko.R;

public class ToBuyFragment extends Fragment {

    String[] PRODUCTS = {"Papier toaletowy", "Płyn do mycia naczyń", "Płyn do mycia okien", "Kret",
    "olej"};

    String[] DESCRIPTIONS = {"opis papier toaletowy", "opis płyn do mycia naczyń", "opis płyn do mycia okien", "opis kret",
            "opis olej"};

    EditText productNameEdit;
    EditText descriptionEdit;
    ImageView addProductImage;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shopping_list_to_buy, null);

        productNameEdit = view.findViewById(R.id.productNameEditText);
        descriptionEdit = view.findViewById(R.id.descriptionEditText);
        addProductImage = view.findViewById(R.id.addProductImageView);

        ListView toBuyListView = view.findViewById(R.id.toBuyListView);

        CustomAdapter customAdapter = new CustomAdapter();
        toBuyListView.setAdapter(customAdapter);

        addProductImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // productNameEdit.getText(); - nazwa produktu ktory dodajemy do bazy
                // descriptionEdit.getText(); - opis produktu ktory dodajemy do bazy
                //dodawanie do bazy i odswiezenie listy

            }
        });

        return view;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return PRODUCTS.length;
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
            productName.setText(PRODUCTS[position]);
            description.setText(DESCRIPTIONS[position]);

            return convertView;
        }
    }

}

