package com.example.todoactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

public class DetailsActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        fragmentManager=getSupportFragmentManager();
        setValues();
    }

    private void setValues()
    {
        Intent intent = getIntent();
        String name=intent.getStringExtra("nameKey");
        SecondFragment secondFragment = new SecondFragment();

        Bundle bundle = new Bundle();
        bundle.putString("key",name);
        secondFragment.setArguments(bundle);

        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.layoutContainer2,secondFragment);
        fragmentTransaction.commit();
    }

}
