package ru.diaran.lab6;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainList.OnFragmentInteractionListener {

@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onFragmentInteraction(int link) {
        DetailFrag fragment = (DetailFrag) getFragmentManager()
                .findFragmentById(R.id.fragment);
        if (fragment != null && fragment.isInLayout()) {
            fragment.setText(link);
        }
    }

}

