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
                myList.add(json.get("currency1").toString().split("\\(")[0]);
                myList.add(json.get("currency2").toString().split("\\(")[0]);
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
            TextView currencies = (TextView) row.findViewById(R.id.currencies);
            TextView values = (TextView) row.findViewById(R.id.values);

            dateTextView.setText(dealings.get(position).get(4).substring(0,10));
            String curs = dealings.get(position).get(0) + "/" + dealings.get(position).get(1);
            String vals = dealings.get(position).get(2) + "/" + dealings.get(position).get(3);
            currencies.setText(curs);
            values.setText(vals);


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

//�������� json
//[{"id":45,"user_id":36,"currency1":"AUD(������������� ������)","currency2":"GBP(���� ���������� ������������ �����������)","value1":31.0,"value2":17.0,"date":"2021-05-31T00:00:00.000+00:00"},{"id":46,"user_id":36,"currency1":"AUD(������������� ������)","currency2":"GBP(���� ���������� ������������ �����������)","value1":30000.0,"value2":16372.0,"date":"2021-05-31T00:00:00.000+00:00"}]
