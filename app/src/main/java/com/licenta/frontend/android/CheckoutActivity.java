package com.licenta.frontend.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.ConfirmPaymentIntentParams;
import com.stripe.android.model.ConfirmSetupIntentParams;
import com.stripe.android.model.PaymentIntent;
import com.stripe.android.model.PaymentMethodCreateParams;
import com.stripe.android.view.CardInputWidget;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CheckoutActivity extends AppCompatActivity {

    private final String PUBLISHABLE_KEY = "pk_test_51J7hVyBOtDf8PITt1nGZXIJ0cPP9pkfcu2gzVYt6lKlaV1TWyRNS5C96NjKIfAJN4D6uR7kX8H6fomAQEjINTpNI00XPMDRZg9";
    private String clientSecret;
    private Stripe stripe;
    private int idListaPlata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);


        idListaPlata = getIntent().getIntExtra("idListaPlata",0);
        System.out.println(idListaPlata);

        getClientSecret();




        stripe = new Stripe(
                this,PUBLISHABLE_KEY);

    }

    public void getClientSecret(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.144:8080/pay/" + idListaPlata;
        JSONObject accountJSON = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, accountJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    clientSecret = response.getString("paymentIntent");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });


        queue.add(jsonObjectRequest);
    }

    public void adaugaPlata(){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.144:8080/plata/createPlata/" + idListaPlata;
        JSONObject accountJSON = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, accountJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                finishActivity();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void clickPay(View view){
        CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
        PaymentMethodCreateParams params = cardInputWidget.getPaymentMethodCreateParams();
        if(params != null){
            ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams.createWithPaymentMethodCreateParams(params,clientSecret);
            stripe.confirmPayment(this,confirmParams);

        }
        //this.finish();
    }

    public void finishActivity(){
       this.setResult(1);
       this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



        stripe.onPaymentResult(requestCode, data, new ApiResultCallback<PaymentIntentResult>() {
            @Override
            public void onSuccess(@NotNull PaymentIntentResult paymentIntentResult) {
                Toast.makeText(getApplicationContext(), "Plata efectuata cu succes!", Toast.LENGTH_SHORT).show();
                adaugaPlata();
                //finishActivity(10);
            }

            @Override
            public void onError(@NotNull Exception e) {
//                System.out.println(e.getMessage());
//                Toast.makeText(getApplicationContext(), "Plata nu a fost efectuata", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(CheckoutActivity.this);
                builder.setMessage("Plata nu a fost efectuata! Fonduri insuficiente!");
                builder.setNegativeButton("Ok",null);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
}