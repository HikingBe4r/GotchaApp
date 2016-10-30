//---------------------------------------------------------------
// 가입액티비티. 이메일, 비밀번호를 입력하면 데이터베이스에 저장해
// 로그인시 사용하기 위한 항목이다.
//---------------------------------------------------------------
package com.example.hikingbear.a7k_gotcha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hikingbear.database.DBManager;

public class SignupActivity extends Activity {

    DBManager dbManager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DBManager(getApplicationContext(), "Account_list.db", null, 1);
        setContentView(R.layout.activity_signup);


        System.out.println("===========================================================================");
        System.out.println(" final DBManager dbManager ");
        System.out.println("===========================================================================");
        // DB에 저장될 속성입력받는다
        final EditText etEmail = (EditText) findViewById(R.id.signup_email);
        final EditText etPassword = (EditText) findViewById(R.id.signup_password);
        final EditText etPassword_Confirm = (EditText) findViewById(R.id.signup_password_confirm); // 비밀번호 확인할때 사용

        Button btnSignup = (Button) findViewById(R.id.signupButton);
        Button btnBack = (Button) findViewById(R.id.backButton);

        // 쿼리결과 입력
        final TextView tvResult = (TextView) findViewById(R.id.tv_result);

        tvResult.setText(dbManager.PrintData()); // db확인용. 테스트끝나면 지워라.

        // 가입 (db insert)
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // insert into 테이블명 values (값, 값, 값, ... );
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                String passwordcf = etPassword_Confirm.getText().toString();

                // 가입조건

                // 1. password와 passwordcf 가 일치하는지 확인.
                if(password.equals(passwordcf)) {
                    // 2. email이 db내에 없는것만 가입가능.
                    if (dbManager.DepulicateEmail(email)) {
                        // 중복되면 true, 중복안되면 false
                        Toast.makeText(SignupActivity.this, "중복된 email입니다.", Toast.LENGTH_SHORT).show();
                    } else {

                        //.dbManager.insert ("insert into ACCOUNT_LIST values (null, '"+ email +"', " + password + ");");
                        dbManager.insert("insert into ACCOUNT_LIST values(null, '" + email + "', '" + password + "');");
                        Toast.makeText(SignupActivity.this, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}