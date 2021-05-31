package com.example.converter.ui.history;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.fragment.app.ListFragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.example.converter.HttpClient;
import com.example.converter.MainActivity;
import com.example.converter.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Фрагмент страницы с историей
 * @author Vadim
 */
public class HistoryFragment extends ListFragment {

    final ArrayList<ArrayList<String>> dealings = new ArrayList<>();
    //        dealings.add(new ArrayList<String>());
//        dealings.get(0).add("2021-02-25");
//        dealings.get(0).add("USDT/RUB");
//        dealings.get(0).add("10/754");
//        dealings.add(new ArrayList<String>());
//        dealings.get(1).add("2021-02-25");
//        dealings.get(1).add("USDT/RUB");
//        dealings.get(1).add("1000/75400");

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MainActivity m = ((MainActivity) getActivity());
        String login = m.getLogin();
        String userId = m.getUserId();


        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.listfragment_row, dealings);
        setListAdapter(myListAdapter);


    }

    public class MyListAdapter extends ArrayAdapter<ArrayList<String>> {

        private Context mContext;

        public MyListAdapter(Context context, int textViewResourceId,
                             ArrayList<ArrayList<String>> objects) {
            super(context, textViewResourceId, objects);
            mContext = context;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("ViewHolder") View row = inflater.inflate(R.layout.listfragment_row, parent,
                    false);

            TextView dateTextView = (TextView) row.findViewById(R.id.date);
            TextView currenciesTextView = (TextView) row.findViewById(R.id.currencies);
            TextView valuesTextView = (TextView) row.findViewById(R.id.values);

            dateTextView.setText(dealings.get(position).get(0));
            currenciesTextView.setText(dealings.get(position).get(1));
            valuesTextView.setText(dealings.get(position).get(2));

            return row;
        }

    }

    public String searchHistory(String date, String cur1, String cur2, String userId){
        HttpClient c = new HttpClient();
        Map<String, String> data = new HashMap<>();
        data.put("date", date);
        data.put("currency1", cur1);
        data.put("currency2", cur2);
        data.put("userId", userId);
        return c.post("/search", data.toString());
    }

    public String getHistory(String userId){
        HttpClient c = new HttpClient();
        Map<String, String> data = new HashMap<>();
        data.put("userId", userId);
        return c.post("/history", data.toString());
    }
}
