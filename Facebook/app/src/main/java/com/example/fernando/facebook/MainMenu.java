package com.example.fernando.facebook;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {

    EditText userText;
    EditText passwordText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Facebook");
        setContentView(R.layout.activity_main_menu);
        userText = (EditText)findViewById(R.id.usernameText);
        passwordText = (EditText)findViewById(R.id.passwordText);
    }

    public void loginActivity(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();
        String user = userText.getText().toString();
        String password= passwordText.getText().toString();
        //String[] argumentoUser = new String[]{user};
        //String[] argumentoPass = new String[]{password};

        if(!user.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select usu_nombre, usu_apellido from usuario where usu_email ='" + user + "' AND usu_pass ='"+ password +"'", null);

            if(fila.moveToFirst()){
                Intent i = new Intent(this, welcomeMenu.class);
                Bundle extras = new Bundle();
                extras.putString("nombreLogin",fila.getString(0));
                extras.putString("apellidoLogin",fila.getString(1));
                i.putExtras(extras);
                startActivity(i);
                BaseDeDatabase.close();
            }
            else {
                Toast.makeText(this,"email o password incorrecto", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        }
        else {
            Toast.makeText(this, "Debes introducir el mail", Toast.LENGTH_SHORT).show();
        }
    }


    public void lostPassActivity(View view) {
        Intent i = new Intent(this, lostPassMenu.class);
        startActivity(i);

    }

    public void registerNameActivity(View view) {
        Intent i = new Intent(this, registerNameMenu.class);
        startActivity(i);
    }
/*
    public void Buscar(View view){



        if(!user.isEmpty()){
            Cursor fila = BaseDeDatabase.rawQuery
                    ("select descripcion, precio from articulos where codigo =" + codigo, null);

            if(fila.moveToFirst()){
                et_descripcion.setText(fila.getString(0));
                et_precio.setText(fila.getString(1));
                BaseDeDatabase.close();
            } else {
                Toast.makeText(this,"No existe el artículo", Toast.LENGTH_SHORT).show();
                BaseDeDatabase.close();
            }

        } else {
            Toast.makeText(this, "Debes introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }
*/

}


