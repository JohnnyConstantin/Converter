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

import com.example.converter.HttpClient;
import com.example.converter.MainActivity;
import com.example.converter.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Fragment of a page with history
 * @author Vadim
 */
public class HistoryFragment extends ListFragment {

    final ArrayList<ArrayList<String>> dealings = new ArrayList<ArrayList<String>>();
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

        JSONArray data = null;

        try {
            data = new JSONArray(getHistory(userId));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        System.out.println(data);

        for(int i =0; i<data.length(); i++){
            try {
                JSONObject json = new JSONObject(data.get(i).toString());
                ArrayList<String> myList = new ArrayList<>();
                myList.add(json.get("currency1").toString());
                myList.add(json.get("currency2").toString());
                myList.add(json.get("value1").toString());
                myList.add(json.get("value2").toString());
                myList.add(json.get("date").toString());
                System.out.println(myList.get(0) + "\n");
                dealings.add(myList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        MyListAdapter myListAdapter = new MyListAdapter(getActivity(),
                R.layout.listfragment_row, dealings);
        setListAdapter(myListAdapter);





//        try {
//            JSONObject json = new JSONObject(data.get(0).toString());
//            String id = json.get("user_id").toString();
//            String from = json.get("currency1").toString();
//            String to = json.get("currency2").toString();
//
//            System.out.println(id+'\n'+from+'\n'+to);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

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
            TextView value1TextView = (TextView) row.findViewById(R.id.currencies);
            TextView value2TextView = (TextView) row.findViewById(R.id.values);
//            TextView curr1TextView = (TextView) row.findViewById(R.id.currency1);
//            TextView curr2TextView = (TextView) row.findViewById(R.id.currency2);

            dateTextView.setText(dealings.get(position).get(4).substring(0,9));
            value1TextView.setText(dealings.get(position).get(2));
            value2TextView.setText(dealings.get(position).get(0));
//            curr1TextView.setText(dealings.get(position).get(0));


            return row;
        }

    }

    /**
     *
     * @param date
     * @param cur1
     * @param cur2
     * @param userId
     * @return
     */
    public String searchHistory(String date, String cur1, String cur2, String userId){
        HttpClient c = new HttpClient();
        Map<String, String> data = new HashMap<>();
        data.put("date", date);
        data.put("currency1", cur1);
        data.put("currency2", cur2);
        data.put("userId", userId);
        return c.post("/search", data.toString());
    }

    /**
     *
     * @param userId
     * @return
     */
    public String getHistory(String userId) throws ExecutionException, InterruptedException {
        HttpClient c = new HttpClient();
        Map<String, String> data = new HashMap<>();
        data.put("userId", userId);
        return c.execute("POST", "/converter/history", data.toString()).get();
    }
}

//Тестовый json
//[{"id":45,"user_id":36,"currency1":"AUD(Австралийский доллар)","currency2":"GBP(Фунт стерлингов Соединенного королевства)","value1":31.0,"value2":17.0,"date":"2021-05-31T00:00:00.000+00:00"},{"id":46,"user_id":36,"currency1":"AUD(Австралийский доллар)","currency2":"GBP(Фунт стерлингов Соединенного королевства)","value1":30000.0,"value2":16372.0,"date":"2021-05-31T00:00:00.000+00:00"}]
