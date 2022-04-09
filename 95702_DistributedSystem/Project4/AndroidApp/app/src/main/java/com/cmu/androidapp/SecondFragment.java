package com.cmu.androidapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.cmu.androidapp.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    View view;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

//        binding = FragmentSecondBinding.inflate(inflater, container, false);
        view = inflater.inflate(R.layout.fragment_second, container, false);
        Button compare_button = (Button)view.findViewById(R.id.button_compare);

        compare_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int searchID1 = Integer.parseInt(((EditText)view.findViewById(R.id.playerID1)).getText().toString());
                int searchID2 = Integer.parseInt(((EditText)view.findViewById(R.id.playerID2)).getText().toString());
                fpl FPL = new fpl();
                System.out.println(searchID1);
                String result = FPL.handle_compare(searchID1,searchID2);
                TextView result_text = (TextView) view.findViewById(R.id.compare_result);
                result_text.setText(result);
                TextView searchView1 = (EditText) view.findViewById(R.id.playerID1);
                TextView searchView2 = (EditText)view.findViewById(R.id.playerID2);
                searchView1.setText("");
                searchView2.setText("");
            }
        });

        Button compare_switch_button = (Button)view.findViewById(R.id.button_switch2);
        compare_switch_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view1) {
                view = inflater.inflate(R.layout.fragment_first, container, false);

            }
        });


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}