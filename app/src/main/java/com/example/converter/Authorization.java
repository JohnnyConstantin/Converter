package com.example.converter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Authorization extends Activity {

    EditText editText1,editText2;
    TextView textView;
    Button button1,button2;
    EmailValidator emailValidator;
    String login;
    Drawable drawable;
    boolean IsCorrect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        button1 = (Button) findViewById(R.id.btnLogin);
        button2 = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        emailValidator = new EmailValidator();
        editText1 = (EditText) findViewById(R.id.email);


        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(emailValidator.validate(s.toString())){
                    drawable = Authorization.this.getResources().getDrawable(R.drawable.ic_check);
                    editText1.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                    IsCorrect = true;
                }else{
                    drawable = getBaseContext().getResources().getDrawable(R.drawable.ic_close);
                    editText1.setCompoundDrawablesWithIntrinsicBounds(null,null,drawable,null);
                    IsCorrect = false;
                }
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsCorrect){
                    Intent i = new Intent(Authorization.this,MainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(Authorization.this, "Введен некорректный логин.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
