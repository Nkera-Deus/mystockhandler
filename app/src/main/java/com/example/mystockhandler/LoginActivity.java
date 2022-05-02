package com.example.mystockhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText username, password;
    private TextView ComName, Info;
    private Button myButton;
    DBHelper2 DB;

    private int counter = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.Uname);
        password = (EditText) findViewById(R.id.Pword);
        ComName = (TextView) findViewById(R.id.txtViewUp);
        Info = (TextView) findViewById(R.id.txtViewDown);
        myButton = (Button) findViewById(R.id.btnLogin);
        DB = new DBHelper2(this);

        Info.setText("No. of attemps remaining: 4");

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkuserpass = DB.checkusernamepaassword(user, pass);

                    if (checkuserpass == true) {
                        Toast.makeText(LoginActivity.this, "Signin successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        username.setText("");
                        password.setText("");
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                       counter--;
                        Info.setText("No. of attemps remaining: " + String.valueOf(counter));

                        if (counter == 0) {
                            myButton.setEnabled(false);

                        }
                    }
                }
            }
        });


    }
}