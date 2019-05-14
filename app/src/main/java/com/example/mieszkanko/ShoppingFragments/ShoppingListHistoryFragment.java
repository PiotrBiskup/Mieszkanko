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
import android.widget.ListView;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.R;

import org.w3c.dom.Text;

public class ShoppingListHistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shopping_list_history, null);

        ListView purchasedProductsList = view.findViewById(R.id.purchasedProductsListView);
        CustomAdapterPurchasedProductsList customAdapterPurchasedProductsList = new CustomAdapterPurchasedProductsList();

        purchasedProductsList.setAdapter(customAdapterPurchasedProductsList);

        return view;
    }

    class CustomAdapterPurchasedProductsList extends BaseAdapter {

        @Override
        public int getCount() {
            return AccountSettings.getHistoryProductsName().size();
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

            convertView = getLayoutInflater().inflate(R.layout.history_shopping_list_custom_layout, null);
            TextView boughtProductName = convertView.findViewById(R.id.BoughtProductNameTextView);
            TextView productPrice = convertView.findViewById(R.id.BoughtProductPriceTextView);
            TextView purchaseDate = convertView.findViewById(R.id.DateOfPurchaseTextView);
            TextView buyer = convertView.findViewById(R.id.BuyerTextView);

            boughtProductName.setText(AccountSettings.getHistoryProductsName().get(position));
            productPrice.setText(String.format ("%.2f", AccountSettings.getHistoryProductsPrice().get(position)) + " PLN");
            purchaseDate.setText(AccountSettings.getHistoryProductsDate().get(position).toString());
            buyer.setText(AccountSettings.getHistoryProductsBuyer().get(position));


            return convertView;
        }
    }
}
