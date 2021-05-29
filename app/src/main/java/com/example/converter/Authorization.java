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
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.converter.tools.EmailValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Активность авторизации
 * @author Vadim
 */
public class Authorization extends Activity {

    /** Поля логина и пароля */
    EditText login_edText, password_edText;
    /** Кнопки логина и регистрации */
    Button login_button,registration_button;
    /** Валидатор почты */
    EmailValidator emailValidator;
    /** Изображение валидности логина */
    Drawable drawable;
    boolean IsCorrect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        login_button = (Button) findViewById(R.id.btnLogin);
        registration_button = (Button) findViewById(R.id.btnLinkToRegisterScreen);
        emailValidator = new EmailValidator();
        login_edText = (EditText) findViewById(R.id.email);
        password_edText = (EditText) findViewById(R.id.password);


        login_edText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            
            /**
             * Проверка введенного email
             * c помощью класса EmailValidator
             * @see     EmailValidator
             */
            @Override
            public void afterTextChanged(Editable s) {
                if(emailValidator.validate(s.toString())){
                    drawable = Authorization.this.getResources().getDrawable(R.drawable.ic_check);
                    login_edText.setCompoundDrawablesWithIntrinsicBounds(null,null, drawable,null);
                    IsCorrect = true;
                }else{
                    drawable = getBaseContext().getResources().getDrawable(R.drawable.ic_close);
                    login_edText.setCompoundDrawablesWithIntrinsicBounds(null,null, drawable,null);
                    IsCorrect = false;
                }
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(IsCorrect){
                    Client c = new Client();
                    String response = null;
                    Map<String, String> data = new HashMap<>();
                    data.put("login", login_edText.getText().toString());
                    data.put("password", password_edText.getText().toString());
                    try {
                        response = c.execute("POST", "/login", data.toString()).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(response);
//                    Intent i = new Intent(Authorization.this,MainActivity.class);

//          НЕ ТРОГАТЬ! ЗАГОТОВКА ДЛЯ ПЕРЕДАЧИ МЫЛА НА ШАПКУ ПРОФИЛЯ!
//                    i.putExtra("Profile_login", "mail@gmail.com");

//                    startActivity(i);
                }else{
                    Toast.makeText(Authorization.this, "?????? ???????????? ?????.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        registration_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Authorization.this, Registration.class);
                startActivity(i);
            }
        });

    }
}
