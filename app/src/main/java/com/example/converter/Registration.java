package com.example.converter;

import android.graphics.drawable.Drawable;

import java.io.IOException;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

/** Активность регистрации
 * @author J.C
 */

public class Registration extends AppCompatActivity {
    /**Поля ввода данных при регистрации*/
    TextInputLayout email_lay, confirm_lay, password_lay;
    TextInputEditText email_text,confirm_text,password_text;
    /** Кнопка регистрации */
    Button button;
    /** Валидатор почты */
    EmailValidator emailValidator;
    /** Изображение валидности логина */
    Drawable drawable;
    boolean IsCorrect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        button = (Button) findViewById(R.id.btnRegister);

        email_lay = (TextInputLayout) findViewById(R.id.email_lay);
        email_text = (TextInputEditText) findViewById(R.id.email_text);

        confirm_lay = (TextInputLayout) findViewById(R.id.confirm_password);
        confirm_text = (TextInputEditText) findViewById(R.id.confirm_password_text);

        password_lay = (TextInputLayout) findViewById(R.id.password);
        password_text = (TextInputEditText) findViewById(R.id.password_text);

        email_lay.setPlaceholderText("Login must be your email");

        emailValidator = new EmailValidator();

        try {
            final String BASE_URL = (Util.getProperty("BASE_URL",getApplicationContext()));
            //Here you can get sensitive vars from config.properties
        } catch (IOException e) {
            e.printStackTrace();
        }

        confirm_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals(password_text.getText().toString())){
                    confirm_lay.setError("Password doesn't match");
            }else
                    confirm_lay.setError(null);

            }
        });

        email_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(emailValidator.validate(s.toString())){
                    drawable = Registration.this.getResources().getDrawable(R.drawable.ic_check);
                    email_text.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                    IsCorrect = true;
                }else{
                    drawable = getBaseContext().getResources().getDrawable(R.drawable.ic_close);
                    email_text.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                    IsCorrect = false;
                }
            }
        });


        //todo
        //Enabling button pressed while everything is ok\or make error message if anything is not alright

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                //Send data to server and register

            }
        });

    }
}

