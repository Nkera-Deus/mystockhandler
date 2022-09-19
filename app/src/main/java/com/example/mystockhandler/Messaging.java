package com.example.mystockhandler;

//import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.PendingIntent;
import android.app.Activity;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Messaging extends Activity {
    EditText Mobileno,message;
    Button sendsms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        Mobileno =findViewById(R.id.edtNumber);
        message =findViewById(R.id.edtMessage);

        sendsms =findViewById(R.id.btnSendSMS);

        sendsms.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String no = Mobileno.getText().toString();
                String msg=message.getText().toString();
                Intent intent = new Intent(getApplicationContext(),Messaging.class);
                PendingIntent pi = PendingIntent.getActivity(getApplicationContext(), 0, intent,0 );
                SmsManager smsM = SmsManager.getDefault();
                smsM.sendTextMessage(no, null, msg, pi, null);
                Toast.makeText(Messaging.this, "Message sent successfully!", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.my_options_menu, menu);
        return true;
    }


}