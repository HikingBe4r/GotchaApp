package com.example.hikingbear.a7k_gotcha;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

// hashkey 얻을때 사용.
import java.security.MessageDigest;
import android.content.pm.Signature;
import android.util.Log;

// facebook에서 씀
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;


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
        setContentView(R.layout.login_layout);

        // 테스트용
        String CorrectId, CorrectPw;
        CorrectId = "admin";
        CorrectPw = "0000";

        idInput = (EditText) findViewById(R.id.emailInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);
        loginBtn = (Button) findViewById(R.id.loginButton);
        SignupBtn = (Button) findViewById(R.id.signupButton);

        // 페이스북 연동 테스트. 해쉬키를 얻을때 사용함.
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);



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
        // 아이디 비밀번호는 가입하면 등록되도록. 그리고 등록된것에서 찾아서 맞는지 확인시킬것.
        if(idInput.getText().toString().equals("admin") && passwordInput.getText().toString().equals("admin")){// && passwordInput.getText().toString() == Pw) {
            //System.out.println("헉!");
            Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "아이디 또는 비밀번호를 다시 확인해주세요.", Toast.LENGTH_LONG).show();
        }
    }
    public void signupBtnClick (View v) {
        Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
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
