package com.example.mieszkanko.ShoppingFragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Calendar;
import java.util.Locale;

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
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
                    ((TextView)view.findViewById(R.id.productDescriptionTextView))
                            .setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    ((TextView)view.findViewById(R.id.productNameTextView))
                            .setTextColor(getResources().getColor(android.R.color.tab_indicator_text));

                } else {
                    selectedItemIndex = position;
                    for (int j = 0; j < parent.getChildCount(); j++){
                        parent.getChildAt(j).setBackgroundColor(Color.TRANSPARENT);
                        ((TextView)parent.getChildAt(j).findViewById(R.id.productNameTextView))
                                .setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                        ((TextView)parent.getChildAt(j).findViewById(R.id.productDescriptionTextView))
                                .setTextColor(getResources().getColor(android.R.color.tab_indicator_text));
                    }

                    ((TextView)view.findViewById(R.id.productDescriptionTextView)).setTextColor(Color.WHITE);
                    ((TextView)view.findViewById(R.id.productNameTextView)).setTextColor(Color.WHITE);
                    view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(selectedItemIndex == -1) {
                    Toast.makeText(getContext(), "Select product you want to buy",
                            Toast.LENGTH_SHORT).show();
                } else {


                    final View dialogView = inflater.inflate(R.layout.
                            buying_product_dialog_custom_layout, null);

                    final EditText productNameDialog = dialogView.findViewById(R.id.productNameDialogEditText);
                    final EditText priceDialog = dialogView.findViewById(R.id.priceDialogEditText);
                    final TextView datePicker = dialogView.findViewById(R.id.datePickerDialogTextView);




                    productNameDialog.setText(AccountSettings.getShoppingList().getToBuy().
                            get(selectedItemIndex).getName());

                    final Calendar myCalendar = Calendar.getInstance();
                    final String myFormat = "dd-MM-YYYY";
                    final DateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);
                    datePicker.setText(sdf.format(myCalendar.getTime()));

                    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear,
                                              int dayOfMonth) {
                            // TODO Auto-generated method stub
                            myCalendar.set(Calendar.YEAR, year);
                            myCalendar.set(Calendar.MONTH, monthOfYear);
                            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                             //In which you need put here
                            datePicker.setText(sdf.format(myCalendar.getTime()));
                        }

                    };

                    datePicker.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // TODO Auto-generated method stub
                            new DatePickerDialog(getContext(), date, myCalendar
                                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                        }
                    });


                    final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Buy product")
                        .setIcon(R.drawable.ic_shopping_basket_black_24dp)
                        .setView(dialogView)
                        .setNegativeButton(android.R.string.no, null)
                        .setPositiveButton("Buy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "You bought the product",
                                        Toast.LENGTH_SHORT).show();
                                //String productName = productNameDialog.getText().toString();
                                //String productPrice = priceDialog.getText().toString();
                                //String purchaseDate = datePicker.getText().toString();

                                //dodaj do listy zakupionych i usun z listy tobuy (index to selectedItemIndex)
                                //na koniec ustaw selectedItemIndex = -1
                            }
                        });

                    final AlertDialog dialog = builder.create();

                    priceDialog.addTextChangedListener(new TextWatcher() {

                        private void handleText() {
                            // Grab the button
                            final Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            if(priceDialog.getText().length() == 0 || productNameDialog.getText().length() == 0) {
                                okButton.setEnabled(false);
                            } else {
                                okButton.setEnabled(true);
                            }
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            handleText();

                        }
                    });
                    productNameDialog.addTextChangedListener(new TextWatcher() {

                        private void handleText() {
                            // Grab the button
                            final Button okButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                            if(productNameDialog.getText().length() == 0 || priceDialog.getText().length() == 0) {
                                okButton.setEnabled(false);
                            } else {
                                okButton.setEnabled(true);
                            }
                        }

                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            handleText();

                        }
                    });

                    dialog.show();

                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedItemIndex == -1)
                {
                    Toast.makeText(getContext(), "Select product you want to delete",
                            Toast.LENGTH_SHORT).show();
                } else
                {
                    new AlertDialog.Builder(getContext())
                            .setTitle("Delete product")
                            .setMessage("Are you sure you want to delete " + AccountSettings
                                    .getShoppingList().getToBuy()
                                    .get(selectedItemIndex).getName() + "?")
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //tutaj tylko usuwanie z bazy danych, index prduktu to
                                    // lista.size - selectedItemIndex poniewaz robie na liscie reverse wczesniej
                                    //po ustawieniu ustawić selectedItemIndex = -1
                                }
                            })
                            .setNegativeButton(android.R.string.no, null)
                            .setIcon(R.drawable.ic_delete_dialog_24dp)
                            .show();
                }
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
                            Toast.LENGTH_SHORT).show();
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

