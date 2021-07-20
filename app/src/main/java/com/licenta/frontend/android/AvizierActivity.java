package com.licenta.frontend.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.licenta.frontend.android.models.ListaPlata;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class AvizierActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avizier);

        int numarBloc = getIntent().getIntExtra("numarBloc",0);
        List<ListaPlata> lista = new ArrayList<>();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://192.168.1.144:8080/listaPlata/getListaPlati/" + numarBloc;
        JSONArray accountJSON = new JSONArray();
        System.out.println(accountJSON.toString());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, accountJSON, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    for(int i=0;i<response.length();++i){
                        ListaPlata listaPlata = new ListaPlata();
                        JSONObject jsonObject = response.getJSONObject(i);
                        listaPlata.setApartament(jsonObject.getJSONObject("user").getInt("apartament"));
                        listaPlata.setScara(jsonObject.getJSONObject("user").getInt("scara"));
                        listaPlata.setApaCalda(jsonObject.getDouble("apaCalda"));
                        listaPlata.setApaRece(jsonObject.getDouble("apaRece"));
                        listaPlata.setEnel(jsonObject.getDouble("enel"));
                        listaPlata.setGaze(jsonObject.getDouble("gaze"));
                        listaPlata.setDiferentaApaCalda(jsonObject.getDouble("diferentaApaCalda"));
                        listaPlata.setDiferentaApaRece(jsonObject.getDouble("diferentaApaRece"));
                        listaPlata.setCheltuieliComunePeCotaIndiviza(jsonObject.getDouble("cheltuieliComunePeCotaIndiviza"));
                        listaPlata.setCheltuieliRepartizatePeNumarDePersoane(jsonObject.getDouble("cheltuieliRepartizatePeNumarDePersoane"));
                        listaPlata.setCheltuieliRepartizatePeApartament(jsonObject.getDouble("cheltuieliRepartizatePeApartament"));
                        listaPlata.setRestanteIntretinere(jsonObject.getDouble("restanteInteretinere"));
                        listaPlata.setTotalPlata(BigDecimal.valueOf(jsonObject.getDouble("totalPlata")).setScale(2, RoundingMode.HALF_UP).doubleValue());
                        lista.add(listaPlata);
                    }
                    AdapterListaPlata adapter = new AdapterListaPlata(getApplicationContext(),1,lista);

                    ListView listView = (ListView) findViewById(R.id.listViewPlata);
                    listView.setAdapter(adapter);

                }catch(Exception ex){

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });




        queue.add(jsonArrayRequest);


//        lista.add(new ListaPlata(1,50.2,30.5,27.4,22,130.1));
//        lista.add(new ListaPlata(2,70.2,30.5,27.4,22,150.1));
//
//        AdapterListaPlata adapter = new AdapterListaPlata(getApplicationContext(),1,lista);
//
//        ListView listView = (ListView) findViewById(R.id.listViewPlata);
//        listView.setAdapter(adapter);
    }
}