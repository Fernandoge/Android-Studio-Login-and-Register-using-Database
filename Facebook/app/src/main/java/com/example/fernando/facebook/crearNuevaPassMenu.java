package com.example.fernando.facebook;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class crearNuevaPassMenu extends AppCompatActivity {

    EditText et_pass;
    EditText et_confirm_pass;
    int upperChars = 0, lowerChars = 0, numbers = 0, specialChars = 0, otherChars = 0, strengthPoints = 0, passwordLengthGlobal = 0, seguridad = 0;
    private TextView tvPasswordStrength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nueva_pass_menu);

        et_pass = (EditText)findViewById(R.id.password);
        et_confirm_pass = (EditText)findViewById(R.id.password2);
        tvPasswordStrength = (TextView)findViewById(R.id.tvPasswordStrength);

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

        char c;
        upperChars = lowerChars = numbers = specialChars = otherChars = strengthPoints = passwordLengthGlobal = seguridad = 0;
        int passwordLength = passwordText.length();
        passwordLengthGlobal = passwordText.length();

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

        if (strengthPoints <= 4)
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

    public void Confirmar(View v){

        String pass1 = et_pass.getText().toString();
        String pass2 = et_confirm_pass.getText().toString();

        if (pass1.isEmpty() || pass2.isEmpty()) {

            if (pass1.isEmpty()){
                et_pass.setError("Este campo es obligatorio");
            }
            if (pass2.isEmpty()){
                et_confirm_pass.setError("Este campo es obligatorio");
            }

        }

        else if(strengthPoints <= 6 && seguridad == 0){

            if (strengthPoints > 4) {
                Toast.makeText(this, "La contraseña ingresada no es muy segura, presione el boton nuevamente para omitir la restricción", Toast.LENGTH_LONG).show();
                seguridad = 1;
            }
            if (passwordLengthGlobal <= 10)
                et_pass.setError("La contraseña es muy debil, se recomienda una clave con más de 10 caracteres");
            else if (upperChars == 0)
                et_pass.setError("La contraseña es muy debil, se recomienda el uso de letras mayúsculas");
            else if (lowerChars == 0)
                et_pass.setError("La contraseña es muy debil, se recomienda el uso de letras minúsculas");
            else if (numbers == 0)
                et_pass.setError("La contraseña es muy debil, se recomienda el uso de numeros");
            else if (specialChars == 0)
                et_pass.setError("La contraseña es muy debil, se recomienda el uso de caracteres especiales como '@' o '_'");

        }
        else
        {
            if (pass1.equals(pass2)) {
                AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "administracion", null, 1);
                SQLiteDatabase BaseDatabase = admin.getWritableDatabase();
                ContentValues registro = new ContentValues();
                Intent j = getIntent();
                Bundle extras = j.getExtras();
                String correo = extras.getString("correo");
                registro.put("usu_pass", pass1);
                BaseDatabase.update("usuario", registro, "usu_email='"+correo+"'", null);
                BaseDatabase.close();
                Toast.makeText(this, correo, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, MainMenu.class);
                startActivity(i);
            }

            else {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
