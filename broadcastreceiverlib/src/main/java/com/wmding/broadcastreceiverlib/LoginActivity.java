package com.wmding.broadcastreceiverlib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.wmding.broadcastreceiverlib.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    private EditText eTPassword;
    private EditText eTUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView() {
        eTPassword = findViewById(R.id.et_password);
        eTUserName = findViewById(R.id.et_name);

        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = eTUserName.getText().toString();
                String password = eTPassword.getText().toString();

                // todo 模拟登录
                if ("admin".equals(userName) && "12345".equals(password)) {
                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}