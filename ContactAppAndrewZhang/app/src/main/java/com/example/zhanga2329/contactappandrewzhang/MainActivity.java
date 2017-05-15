package com.example.zhanga2329.contactappandrewzhang;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;

    Button btnAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);
        //Add layout vars
        editName = (EditText)(findViewById(R.id.editText_name));
        editAge = (EditText)(findViewById(R.id.editText_age));
        editAddress = (EditText)(findViewById(R.id.editText_address));
    }
    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(),editAge.getText().toString(),editAddress.getText().toString());

        if(isInserted){
            Log.d("MyContact", "Data Insert Successful");
            //Insert Toast message here
        }
        else{
            Log.d("MyContact", "Data Insert Failed");
            //Insert Toast message here
        }
    }
}
