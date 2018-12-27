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
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();
        String mail = para.getText().toString();
        String pass = "clave";


        if (isValid(para.getText().toString())){
            /* ContentValues registro = new ContentValues();
            registro.put ("usu_pass",pass);
            BaseDatabase.update("usuario", registro, "usu_email=" + "'"+ mail + "'", null);
            BaseDatabase.close(); */
            // Se instancia un objeto de la Clase Email la primera vez con la Session y luego con el cuerpo del mail
            new Email("pruebaemailusach@gmail.com", "Prueba123",sendPassMenu.this).execute(
                    new Email.Correo( para.getText().toString(), "Recuperación de contraseña", "Su nueva contraseña es : " + nuevapass)
            );
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
