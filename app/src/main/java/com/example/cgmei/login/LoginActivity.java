package com.example.cgmei.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final TextView tvRegisterLink = (TextView) findViewById(R.id.tvRegisterHere);
        final Button btLogin = (Button) findViewById(R.id.btLogin);

        // Go for Registeration
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        // User wants to login
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();


                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            // collect the response and check for success in the database access
                            JSONObject jsonResponse = new JSONObject(response);
                            Log.i("value", jsonResponse.toString());
                            boolean success = jsonResponse.getJSONObject("data").getBoolean("success");
                            String auth_key = jsonResponse.getJSONObject("data").getString("auth_key");
                            String userid = jsonResponse.getJSONObject("data").getString("id");
                            //Log.i("value",  "success: " + String.valueOf(success) + ", auth_key: "+ auth_key);

                            // if successful in username/password, display the captured data
                            if (success) {

                                // save the date to process and hand over
                                Intent intent = new Intent(LoginActivity.this, UserAreaActivity.class);
                                intent.putExtra("username", username);
                                intent.putExtra("auth_key", auth_key);
                                intent.putExtra("userid", userid);
                                LoginActivity.this.startActivity(intent);
                            } else {
                                // username/password or database access failed
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                // pass on the username/password and let Volley do the request/response routine
                // and pass back the retrieved values
                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }
}
