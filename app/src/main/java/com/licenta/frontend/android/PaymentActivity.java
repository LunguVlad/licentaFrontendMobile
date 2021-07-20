package com.licenta.frontend.android;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.licenta.frontend.android.models.Luna;
import com.licenta.frontend.android.models.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class PaymentActivity extends AppCompatActivity {

    private final int REQUEST_CODE_LOCATION = 10;
    private final String KEY = "sk_test_51J7hVyBOtDf8PITt8nfE31yJbcCJxCJaJXP3lpCv4cGfI1vAz26Vph5uNQG3DRY6WrIYEDhrYyp31rQHZ3VDoBFp00KWdL7fZR";
    private User user;
    private TextView twSuma;
    private TextView twLuna;
    int idListaPlata;
    double totalDePlata=0;
    boolean platit = false;
    private Button buttonPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this,permissions,REQUEST_CODE_LOCATION);
        }

        twSuma = findViewById(R.id.twValoare);
        twLuna = findViewById(R.id.twLuna);
        buttonPay = findViewById(R.id.buttonPay);

        user = getIntent().getParcelableExtra("user");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.144:8080/listaPlata/getListaPlata/" + user.getId();
        JSONObject accountJSON = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, accountJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {



                    totalDePlata = response.getDouble("totalPlata");
                    String luna = response.getString("luna");
                    for(Luna l : Luna.values()){
                        if(l.valoare.equals(luna)){
                            twLuna.setText("Luna " + l);
                        }
                    }

                    idListaPlata = response.getInt("id");

                    aPlatit(idListaPlata);
                    if(platit== false){
                        twSuma.setText("Total de plata: "+ String.valueOf(BigDecimal.valueOf(totalDePlata).setScale(2, RoundingMode.HALF_UP).doubleValue()) + " RON !");
                    }else{
                        twSuma.setText("Ati platit deja suma de " + String.valueOf(BigDecimal.valueOf(totalDePlata).setScale(2, RoundingMode.HALF_UP).doubleValue()) + " RON !");
                    }

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

    public void aPlatit(int idListaPlata){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.144:8080/plata/getPlata/" + idListaPlata;
        JSONObject accountJSON = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, accountJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response!=null){
                    System.out.println("AICI");
                    setPlatit(true);
                    twSuma.setText("Ati platit deja suma de " + String.valueOf(BigDecimal.valueOf(totalDePlata).setScale(2, RoundingMode.HALF_UP).doubleValue()) + " RON !");
                    buttonPay.setEnabled(false);
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

    public void setPlatit(boolean platit){
        this.platit = true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            System.out.println("RESULT 1");
            twSuma.setText("Ati platit deja suma de " + String.valueOf(BigDecimal.valueOf(totalDePlata).setScale(2, RoundingMode.HALF_UP).doubleValue()) + " RON !");
            buttonPay.setEnabled(false);
        }
    }

    public void clickPlateste(View view) {
        Intent it = new Intent(this,CheckoutActivity.class);
        it.putExtra("idListaPlata",idListaPlata);
        startActivityForResult(it,1);
    }
}