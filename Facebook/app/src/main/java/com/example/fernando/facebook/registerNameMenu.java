//activity registro 1

package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class registerNameMenu extends AppCompatActivity {


    EditText textoNombre;
    EditText textoApellido;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_name_menu);
        textoNombre = (EditText)findViewById(R.id.nombre);
        textoApellido = (EditText)findViewById(R.id.apellido);
    }

    public void registerCelActivity(View view) {
        String nombre = textoNombre.getText().toString();
        String apellido = textoApellido.getText().toString();
        if(nombre.isEmpty() || apellido.isEmpty()) {
            if (nombre.isEmpty())
                textoNombre.setError("Este campo es obligatorio");
            if (apellido.isEmpty())
                textoApellido.setError("Este campo es obligatorio");
        }
        else {
            Intent i = new Intent(this, registerCelMenu.class);
            Bundle extras = new Bundle();
            extras.putString("nombreUsuario",nombre);
            extras.putString("apellidoUsuario", apellido);
            i.putExtras(extras);
            startActivity(i);

        }
    }

    public void Regresar(View view){
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }
}
