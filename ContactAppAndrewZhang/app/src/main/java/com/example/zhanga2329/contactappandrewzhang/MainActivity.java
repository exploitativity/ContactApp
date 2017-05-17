package com.example.zhanga2329.contactappandrewzhang;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName;
    EditText editAge;
    EditText editAddress;
    EditText editSearch;

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
        editSearch = (EditText) (findViewById(R.id.editText_search));
    }
    public void addData(View v) {
        boolean isInserted = myDb.insertData(editName.getText().toString(), editAge.getText().toString(), editAddress.getText().toString());

        if(isInserted){
            Log.d("MyContact", "Data Insert Successful");
            //Insert Toast message here
            Toast.makeText(this, "Data Insert Successful", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("MyContact", "Data Insert Failed");
            //Insert Toast message here
            Toast.makeText(this, "Data Insert Failed", Toast.LENGTH_LONG).show();
        }
    }
    public void viewData(View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            Log.d("MyContact", "Database is empty!");
            showMessage("Error", "No data found in database, res.getCount = " +res.getCount());
            //output message using Log.d and Toast
            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup a loop with Cursor (res) using moveToNext
        for(int i = 0; i < res.getColumnCount(); i++)
        {
            buffer.append(res.getColumnName(i));
            buffer.append("\t");
        }
        buffer.append("\n");
        res.moveToFirst();
        do
        {
            for(int i = 0; i < res.getColumnCount(); i++)
            {
                buffer.append(res.getString(i));
                buffer.append("\t");
            }
            buffer.append("\n");
        }while(res.moveToNext());
        // append each COL to the buffer
        // display message using showMessage
        showMessage("Data", buffer.toString());

    }
    public void search(View v) {
        Cursor res = myDb.getAllData();
        if(res.getCount() == 0){
            Log.d("MyContact", "Database is empty!");
            showMessage("Error", "No data found in database, res.getCount = " +res.getCount());
            //output message using Log.d and Toast
            return;
        }

        StringBuffer buffer = new StringBuffer();
        //setup a loop with Cursor (res) using moveToNext
        for(int i = 0; i < res.getColumnCount(); i++)
        {
            buffer.append(res.getColumnName(i));
            buffer.append("\t");
        }
        buffer.append("\n");
        res.moveToFirst();
        do
        {
            if(res.getString(res.getColumnIndex("NAME")).indexOf(editSearch.getText().toString()) > -1){
                for(int i = 0; i < res.getColumnCount(); i++)
                {
                    buffer.append(res.getString(i));
                    buffer.append("\t");
                }
                buffer.append("\n");
            }
        }while(res.moveToNext());
        // append each COL to the buffer
        // display message using showMessage
        showMessage("Search Results", buffer.toString());
    }

    private void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
