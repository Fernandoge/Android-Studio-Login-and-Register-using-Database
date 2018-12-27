package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class welcomeMenu extends AppCompatActivity {

    TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_menu);
        texto = (TextView)findViewById(R.id.textView);
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        String nombre = extras.getString("nombreLogin");
        texto.setText("bienvenido "+nombre);
    }



}
