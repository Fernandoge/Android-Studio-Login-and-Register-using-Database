package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class lostPassMenu extends AppCompatActivity {

    private RadioButton rb1, rb2, rb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_pass_menu);

        rb1 = (RadioButton) findViewById(R.id.rbSms);
        rb2 = (RadioButton) findViewById(R.id.rbWsp);
        rb3 = (RadioButton) findViewById(R.id.rbEmail);

    }


    public void continueButton(View view) {

        if (rb1.isChecked()) {
            Toast.makeText(this, "Se envió un mensaje SMS", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainMenu.class);
            startActivity(i);
        }

        if (rb2.isChecked()) {
            Toast.makeText(this, "Se envió un mensaje a WhatsApp", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, MainMenu.class);
            startActivity(i);
        }

        if (rb3.isChecked()) {
            //Toast.makeText(this, "Se envió un correo electrónico", Toast.LENGTH_LONG).show();
            Intent i = new Intent(this, sendPassMenu.class);
            startActivity(i);
        }

    }
}
