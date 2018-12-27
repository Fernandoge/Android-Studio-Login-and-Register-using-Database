package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class registerSexMenu extends AppCompatActivity {

    RadioButton rb_hombre;
    RadioButton rb_mujer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_sex_menu);
        rb_hombre = (RadioButton)findViewById(R.id.rbHombre);
        rb_mujer = (RadioButton)findViewById(R.id.rbMujer);
    }

    public void registerMailPassActivity(View view) {
        if (rb_hombre.isChecked() || rb_mujer.isChecked()) {
            Intent j = getIntent();
            Bundle extras = j.getExtras();
            Intent i = new Intent(this, registerMailPass.class);
            if (rb_hombre.isChecked())
                extras.putString("sexo", "H");
            else if (rb_mujer.isChecked())
                extras.putString("sexo", "M");
            i.putExtras(extras);
            startActivity(i);
        }
        else
            Toast.makeText(this, "Tiene que marcar una opción", Toast.LENGTH_LONG).show();

    }

    public void Regresar(View view){
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        Intent i = new Intent(this, registerBirthDateMenu.class);
        i.putExtras(extras);
        startActivity(i);
    }


}
