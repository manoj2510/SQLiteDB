package com.example.todoactivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FirstFragment extends Fragment {
    DatabaseHelper myDb;

    public FirstFragment() {
    }

    View rootView;
    private RecyclerView mRecycler;
    private Button mAddButton;
    public static final String TAG = "FirstFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_first, container, false);
        initView();
//        setValues();
        Log.d(TAG, "onCreateView: called");
        return rootView;
    }

    private void initView() {
        myDb = new DatabaseHelper(getActivity());
        mRecycler = rootView.findViewById(R.id.name_recycler);
        mAddButton = rootView.findViewById(R.id.bt_add_item);
        Log.d(TAG, "initView: called");
    }

    private ArrayList<String> loadData() {
        Cursor res = myDb.getNames();
        if (res.getCount() == 0) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
//            return null;
        }
        Log.d(TAG, "loadData: called");
        ArrayList<String> nameList = new ArrayList<>();
        while (res.moveToNext()) {
            nameList.add(res.getString(0));
        }
        return nameList;
    }

    @Override
    public void onResume() {
        super.onResume();
        setValues();
    }

    private void setValues() {
        TodoAdapter todoAdapter = new TodoAdapter(getActivity(), loadData());
        mRecycler.setItemAnimator(new DefaultItemAnimator());
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(todoAdapter);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddActivity.class);
                startActivity(intent);
            }
        });
        Log.d(TAG, "setValues: called");
    }

}
