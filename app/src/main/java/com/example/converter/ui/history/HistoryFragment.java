package com.example.converter.ui.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.converter.R;
/**
 * Фрагмент страницы с историей
 * @author Vadim
 */
public class HistoryFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragmentLayout = inflater.inflate(R.layout.fragment_history, container, false);
        NavController navController = NavHostFragment.findNavController(this);



        return fragmentLayout;
    }
}
