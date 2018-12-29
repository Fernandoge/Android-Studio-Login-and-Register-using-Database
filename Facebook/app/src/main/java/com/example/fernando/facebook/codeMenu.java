package com.example.fernando.facebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class codeMenu extends AppCompatActivity {

    EditText et_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_menu);
        et_codigo = findViewById(R.id.codigo);

    }

    public void Ingresar (View view ){
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        int codigo = extras.getInt("codigo");
        if ((et_codigo.getText().toString()).isEmpty())
            et_codigo.setError("Este campo es obligatorio");
        else {
            int valor_et = Integer.valueOf(et_codigo.getText().toString());
            if (codigo == valor_et) {
                Intent i = new Intent(this, crearNuevaPassMenu.class);
                i.putExtras(extras);
                startActivity(i);
            } else {
                Toast.makeText(this, "El codigo ingresado es incorrecto", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainMenu.class);
                startActivity(i);
            }
        }
    }
}
