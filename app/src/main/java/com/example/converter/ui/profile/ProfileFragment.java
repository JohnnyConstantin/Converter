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

import com.example.converter.R;

/**
 * Фрагмент страницы профиля
 * @author Vadim
 */
public class ProfileFragment extends Fragment {
    /**
     Flag for hiding login in profile head
     */
    boolean Hidden = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_profile, container, false);
        NavController navController = NavHostFragment.findNavController(this);
        TextView Profile_login = (TextView) fragmentLayout.findViewById(R.id.Profile_login);
        ImageView Profile_photo = (ImageView) fragmentLayout.findViewById(R.id.Profile_photo);//<-Dunno what to do with this shit, mb just change its color like VIP and ordinary user
        ImageView Profile_hide = (ImageView) fragmentLayout.findViewById(R.id.Profile_hide);

        //todo need to place in Profile_login mail from authorization
        Profile_login.append("Justtesting@mail.ru");


        //todo hide only substring before @
        //todo add animation for hiding image
        //Hiding login when image is pressed
        Profile_hide.setOnClickListener(v -> {
            if(!Hidden){
                Profile_login.setText("hidden");
                Hidden = true;
            } else{
            Hidden = false;
            Profile_login.setText("Justtesting@mail.ru");
            }
        });

        //todo ID(get it from db)
        //todo changing mail
        //todo changing password
        //todo favorite pairs
        return fragmentLayout;
    }

}
