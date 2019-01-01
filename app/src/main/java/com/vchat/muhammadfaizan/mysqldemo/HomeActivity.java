package com.vchat.muhammadfaizan.mysqldemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {

    private EditText edtUsername,edtPass;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        edtUsername = findViewById(R.id.edtUsername);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser();
            }
        });
    }

    private void loginUser() {
        //    public void login(){

        StringRequest request = new StringRequest(Request.Method.POST, 
                Constants.ROOT_LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(MainActivity.this, ""+ response, Toast.LENGTH_SHORT).show();

                         if(response.contains("1")){
                             //startActivity(new Intent(MainActivity.this,HomeActivity.class));
                             Toast.makeText(HomeActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                         }else{
                             Toast.makeText(HomeActivity.this, "Invalid username or password",
                                     Toast.LENGTH_LONG).show();
                         }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("username",edtUsername.getText().toString());
                params.put("password",edtPass.getText().toString());

                return params;
            }
        };

        Volley.newRequestQueue(this).add(request);
    }
    
}
