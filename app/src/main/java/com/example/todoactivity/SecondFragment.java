package com.example.todoactivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SecondFragment extends Fragment {
    private String id;
    private String TAG = "SecondFragment";
    DatabaseHelper myDb;
    View rootView;
    private EditText mName, mDate, mDueDate;
    private Button mUpdateButton, mDeleteButton;
    private String value;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_second, container, false);

        initView();
        setValues();
        return rootView;
    }

    private void initView() {
        myDb = new DatabaseHelper(getActivity());
        mName = rootView.findViewById(R.id.et_item_name);
        mDate = rootView.findViewById(R.id.et_date);
        mDueDate = rootView.findViewById(R.id.et_due_date);
        mUpdateButton = rootView.findViewById(R.id.bt_update);
        mDeleteButton = rootView.findViewById(R.id.bt_delete);

        if (getArguments() != null) {
            value = getArguments().getString("key");

            if (value != null && !value.equalsIgnoreCase("")) {
                Cursor values = myDb.getRowData(value);
                values.moveToFirst();
                id = values.getString(0);
                String name = values.getString(1);
                String date = values.getString(2);
                String dueDate = values.getString(3);


                mName.setText(name);
                mDate.setText(date);
                mDueDate.setText(dueDate);
            }
        }
    }

    private void setValues() {
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(id, mName.getText().toString(), mDate.getText().toString(), mDueDate.getText().toString());
                if (isUpdated)
                    Toast.makeText(getActivity(), "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Update unsuccessful", Toast.LENGTH_SHORT).show();
                getActivity().finish();

            }
        });

        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletedRows = myDb.deleteData(id, mName.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(getActivity(), "To-do deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Could Not Delete", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

    }

}
