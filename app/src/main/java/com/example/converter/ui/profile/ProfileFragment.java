package com.example.converter.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Фрагмент страницы профиля
 * @author Vadim
 */
public class ProfileFragment extends Fragment {
    /**
     Флаг для сокрытия логина в профиле
     */
    boolean Hidden = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_profile, container, false);
        TextView Profile_login = (TextView) fragmentLayout.findViewById(R.id.Profile_login);
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
