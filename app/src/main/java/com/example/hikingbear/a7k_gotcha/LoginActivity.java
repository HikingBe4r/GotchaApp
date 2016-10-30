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
import com.example.hikingbear.database.DBManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.widget.LoginButton;


import com.example.hikingbear.database.DBManager;

public class LoginActivity extends AppCompatActivity {

    EditText idInput, passwordInput;
    Button loginBtn, SignupBtn;
    CheckBox autoLogin;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Boolean loginChecked;

    DBManager dbManager;    // 로그인시 db체크
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // facebook init
        GetHashKey();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.login_layout);

        idInput = (EditText) findViewById(R.id.login_email);
        passwordInput = (EditText) findViewById(R.id.login_password);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);
        loginBtn = (Button) findViewById(R.id.loginButton);
        SignupBtn = (Button) findViewById(R.id.signupButton);
    }

    public void loginBtnClick (View v) {

        // 아이디 비밀번호는 가입하면 등록되도록. 그리고 등록된것에서 찾아서 맞는지 확인시킬것.
        if(idInput.getText().toString().equals("admin") && passwordInput.getText().toString().equals("admin")) {
            //System.out.println("헉!");
            Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "아이디 또는 비밀번호를 다시 확인해주세요.", Toast.LENGTH_LONG).show();
        }
    }
    public void signupBtnClick (View v) {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        //Toast.makeText(this, "준비중입니다.", Toast.LENGTH_LONG).show();
    }

    protected void GetHashKey () {
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
    }


}
