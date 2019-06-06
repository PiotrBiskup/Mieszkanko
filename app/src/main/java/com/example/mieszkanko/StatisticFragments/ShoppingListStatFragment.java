package com.example.mieszkanko.StatisticFragments;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.GridView;
import android.widget.TextView;

import com.example.mieszkanko.AccountSettings.AccountSettings;
import com.example.mieszkanko.Models.Purchased;
import com.example.mieszkanko.Models.User;
import com.example.mieszkanko.R;

import java.security.spec.ECField;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ShoppingListStatFragment extends Fragment {

    private TextView fromDateTextView;
    private TextView toDateTextView;
    private GridView gridView;
    private CustomAdapter customAdapter = new CustomAdapter();

    private List<NickAndOutgoings> summaryList = new ArrayList<>();
    private Double totalOutgoings = 0.0;

    final private String myFormat = "dd-MM-yyyy";
    private Calendar myCalendar;
    private DateFormat dateFormat;
    private String prevFromDate;
    private String prevToDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_stat_shopping_list, null);

        fromDateTextView = view.findViewById(R.id.fromDateTextView);
        toDateTextView = view.findViewById(R.id.toDateTextView);
        gridView = view.findViewById(R.id.GridViewShoppingListStat);

        myCalendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat(myFormat, Locale.ENGLISH);

        setDefaultFromDate();
        setDefaultToDate();

        for (String nick : AccountSettings.getApartment().getFlatmates()){
            summaryList.add(new NickAndOutgoings(nick));
        }

        try{
            countSummaryList();
        } catch (Exception e)
        {
            Log.e("ShoppingListStat", e.getMessage());
        }

        gridView.setAdapter(customAdapter);

        final DatePickerDialog.OnDateSetListener toDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String thisDate = dateFormat.format(myCalendar.getTime());
                if(!thisDate.equals(prevToDate))
                {
                    toDateTextView.setText(thisDate);
                    prevToDate = thisDate;

                    try{
                        countSummaryList();
                        customAdapter.notifyDataSetChanged();

                    } catch (Exception e)
                    {
                        Log.e("ShoppingListStat", e.getMessage());
                    }
                }

            }

        };

        toDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), toDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        final DatePickerDialog.OnDateSetListener fromDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String thisDate = dateFormat.format(myCalendar.getTime());
                if(!thisDate.equals(prevFromDate))
                {
                    fromDateTextView.setText(thisDate);
                    prevFromDate = thisDate;

                    try{
                        countSummaryList();
                        customAdapter.notifyDataSetChanged();
                    } catch (Exception e)
                    {
                        Log.e("ShoppingListStat", e.getMessage());
                    }

                }

            }

        };

        fromDateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), fromDate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        1).show();

            }
        });

        return view;
    }

    private void setDefaultFromDate(){
        String thisYear = Integer.toString(myCalendar.get(Calendar.YEAR));
        String thisMonth = String.format("%02d", myCalendar.get(Calendar.MONTH) + 1);
        String fromdate = "01-" + thisMonth + "-" + thisYear;
        prevFromDate = fromdate;
        fromDateTextView.setText(fromdate);
    }

    private void setDefaultToDate(){
        String todate = dateFormat.format(myCalendar.getTime());
        prevToDate = todate;
        toDateTextView.setText(todate);
    }


    private void countSummaryList() throws ParseException {

        List<NickAndOutgoings> tempSummaryList = summaryList;
        Double tempTotalOutgoings = 0.0;

        Date date1 = dateFormat.parse(fromDateTextView.getText().toString());
        Date date2 = dateFormat.parse(toDateTextView.getText().toString());

        for (Purchased x : AccountSettings.getShoppingList().getPurchased())
        {
            Date purchaseDate = dateFormat.parse(x.getPurchaseDate());

            if(!date1.after(purchaseDate) && !date2.before(purchaseDate))
            {
                tempTotalOutgoings += x.getPrice();

                for (NickAndOutgoings i : tempSummaryList)
                {
                    if(i.getNick() == x.getBuyer())
                    {
                        i.addToOutgoings(x.getPrice());
                        break;
                    }
                }
            }
        }

        this.totalOutgoings = tempTotalOutgoings;
        this.summaryList = tempSummaryList;
    }

    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return summaryList.size() + 1;
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
            View customView = getLayoutInflater().inflate(R.layout.shopping_list_stat_cutom_layout, null);

            TextView nick = customView.findViewById(R.id.nickSLStatTextView);
            TextView outgoings = customView.findViewById(R.id.outgoingsSLStatTextView);

            if(position == 0)
            {
                customView.setBackground(getResources().getDrawable(R.drawable.shadow_primary_color));
                nick.setText("In total");
                outgoings.setText(String.format("%.2f", totalOutgoings));
                outgoings.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            } else {
                nick.setText(summaryList.get(position - 1).getNick());
                outgoings.setText(String.format ("%.2f", summaryList.get(position - 1).getOutgoings()) + " PLN");
            }

            return customView;
        }
    }

    class NickAndOutgoings{

        private String nick;
        private Double outgoings = 0.0;

        NickAndOutgoings(String nick)
        {
            this.nick = nick;
        }

        public String getNick() {
            return nick;
        }

        public Double getOutgoings() {
            return outgoings;
        }

        public void addToOutgoings(Double money){
            this.outgoings += money;
        }
    }

}
