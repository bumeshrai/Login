package com.example.cgmei.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cgmei.login.R;

public class UserAreaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        String auth_key = intent.getStringExtra("auth_key");
        String userid = intent.getStringExtra("userid");

        TextView tvWelcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);
        EditText etUsername = (EditText) findViewById(R.id.etUsername);
        EditText etCode = (EditText) findViewById(R.id.etCode);
        EditText etUserid = (EditText) findViewById(R.id.etId);

        // Display user details
        String message = "Access Code for user:  " + username;
        tvWelcomeMsg.setText(message);
        etUsername.setText(username);
        etCode.setText(auth_key);
        etUserid.setText(userid);
    }
}
