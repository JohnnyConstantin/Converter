package com.example.converter.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.example.converter.HttpClient;
import com.example.converter.MainActivity;
import com.example.converter.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Fragment of the profile page
 * @author Vadim
 */
public class ProfileFragment extends Fragment {
    /** Flag to hide the login in the profile */
    boolean Hidden = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView Profile_login = (TextView) fragmentLayout.findViewById(R.id.Profile_login);
        TextView curTo = (TextView) fragmentLayout.findViewById(R.id.cur_to);
        EditText curFrom = (EditText) fragmentLayout.findViewById(R.id.cur_from);
        Spinner spinFrom = (Spinner) fragmentLayout.findViewById(R.id.spin_from);
        Spinner spinTo = (Spinner) fragmentLayout.findViewById(R.id.spin_to);
        Button button = (Button) fragmentLayout.findViewById(R.id.accept);
        ImageView Profile_hide = (ImageView) fragmentLayout.findViewById(R.id.Profile_hide);

        MainActivity m = ((MainActivity) getActivity());
        String login = m.getLogin();
        String userId = m.getUserId();

        Profile_login.append(login);
        Profile_hide.setOnClickListener(v -> {
            if(!Hidden){
                int len = login.length();
                String hidden = login.charAt(0) + "**************" + login.substring(len-6);
                Profile_login.setText(hidden);
                Hidden = true;
            } else{
                Hidden = false;
                Profile_login.setText(login);
            }
        });

        return fragmentLayout;
    }

    /**
     * Currency conversion method
     * @param cur1 conversion comes from this currency
     * @param cur2 conversion takes place into this currency
     * @return returns the response of the http request
     */
    public String makeExchange(String cur1, String cur2){
        HttpClient c = new HttpClient();
        Map<String, String> data = new HashMap<>();
        data.put("currencyFrom", cur1);
        data.put("currencyTo", cur2);
        data.put("userId", cur2);
        data.put("input", cur2);
        String response = c.post("/exchange", data.toString());
        c.cancel(true);
        return response;
    }
}
