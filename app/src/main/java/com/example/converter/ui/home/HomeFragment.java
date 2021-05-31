package com.example.converter.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.converter.HttpClient;
import com.example.converter.MainActivity;
import com.example.converter.R;
import com.example.converter.tools.slider.SliderAdapter;
import com.example.converter.tools.slider.SliderItem;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.concurrent.ExecutionException;


/**
 * Fragment of the home page
 * @author Vadim
 */
public class HomeFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_home, container, false);
        NavController navController = NavHostFragment.findNavController(this);


        MainActivity m = ((MainActivity) getActivity());
        String login = m.getLogin();
        String userId = m.getUserId();

        sliderSetup(fragmentLayout);
        updateCurrencies(fragmentLayout);


        return fragmentLayout;
    }

    /**
     *
     * @return
     */
    public String getCurrencies() {
        HttpClient c = new HttpClient();
        try {
            String response = c.execute("GET", "/getAllCurrencies").get();
            c.cancel(true);
            return response;
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param view
     */
    public void updateCurrencies(View view){
        String response = getCurrencies();
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(response);
            setCurInfo(10, jsonArray, (TextView) view.findViewById(R.id.info_text11), (TextView) view.findViewById(R.id.info_text12));// 10
            setCurInfo(11, jsonArray, (TextView) view.findViewById(R.id.info_text21), (TextView) view.findViewById(R.id.info_text22));// 11
            setCurInfo(0, jsonArray, (TextView) view.findViewById(R.id.info_text31), (TextView) view.findViewById(R.id.info_text32));// 0
            setCurInfo(2, jsonArray, (TextView) view.findViewById(R.id.info_text41), (TextView) view.findViewById(R.id.info_text42));// 2
            setCurInfo(33, jsonArray, (TextView) view.findViewById(R.id.info_text51), (TextView) view.findViewById(R.id.info_text52));// 33
            setCurInfo(14, jsonArray, (TextView) view.findViewById(R.id.info_text61), (TextView) view.findViewById(R.id.info_text62));// 14
            setCurInfo(8, jsonArray, (TextView) view.findViewById(R.id.info_text71), (TextView) view.findViewById(R.id.info_text72));// 8
            setCurInfo(30, jsonArray, (TextView) view.findViewById(R.id.info_text81), (TextView) view.findViewById(R.id.info_text82));// 30
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param i
     * @param jsonArray
     * @param n
     * @param r
     * @throws JSONException
     */
    public void setCurInfo(int i, JSONArray jsonArray, TextView n, TextView r) throws JSONException {
        JSONObject cur = new JSONObject(jsonArray.get(i).toString());
        String name = cur.get("name").toString().split("\\(")[0] + "/RUB";
        String rate = cur.get("rate").toString();
        n.setText(name);
        r.setText(rate);
    }

    /**
     *
     * @param v
     */
    private void sliderSetup(View v){
        SliderView sliderView = v.findViewById(R.id.imageSlider);

        SliderAdapter adapter = new SliderAdapter(getContext());
        SliderItem sliderItem1 = new SliderItem();
        sliderItem1.setImageUrl("data:image/jpeg;base64,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");
        adapter.addItem(sliderItem1);

        SliderItem sliderItem2 = new SliderItem();
        sliderItem2.setImageUrl("data:image/jpeg;base64,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");
        adapter.addItem(sliderItem2);

        SliderItem sliderItem3 = new SliderItem();
        sliderItem3.setImageUrl("data:image/jpeg;base64,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");
        adapter.addItem(sliderItem3);

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();

    }

}
