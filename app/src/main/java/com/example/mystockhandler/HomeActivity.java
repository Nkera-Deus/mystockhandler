package com.example.mystockhandler;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    EditText name,bPrice, sPrice, exDate, bNumber;
    Button insert, update, delete, view;
    DBHelper DB;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        name = findViewById(R.id.name);
        bPrice = findViewById(R.id.bPrice);
        sPrice = findViewById(R.id.sPrice);
        bNumber = findViewById(R.id.bNumber);
        exDate = findViewById(R.id.exDate);

        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);


       DB =new DBHelper(this);
       insert.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String nameTXT = name.getText().toString();
               String bPriceTXT = bPrice.getText().toString();
               String sPriceTXT = sPrice.getText().toString();
               String bNumberTXT = bNumber.getText().toString();
               String exDateTXT = exDate.getText().toString();

               Boolean checkinsertdata = DB.insertitemdata(nameTXT, bPriceTXT, sPriceTXT, bNumberTXT, exDateTXT);
               if (checkinsertdata == true&& !nameTXT.equals("")) {

                       Toast.makeText(HomeActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                       name.setText("");
                       bPrice.setText("");
                       sPrice.setText("");
                       exDate.setText("");
                       bNumber.setText("");

               }
               else
               
               
                   Toast.makeText(HomeActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
               name.setText("");
               bPrice.setText("");
               sPrice.setText("");
               exDate.setText("");
               bNumber.setText("");

           }


       });
       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String nameTXT = name.getText().toString();
               String bPriceTXT = bPrice.getText().toString();
               String sPriceTXT = sPrice.getText().toString();
               String bNumberTXT = bNumber.getText().toString();
               String exDateTXT = exDate.getText().toString();
               Boolean checkupdatedata = DB.updateitemdata(nameTXT, bPriceTXT, sPriceTXT, bNumberTXT, exDateTXT);
               if (checkupdatedata == true)
                   Toast.makeText(HomeActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(HomeActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();

           }




       });
       delete.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String nameTXT = name.getText().toString();


               Boolean checkdeletedata = DB.deletedata(nameTXT);
               if (checkdeletedata == true)
                   Toast.makeText(HomeActivity.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
               else
                   Toast.makeText(HomeActivity.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
           }




       });
       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Cursor res = DB.getdata();
               if (res.getCount() == 0) {
                   Toast.makeText(HomeActivity.this, "No List Exits", Toast.LENGTH_SHORT).show();
                   return;

               }

               StringBuffer buffer = new StringBuffer();
               int i;
               while (res.moveToNext()) {
                   buffer.append("Name:" + res.getString(0) + "\n");
                   buffer.append("Buying Price:" + res.getString(1) + "\n");
                   buffer.append("Selling Price:" + res.getString(2) + "\n");
                   buffer.append("Batch Number:" + res.getString(3) + "\n");
                   buffer.append("Expiry Date:" + res.getString(4) + "\n\n");

               }
               AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
               builder.setCancelable(true);
               builder.setTitle("Entries");
               builder.setMessage(buffer.toString());
               builder.show();
           }


       });

    }
}