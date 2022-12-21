package com.example.actprov4.data_access;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.actprov4.R;
import com.example.actprov4.gui.Login;
import com.example.actprov4.myclass.Conclusion;
import com.example.actprov4.myclass.Passport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GetDatabase extends AppCompatActivity {
    TextView xxx;
    ArrayList<Passport> myPassport;
    ArrayList<Conclusion> listConclusion;
    ArrayList<Conclusion>myListConclusion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_database);
        xxx = (TextView) findViewById(R.id.xxx);
        myPassport = new ArrayList<>();
        Login login = new Login();
        myPassport.add(login.myPassport.get(0));
        listConclusion=new ArrayList<>();
        myListConclusion = new ArrayList<>();
        GetConclusion("https://planningpto.000webhostapp.com/getconclusion.php");
        xxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetMyList(listConclusion);
                Toast.makeText(GetDatabase.this, "" + myListConclusion.size(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void GetConclusion(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.getJSONObject(i);
                        listConclusion.add(new Conclusion(
                                jsonObject.getInt("ID"),
                                jsonObject.getString("BRANCH"),
                                jsonObject.getString("TYPE_OF_MEETINGS"),
                                jsonObject.getString("CONCLUSION"),
                                jsonObject.getString("DATECREATE"),
                                jsonObject.getString("DEADLINE"),
                                jsonObject.getString("CHAIR"),
                                jsonObject.getString("DOER"),
                                jsonObject.getString("OBSERVER"),
                                jsonObject.getInt("STATUS"),
                                jsonObject.getString("CLOSETIME")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void GetMyList(ArrayList<Conclusion>arr){
        if(myPassport.get(0).getDecentralization()==1||
                myPassport.get(0).getDecentralization()==2||
                        myPassport.get(0).getDecentralization()==3){
            Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
                                for(int i=0; i<arr.size();i++){
                                    if(myPassport.get(0).getBranch().equals(arr.get(i).getBranch())){
                                        myListConclusion.add(arr.get(i));
                                    }
            }
        } else{
            Toast.makeText(this, "Normal", Toast.LENGTH_SHORT).show();
            for(int i=0; i<arr.size();i++){
                if(myPassport.get(0).getBranch().equals(arr.get(i).getBranch())&&
                myPassport.get(0).getUser().equals(arr.get(i).getDoer())){
                    myListConclusion.add(arr.get(i));
                }
            }
        }
    }

    public  boolean isInternetConnection() {
        ConnectivityManager connectivityManager =  (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}