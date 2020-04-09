package com.example.todoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActionListener {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    String selectedName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        /*if(savedInstanceState==null)
            addListFragment();
        else
            addUpdateFragment(savedInstanceState.getString("selectedName",null));*/
//        int orientation = this.getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        // code for portrait mode
        //addListFragment();
//        } else {
//            // code for landscape mode
//            addListFragment();
//            addSecondFragment();
//        }


        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            addListFragment();
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            addListFragment();
            addSecondFragment(selectedName);
        }

    }


    private void addListFragment() {
        fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment firstFragment = new FirstFragment();
        fragmentTransaction.add(R.id.layoutContainer, firstFragment);
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addSecondFragment(String name) {
        fragmentTransaction = fragmentManager.beginTransaction();

        SecondFragment secondFragment = new SecondFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key",name);
        secondFragment.setArguments(bundle);

        fragmentTransaction.add(R.id.layoutContainer2, secondFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void respond(String name) {
        selectedName = name;
        addSecondFragment(name);
    }
}
