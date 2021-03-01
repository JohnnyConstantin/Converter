package com.example.converter;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.Objects;

public class Registration extends AppCompatActivity {
    TextInputLayout email_lay, confirm_lay, password_lay;
    TextInputEditText email_text,confirm_text,password_text;
    Button button;
    EmailValidator emailValidator;
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
                    confirm_lay.setError("Passwords doesn't match");
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
        //Validation login
        //Enabling button pressed while everything is ok

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
                //Send data to server and register
            }
        });

    }
}

