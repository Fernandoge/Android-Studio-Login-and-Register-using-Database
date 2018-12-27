//activity registro 2



package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class registerCelMenu extends AppCompatActivity {

    EditText textoTelefono;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_cel_menu);

        textoTelefono = (EditText)findViewById(R.id.number);

    }

    public void registerBirthdayActivity(View view) {
        String numCel = textoTelefono.getText().toString();
        if(numCel.length() != 8) {
            textoTelefono.setError("No ha ingresado un número correcto (el número debe tener 8 digitos)");

        }
        else
            {
            Intent j = getIntent();
            Bundle extras = j.getExtras();
            Intent i = new Intent(this, registerBirthDateMenu.class);
            extras.putString("telefonoUsuario", numCel);
            i.putExtras(extras);
            startActivity(i);
        }
    }

    public void Regresar(View view){
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        Intent i = new Intent(this, registerNameMenu.class);
        i.putExtras(extras);
        startActivity(i);
    }
}
