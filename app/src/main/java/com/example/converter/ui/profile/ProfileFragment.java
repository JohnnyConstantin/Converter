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
import androidx.navigation.fragment.NavHostFragment;

import com.example.converter.HttpClient;
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

    public String makeExchange(String cur1, String cur2){
        HttpClient c = new HttpClient();
        Map<String, String> data = new HashMap<>();
        data.put("currencyFrom", cur1);
        data.put("currencyTo", cur2);
        data.put("userId", cur2);
        data.put("input", cur2);
        return c.post("/exchange", data.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragmentLayout = inflater.inflate(R.layout.fragment_profile, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        TextView Profile_login = (TextView) fragmentLayout.findViewById(R.id.Profile_login);
        ImageView Profile_photo = (ImageView) fragmentLayout.findViewById(R.id.Profile_photo);//<-Dunno what to do with this shit, mb just change its color like VIP and ordinary user
        ImageView Profile_hide = (ImageView) fragmentLayout.findViewById(R.id.Profile_hide);
        String login = "None";
        String userId = null;


        Bundle args = getArguments();
        if(args != null){
            login = args.getString("p_login");
            userId = args.getString("userId");
        }

        Profile_login.append(login);

        //todo hide only substring before @
        //todo add animation for hiding image
        //Hiding login when image is pressed
        String finalLogin = login;
        Profile_hide.setOnClickListener(v -> {
            if(!Hidden){
                Profile_login.setText("hidden");
                Hidden = true;
            } else{
                Hidden = false;
                Profile_login.setText(finalLogin);
            }
        });

        //todo changing mail
        //todo changing password
        //todo favorite pairs
        return fragmentLayout;
    }

}
