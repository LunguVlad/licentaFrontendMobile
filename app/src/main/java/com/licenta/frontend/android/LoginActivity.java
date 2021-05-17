package com.licenta.frontend.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    private User user;

    private EditText password;
    private EditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        password = (EditText) findViewById(R.id.editTextPassword);
        username = (EditText) findViewById(R.id.editTextUsername);
    }

    public void loginOnClick(View view) throws JSONException {
        String email = username.getText().toString();
        String pass = password.getText().toString();

        System.out.println(email + " " + pass);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://10.0.1.2:8080/user/login/pls";
        JSONObject accountJSON = new JSONObject();
        accountJSON.put("email",email);
        accountJSON.put("password",pass);
        System.out.println(accountJSON.toString());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, accountJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               try {
                   user = new User(response.getInt("id"), response.getString("lastName"), response.getString("firstName"), response.getString("email"), response.getString("password"),
                           response.getInt("phoneNumber"), response.getInt("accountType"), response.getInt("scara"), response.getInt("apartament"), response.getInt("nrPersoane"), response.getDouble("cotaIndiviza"));
                   System.out.println(user);
                   Intent it = new Intent(getApplicationContext(),MainActivity.class);
                   it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                   it.putExtra("user",user);
                   getApplicationContext().startActivity(it);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                System.out.println("LMAO");
            }
        });




        queue.add(jsonObjectRequest);
    }
}