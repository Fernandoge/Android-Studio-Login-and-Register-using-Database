package com.example.fernando.facebook;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Calendar;

public class registerBirthDateMenu extends AppCompatActivity {


    DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_birth_date_menu);
        datePicker = (DatePicker)findViewById(R.id.datePicker1);

    }



    public void registerSexActivity(View view) {
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        Intent i = new Intent(this, registerSexMenu.class);
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        String dateTotal = year+"/"+month+"/"+day;
        extras.putString("fecha", dateTotal);
        i.putExtras(extras);
        startActivity(i);
    }

    public void Regresar(View view){
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        Intent i = new Intent(this, registerCelMenu.class);
        i.putExtras(extras);
        startActivity(i);
    }
}
