package com.licenta.frontend.android;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class IntroducereConsumActivity extends AppCompatActivity {

    private User user;
    private EditText editTextApaReceBaieMare;
    private EditText editTextApaReceBaieMica;
    private EditText editTextApaReceBucatarie;
    private EditText editTextApaCaldaBaieMare;
    private EditText editTextApaCaldaBaieMica;
    private EditText editTextApaCaldaBucatarie;
    private final int TAKE_PICTURE = 1;
    private Uri imageUri;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introducere_consum);

        user = getIntent().getParcelableExtra("user");
        editTextApaCaldaBaieMare = findViewById(R.id.editTextApaCaldaBaieMare);
        editTextApaCaldaBaieMica = findViewById(R.id.editTextApaCaldaBaieMica);
        editTextApaCaldaBucatarie = findViewById(R.id.editTextApaCaldaBucatarie);
        editTextApaReceBaieMare = findViewById(R.id.editTextApaReceBaieMare);
        editTextApaReceBaieMica = findViewById(R.id.editTextApaReceBaieMica);
        editTextApaReceBucatarie = findViewById(R.id.editTextApaReceBucatarie);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},1);
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void insertConsumOnClick(View view) {
        Map<String,String> valori = new HashMap<>();
        if(!editTextApaReceBaieMare.getText().equals("")){
            valori.put("apaReceBaieMare", String.valueOf(editTextApaReceBaieMare.getText()));
        }else{
            valori.put("apaReceBaieMare","0");
        }
        if(!editTextApaReceBaieMica.getText().equals("")){
            valori.put("apaReceBaieMica",String.valueOf(editTextApaReceBaieMica.getText()));
        }else{
            valori.put("apaReceBaieMica","0");
        }
        if(!editTextApaReceBucatarie.getText().equals("")){
            valori.put("apaReceBucatarie",String.valueOf(editTextApaReceBucatarie.getText()));
        }else{
            valori.put("apaReceBucatarie","0");
        }
        if(!editTextApaCaldaBaieMare.getText().equals("")){
            valori.put("apaCaldaBaieMare",String.valueOf(editTextApaCaldaBaieMare.getText()));
        }else{
            valori.put("apaCaldaBaiaMare","0");
        }
        if(!editTextApaCaldaBaieMica.getText().equals("")){
            valori.put("apaCaldaBaieMica",String.valueOf(editTextApaCaldaBaieMica.getText()));
        }else{
            valori.put("apaCaldaBaiaMica","0");
        }
        if(!editTextApaCaldaBucatarie.getText().equals("")){
            valori.put("apaCaldaBucatarie",String.valueOf(editTextApaCaldaBucatarie.getText()));
        }else{
            valori.put("apaCaldaBucatarie","0");
        }


        valori.put("apartament", ""+user.getApartament());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            valori.put("luna", "" + DateHelper.getMonth());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            valori.put("an", "" + DateHelper.getYear());
        }

        valori.forEach((val,key)-> System.out.println(val  + " "+ key));

        JSONObject jsonObject = new JSONObject(valori);

        String url = "http://192.168.1.144:8080/consum/locatar/create/" + user.getId();

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                    System.out.println("SUCCESS");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                System.out.println("ERROR");
            }
        });

        queue.add(jsonObjectRequest);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == TAKE_PICTURE) {
            System.out.println("CHECK");
            String result = data.toURI();
            System.out.println(result);
        }
    }

    public void takePhoto() {

    }

    public void openCamera(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        startActivity(intent);

}}