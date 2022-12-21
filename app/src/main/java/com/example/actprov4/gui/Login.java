package com.example.actprov4.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actprov4.R;
import com.example.actprov4.data_access.GetDatabase;
import com.example.actprov4.myclass.Passport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
ImageButton btn_login;
EditText edtUser, edtPass;
String user;
String pass;
ArrayList<Passport>passports;
public static ArrayList<Passport> myPassport;
Boolean myLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btn_login = (ImageButton) findViewById(R.id.btnLogin);
        user = "";
        pass = "";
        passports = new ArrayList<>();
        myPassport = new ArrayList<>();
        myLogin = false;
        GetPassport();
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = edtUser.getText().toString();
                pass = edtPass.getText().toString();
                if(user.length()==0||pass.length()<6){
                    Toast.makeText(Login.this, "Bạn chưa nhập đầy đủ thông tin tên đăng nhập và mật khẩu", Toast.LENGTH_SHORT).show();
                } else {

                    if(passports.size()>0) {
                        if (CheckLogin(user, pass)) {
                            edtUser.setText("");
                            edtPass.setText("");
                            ToDashBoard();
                        } else {
                            Toast.makeText(Login.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }

    private void GetPassport(){
        String url = "https://planningpto.000webhostapp.com/getpassport.php";
        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject =(JSONObject) response.getJSONObject(i);
                                passports.add(new Passport(
                                        jsonObject.getInt("ID"),
                                        jsonObject.getString("USER"),
                                        jsonObject.getString("PASSWORD"),
                                        jsonObject.getString("FULLNAME"),
                                        jsonObject.getString("POSITION"),
                                        jsonObject.getString("BRANCH"),
                                        jsonObject.getString("MOBILE"),
                                        jsonObject.getInt("DECENTRALIZATION")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }});
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private boolean CheckLogin(String a, String b){
        for(int i=0; i<passports.size();i++){
            if(passports.get(i).getUser().equalsIgnoreCase(a)){
                if(passports.get(i).getPass().equals(b)){
                    myPassport.add(passports.get(i));
                    myLogin = true;
                }
            }
        }
        return myLogin;
    }
    private void ToDashBoard(){
        Intent intent = new Intent(Login.this, GetDatabase.class);
        startActivity(intent);
    }
}