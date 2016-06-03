package com.example.cgmei.login;

/**
 * Created by umesh on 31/5/16.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "http://cmrlvent.co.in/logAndroid/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String username, int age, String password, String name, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
