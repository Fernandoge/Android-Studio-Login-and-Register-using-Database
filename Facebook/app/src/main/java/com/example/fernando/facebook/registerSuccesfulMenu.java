package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class registerSuccesfulMenu extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_succesful_menu);
    }



    public void mainMenuActivity(View view) {
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);

    }

}
