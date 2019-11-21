package com.example.projectpath.ui.explorer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.projectpath.R;

public class ExplorerFragment extends Fragment {

    private ExplorerViewModel explorerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        explorerViewModel =
                ViewModelProviders.of(this).get(ExplorerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_explorer, container, false);
        final TextView textView = root.findViewById(R.id.text_explorer);
        explorerViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}