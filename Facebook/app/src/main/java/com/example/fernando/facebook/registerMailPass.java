package com.example.fernando.facebook;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;


public class registerMailPass extends AppCompatActivity {

    EditText et_correo;
    EditText et_pass;
    private TextView tvPasswordStrength;
    int strengthPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mail_pass);
        et_correo = (EditText) findViewById(R.id.correo);
        et_pass = (EditText) findViewById(R.id.pass);
        tvPasswordStrength = (TextView) findViewById(R.id.tvPasswordStrength);
        // Add password ontext change listener
        et_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Calculate password strength
                calculateStrength(editable.toString());
            }
        });
    }

    public void calculateStrength(String passwordText) {
        int upperChars = 0, lowerChars = 0, numbers = 0,
                specialChars = 0, otherChars = 0;
        char c;

        int passwordLength = passwordText.length();

        if (passwordLength ==0)
        {
            tvPasswordStrength.setText("Invalid Password");
            tvPasswordStrength.setBackgroundColor(Color.RED);
            return;
        }

        //If password length is <= 5 set strengthPoints=1
        if (passwordLength <= 5) {
            strengthPoints =1;
        }
        //If password length is >5 and <= 10 set strengthPoints=2
        else if (passwordLength <= 10) {
            strengthPoints = 2;
        }
        //If password length is >10 set strengthPoints=3
        else
            strengthPoints = 3;
        // Loop through the characters of the password
        for (int i = 0; i < passwordLength; i++) {
            c = passwordText.charAt(i);
            // If password contains lowercase letters
            // then increase strengthPoints by 1
            if (c >= 'a' && c <= 'z') {
                if (lowerChars == 0) strengthPoints++;
                lowerChars = 1;
            }
            // If password contains uppercase letters
            // then increase strengthPoints by 1
            else if (c >= 'A' && c <= 'Z') {
                if (upperChars == 0) strengthPoints++;
                upperChars = 1;
            }
            // If password contains numbers
            // then increase strengthPoints by 1
            else if (c >= '0' && c <= '9') {
                if (numbers == 0) strengthPoints++;
                numbers = 1;
            }
            // If password contains _ or @
            // then increase strengthPoints by 1
            else if (c == '_' || c == '@') {
                if (specialChars == 0) strengthPoints += 1;
                specialChars = 1;
            }
            // If password contains any other special chars
            // then increase strengthPoints by 1
            else {
                if (otherChars == 0) strengthPoints += 2;
                otherChars = 1;
            }
        }

        if (strengthPoints <= 3)
        {
            tvPasswordStrength.setText("Password Strength : LOW");
            tvPasswordStrength.setBackgroundColor(Color.RED);
        }
        else if (strengthPoints <= 6) {
            tvPasswordStrength.setText("Password Strength : MEDIUM");
            tvPasswordStrength.setBackgroundColor(Color.YELLOW);
        }
        else if (strengthPoints <= 9){
            tvPasswordStrength.setText("Password Strength : HIGH");
            tvPasswordStrength.setBackgroundColor(Color.GREEN);
        }
    }

    public void registerSuccesfulActivity(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        String correo = et_correo.getText().toString();
        String pass = et_pass.getText().toString();
        if(correo.isEmpty() || pass.isEmpty() || !isValid(correo) || strengthPoints <= 3) {
            if (correo.isEmpty())
                et_correo.setError("Este campo es obligatorio");
            else if (!isValid(correo))
                et_correo.setError("El mail ingresado no es valido");
            if (pass.isEmpty())
                et_pass.setError("Este campo es obligatorio");
            else if (strengthPoints <= 3){
                et_pass.setError("La contraseña es muy debil");
            }
        }
        else {
            Intent j = getIntent();
            Bundle extras = j.getExtras();
            Intent i = new Intent(this, registerSuccesfulMenu.class);
            ContentValues registro = new ContentValues();


            String nombre = extras.getString("nombreUsuario");
            String apellido = extras.getString("apellidoUsuario");
            String telefono = extras.getString("telefonoUsuario");
            String sexo = extras.getString("sexo");
            String date = extras.getString("fecha");

            //Ingreso en la base de datos

            registro.put("usu_nombre",nombre);
            registro.put("usu_apellido",apellido);
            registro.put("usu_email",correo);
            registro.put("usu_fono",telefono);
            registro.put("usu_pass",pass);
            registro.put("usu_sexo",sexo);
            registro.put("usu_fecha",date);


            BaseDeDatos.insert("usuario", null, registro);
            BaseDeDatos.close();

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
            startActivity(i);
        }
    }

    public void Regresar(View view){
        Intent j = getIntent();
        Bundle extras = j.getExtras();
        Intent i = new Intent(this, registerSexMenu.class);
        i.putExtras(extras);
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


/*
    //Méotdo para dar de alta los productos
    public void Registrar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){
            ContentValues registro = new ContentValues();

            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            BaseDeDatos.insert("articulos", null, registro);

            BaseDeDatos.close();
            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            Toast.makeText(this,"Registro exitoso", Toast.LENGTH_SHORT).show();
        } else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para consultar un artículo o producto
    public void Buscar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDeDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
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

    //Método para eliminar un producto o Artículo
    public void Eliminar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper
                (this, "administracion", nu ll, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){

            int cantidad = BaseDatabase.delete("articulos", "codigo=" + codigo, null);
            BaseDatabase.close();

            et_codigo.setText("");
            et_descripcion.setText("");
            et_precio.setText("");

            if(cantidad == 1){
                Toast.makeText(this, "Artículo eliminado exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "Debes de introducir el código del artículo", Toast.LENGTH_SHORT).show();
        }
    }

    //Método para modificar un artículo o producto
    public void Modificar(View view){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
        SQLiteDatabase BaseDatabase = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();
        String descripcion = et_descripcion.getText().toString();
        String precio = et_precio.getText().toString();

        if(!codigo.isEmpty() && !descripcion.isEmpty() && !precio.isEmpty()){

            ContentValues registro = new ContentValues();
            registro.put("codigo", codigo);
            registro.put("descripcion", descripcion);
            registro.put("precio", precio);

            int cantidad = BaseDatabase.update("articulos", registro, "codigo=" + codigo, null);
            BaseDatabase.close();

            if(cantidad == 1){
                Toast.makeText(this, "Artículo modificado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "El artículo no existe", Toast.LENGTH_SHORT).show();
            }
*/
/*        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
 */
