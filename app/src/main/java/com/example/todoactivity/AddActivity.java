package com.example.todoactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mDate;
    private EditText mDueDate;
    private Button mAddButton;
    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        setValues();
    }

    private void initView()
    {
        myDb = new DatabaseHelper(this);
        mName=findViewById(R.id.et_item_name);
        mDate=findViewById(R.id.et_date);
        mDueDate=findViewById(R.id.et_due_date);
        mAddButton=findViewById(R.id.bt_add_item_to_list);
    }
    private void setValues()
    {
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAdded = myDb.insertData(mName.getText().toString(),mDate.getText().toString(),mDueDate.getText().toString());
                if(isAdded)
                    Toast.makeText(AddActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddActivity.this, "Data not inserted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
