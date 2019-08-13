package com.brownie.mynotesapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brownie.mynotesapp.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private EditText etUsername, etPwd;

    private Button btnLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
    }

    private void init()
    {
        etUsername = findViewById(R.id.et_username);
        etPwd = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_login)
        {
            if((!etUsername.getText().toString().isEmpty()) && (!etPwd.getText().toString().isEmpty()))
            {
                if((etUsername.getText().toString().equalsIgnoreCase("Harman")) && (etPwd.getText().toString().equalsIgnoreCase("Harman")))
                {
                    Toast.makeText(this, "Welcome!!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, MainActivity.class));
                }
            }
        }
    }
}
