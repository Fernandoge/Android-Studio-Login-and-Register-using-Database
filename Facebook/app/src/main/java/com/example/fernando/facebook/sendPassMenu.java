package com.example.fernando.facebook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class sendPassMenu extends AppCompatActivity {

    private EditText para;
    private Button enviar, volver;
    String nuevapass = "clave";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_pass_menu);

        para = findViewById(R.id.txtPara);

        enviar = findViewById(R.id.btnEnviar);
        volver = findViewById(R.id.btnVolver);



    }

    public void Enviar(View view){

        int range = 999001;
        int min = 1000;
        int codigo = (int) (Math.random() * range) + min;

        if (isValid(para.getText().toString())){
            // Se instancia un objeto de la Clase Email la primera vez con la Session y luego con el cuerpo del mail
            //cambiar user a feisbucrecuperarcontrasena@gmail.com con pass Clave123  si no pruebaemailusach@gmail.com y pass Prueba123
            new Email("feisbucrecuperarcontrasena", "Clave123",sendPassMenu.this).execute(
                    new Email.Correo( para.getText().toString(), "Recuperación de contraseña", "El código es : " + codigo)
            );


            Intent i = new Intent(this, codeMenu.class);
            Bundle extras = new Bundle();
            extras.putInt("codigo",codigo);
            extras.putString("correo",para.getText().toString());
            i.putExtras(extras);
            startActivity(i);
        }
        else
            para.setError("El mail ingresado no es valido");
    }

    public void Regresar(View view){
        Intent i = new Intent(this, MainMenu.class);
        startActivity(i);
    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

}
