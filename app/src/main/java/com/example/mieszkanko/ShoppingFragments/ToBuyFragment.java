package com.example.mieszkanko.ShoppingFragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.MainActivity;
import com.example.mieszkanko.R;

import java.util.ArrayList;
import java.util.List;

public class ToBuyFragment extends Fragment {

//    List<String> productsList;
//    List<String> descriptionList;

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

                if(productNameEdit.getText().toString().length() != 0)
                {

                    String productName = productNameEdit.getText().toString();
                    String productDescription = descriptionEdit.getText().toString();

                    if(productDescription.length() == 0) {
                        productDescription = "No description";
                    }

                    AccountSettings.addToToBuyList(productName, productDescription);
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

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return AccountSettings.getProductsNameToBuyList().size();
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
            productName.setText(AccountSettings.getProductsNameToBuyList().get(position));
            description.setText(AccountSettings.getProductsDescriptionToBuyList().get(position));

            return convertView;
        }
    }

}

