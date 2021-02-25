package com.example.converter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

public class Registration extends AppCompatActivity {
    TextInputLayout editText0,editText1,editText2;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        button = (Button) findViewById(R.id.btnRegister);
        editText0 = findViewById(R.id.email);

        editText0.setHelperText("Login must be your email");

        //todo
        //Comparing pass and confirmpass
        //Validation login
        //Change color of hint

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send data to server and register
            }
        });

    }
}

