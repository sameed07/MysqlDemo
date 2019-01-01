package com.vchat.muhammadfaizan.mysqldemo;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity  {

    EditText edtUsername,edtPassword,edtEmail;
    Button btnRegister;
    TextView txtLogin;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtUsername = findViewById(R.id. edtUsername);
        edtPassword = findViewById(R.id.edtPass);
        edtEmail = findViewById(R.id.edtEmail);
        btnRegister = findViewById(R.id.btnRegister);
        mDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

//        edtEmail = findViewById(R.id.edtEmail);
//        edtPassword = findViewById(R.id.edtPass);
//        btnLogin = findViewById(R.id.btnLogin);
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                login();
//            }
//        });

    }

    private void registerUser() {

        final String email = edtEmail.getText().toString().trim();
        final String pass = edtPassword.getText().toString().trim();
        final String name = edtUsername.getText().toString().trim();

        mDialog.setMessage("Registering User....");
        mDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.ROOT_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mDialog.dismiss();
                        try {
                            Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                mDialog.hide();
                Toast.makeText(MainActivity.this, ""+ error.getMessage(), Toast.LENGTH_LONG).show();
                Log.i("dxdiag", error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();
                params.put("user_name",name);
                params.put("user_email", email);
                params.put("user_password", pass);

                return params;
            }
        };

        RequestQueue requestQueue =  Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


//    public void login(){
//
//        StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.10.3/loginapp/login.php",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                       // Toast.makeText(MainActivity.this, ""+ response, Toast.LENGTH_SHORT).show();
//
//                         if(response.contains("1")){
//                             startActivity(new Intent(MainActivity.this,HomeActivity.class));
//                         }else{
//                             Toast.makeText(MainActivity.this, "Invalid username or password",
//                                     Toast.LENGTH_LONG).show();
//                         }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String,String> params = new HashMap<>();
//                params.put("username",edtEmail.getText().toString());
//                params.put("password",edtPassword.getText().toString());
//
//                return params;
//            }
//        };
//
//        Volley.newRequestQueue(this).add(request);
//    }
}
