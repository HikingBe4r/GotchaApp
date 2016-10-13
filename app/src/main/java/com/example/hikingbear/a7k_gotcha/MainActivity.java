package com.example.hikingbear.a7k_gotcha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.SharedPreferences;   //db에 저장하긴 애매할때. 안드로이드에서 제공해줌.
import android.widget.CheckBox;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {

    EditText idInput, passwordInput;
    Button loginBtn, SignupBtn;
    CheckBox autoLogin;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.layout);

        String CorrectId, CorrectPw;

        idInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);
        loginBtn = (Button) findViewById(R.id.loginButton);
        SignupBtn = (Button) findViewById(R.id.signupButton);


        CorrectId = "admin";
        CorrectPw = "0000";
        /*
        // if autoLogin checked, get input
        if (pref.getBoolean("autoLogin", true)) {
            idInput.setText(pref.getString("id", ""));
            passwordInput.setText(pref.getString("pw", ""));
            autoLogin.setChecked(true);
            // goto mainActivity

        } else {
            // if autoLogin unChecked
            String id = idInput.getText().toString();
            String password = passwordInput.getText().toString();
            Boolean validation = loginValidation(id, password);

            if(validation) {
                Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                if(loginChecked) {
                    // if autoLogin Checked, save values
                    editor.putString("id", id);
                    editor.putString("pw", password);
                    editor.putBoolean("autoLogin", true);
                    editor.commit();
                }
                // goto mainActivity

            } else {
                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                // goto LoginActivity
            }
        }

        // set checkBoxListener
        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    loginChecked = true;
                } else {
                    // if unChecked, removeAll
                    loginChecked = false;
                    editor.clear();
                    editor.commit();
                }
            }
        });
        */

    }
    public void loginBtnClick (View v) {
        //Id = idInput.getText().toString();
        //Pw = passwordInput.getText().toString();
        if(idInput.getText().toString().equals(passwordInput.getText().toString() )){// && passwordInput.getText().toString() == Pw) {
            //System.out.println("헉!");
            Toast.makeText(this, "정답!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, passwordInput.getText().toString() , Toast.LENGTH_LONG).show();
        }
    }

    private boolean loginValidation(String id, String password) {
        if(pref.getString("id","").equals(id) && pref.getString("pw","").equals(password)) {
            // login success
            return true;
        } else if (pref.getString("id","").equals(null)){
            // sign in first
            Toast.makeText(MainActivity.this, "Please Sign in first", Toast.LENGTH_LONG).show();
            return false;
        } else {
            // login failed
            return false;
        }
    }
}
