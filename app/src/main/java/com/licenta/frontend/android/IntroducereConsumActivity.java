package com.licenta.frontend.android;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.Text;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.TextRecognizerOptions;
import com.licenta.frontend.android.models.User;

import org.json.JSONObject;

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


        valori.put("apartament", "" + user.getApartament());

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

        Bitmap photo = (Bitmap) data.getExtras().get("data");

        InputImage image = InputImage.fromBitmap(photo,0);

        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
            processImage(image,editTextApaReceBaieMare);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            processImage(image,editTextApaReceBaieMica);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            processImage(image,editTextApaReceBucatarie);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 3) {
            processImage(image,editTextApaCaldaBaieMare);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 4) {
            processImage(image,editTextApaCaldaBaieMica);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 5) {
            processImage(image,editTextApaCaldaBucatarie);
        }
    }

    public void processImage(InputImage image, EditText editText){

        TextRecognizer recognizer = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS);

        Task<Text> result = recognizer.process(image).addOnSuccessListener(new OnSuccessListener<Text>() {
            @Override
            public void onSuccess(@NonNull Text text) {

                for(Text.TextBlock block : text.getTextBlocks()){
                    Rect boundingBox = block.getBoundingBox();
                    Point[] cornerPoints = block.getCornerPoints();
                    String info = block.getText();
                    System.out.println(info);
                }
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        System.out.println(e.getMessage());
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<Text>() {
                    @Override
                    public void onComplete(@NonNull Task<Text> task) {
                        String resultText = task.getResult().getText();
                        //System.out.println(resultText.replaceAll("[^0-9]",""));
                        String formattedResult = resultText.replaceAll("[^0-9]","");
                        editText.setText(formattedResult);
                    }
                });
    }

    public void openCamera(View view) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if(view.getId() == R.id.buttonApaReceBaiaMare){
            startActivityForResult(intent, 0);
        }
        if(view.getId() == R.id.buttonApaReceBaiaMica){
            startActivityForResult(intent, 1);
        }
        if(view.getId() == R.id.buttonApaReceBucatarie){
            startActivityForResult(intent, 2);
        }
        if(view.getId() == R.id.buttonApaCaldaBaiaMare){
            startActivityForResult(intent, 3);
        }
        if(view.getId() == R.id.buttonApaCaldaBaiaMica){
            startActivityForResult(intent, 4);
        }
        if(view.getId() == R.id.buttonApaCaldaBucatarie) {
            startActivityForResult(intent, 5);
        }

}}