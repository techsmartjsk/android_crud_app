package com.example.database;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaDrm;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;
import java.lang.String;
import java.util.ArrayList;

public class Sql extends AppCompatActivity {
    java db;
EditText nametext,locationtext,intereststext,editId ;
Button btn;
Button view;
Button delete;
Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.java);
        db = new java(this);
        nametext =(EditText) findViewById(R.id.name);
        locationtext = (EditText) findViewById(R.id.location);
        intereststext = (EditText) findViewById(R.id.interests);
        editId = (EditText) findViewById(R.id.id);
        btn = (Button) findViewById(R.id.button);
        delete = (Button)findViewById(R.id.delete);
        view = (Button) findViewById(R.id.view);
        update = (Button)findViewById(R.id.update);
        addData();
        viewData();
        deleteData();
        updateData();
    }

    //Add new data

public void addData(){
    btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(nametext.getText().toString().length()!=0 && locationtext.getText().toString().length()!=0 && intereststext.getText().toString().length()!=0){
                boolean isInstered = db.insertData(nametext.getText().toString(),locationtext.getText().toString(),intereststext.getText().toString());
                if(isInstered == true){
                    Toast.makeText(Sql.this,"Data Inserted",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Sql.this,"Data NOT Inserted",Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(Sql.this,"Data Empty",Toast.LENGTH_LONG).show();

            }


        }
    });
}

//View all entered data

public void viewData(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Cursor res = db.view();
             if(res.getCount() == 0){
                 showdata("Error","Nothing in it");
                 return;
             }
             StringBuffer buffer = new StringBuffer();
             while(res.moveToNext()){
                 buffer.append("Name:" + res.getString(0) + "\n");
                 buffer.append("Location: " +res.getString(1) + "\n");
                 buffer.append("Interets: " + res.getString(2) + "\n");
                 buffer.append("ID: " + res.getString(3) + "\n\n");
             }
             showdata("Data",buffer.toString());
            }
        });

}

// showing up the data on AlertDialog

public void showdata(String title,String Message){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(Message);
    builder.show();
}

//Delete Data

public void deleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Integer isDeleted = db.deleteData(editId.getText().toString());
                if(isDeleted > 0){
                    Toast.makeText(Sql.this,"Data Deleted",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(Sql.this,"Data Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

// Editing or Updating Data

public void updateData(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate = db.updateData(nametext.getText().toString(),locationtext.getText().toString(),intereststext.getText().toString(),editId.getText().toString());
            if(isUpdate == true){
                Toast.makeText(Sql.this,"Data Updated",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(Sql.this,"Data Not Updated",Toast.LENGTH_SHORT).show();
            }
            }
        });
}
}
