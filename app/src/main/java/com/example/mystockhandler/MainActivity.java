package com.example.mystockhandler;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
    EditText username, password,retypePassword;
    Button Signin, Signup;
    DBHelper2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.edtUserName);
        password=(EditText)findViewById(R.id.edtPassword);
        retypePassword=(EditText)findViewById(R.id.edtComPassword);


        Signin=(Button)findViewById(R.id.btnSignin) ;
        Signup=(Button)findViewById(R.id.btnSignup);
        DB = new DBHelper2(this);


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass=password.getText().toString();
                String retype=retypePassword.getText().toString();

                if(user.equals("")||pass.equals("")||retype.equals(""))
                    Toast.makeText(MainActivity.this,"Please enter all fields",Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(retype)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(MainActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                                username.setText("");
                                password.setText("");
                                retypePassword.setText("");

                            } else {
                                Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(MainActivity.this, "User already exists", Toast.LENGTH_SHORT).show();

                        }

                    } else {
                        Toast.makeText(MainActivity.this, "password does not match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        Signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });




        }
}