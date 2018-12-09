package com.example.tommal.sqliteapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
DatabaseHelper myDb;

EditText editeName, editSurname,editMarks,editTextId;

Button btnAddData;
Button btnViewAll;
Button btnUpdate;
Button btnDeleteData;
Button btnSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        editeName = (EditText) findViewById(R.id.editText);
        editSurname = (EditText) findViewById(R.id.editText2);
        editMarks = (EditText) findViewById(R.id.editText3);
         editTextId = (EditText) findViewById(R.id.editTextId);
        btnAddData = (Button) findViewById(R.id.buttonAdd);
        btnViewAll = (Button) findViewById(R.id.buttonShow);
        btnUpdate = (Button) findViewById(R.id.buttonUpdate);
        btnDeleteData = (Button) findViewById(R.id.btnDelete);
        btnSecond = (Button) findViewById(R.id.buttonSecondActivity);

        AddData();
        showAllData();
        updateData();
        deleteData();
        openSecondActivity();
        openListView();
    }


    /**
     * Apre la seconda attività
     */
    private void openSecondActivity(){
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".MainSecond");
                startActivity(intent);
            }
        });
    }

    private void openListView(){
        ((Button) findViewById(R.id.buttonListLayout)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".ListViewActivity");
                startActivity(intent);
            }
        });
    }

    private void deleteData() {
      btnDeleteData.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
       Integer deletedRows = myDb.deleteData(editTextId.getText().toString());
       if(deletedRows > 0)
           Toast.makeText(MainActivity.this,"Data Deleted Successfully",Toast.LENGTH_LONG).show();
       else
           Toast.makeText(MainActivity.this,"Error Deleted!",Toast.LENGTH_LONG).show();
    }
});
    }

    public  void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editeName.getText().toString(),
                                editSurname.getText().toString(),
                                Integer.parseInt(editMarks.getText().toString()) );
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void showAllData(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                   showMessage("Error", "No data were found!");
                    return;
                }
                else
                {
                    StringBuffer query = new StringBuffer();
                    while (res.moveToNext()){

                        query.append("ID: " + res.getString(0) + "\n");
                        query.append("Name: " + res.getString(1) + "\n");
                        query.append("Surname: " + res.getString(2)+ "\n");
                        query.append("Mark: " + res.getString(3) + "\n\n");
                    }
                    showMessage("Data",query.toString());
                }
            }
        });
    }



    public void updateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if( myDb.updateData(editTextId.getText().toString(),editeName.getText().toString(),
                        editSurname.getText().toString(),
                       editMarks.getText().toString())){
                   Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
               }
               else
                   Toast.makeText(MainActivity.this,"Error in updated",Toast.LENGTH_LONG).show();
            }
        });
    }


    private void showMessage(String title   , String message) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setCancelable(true);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.show();

    }

}
