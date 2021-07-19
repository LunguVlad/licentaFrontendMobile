package com.licenta.frontend.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class AvizierActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avizier);

        List<ListaPlata> lista = new ArrayList<>();
        lista.add(new ListaPlata(1,50.2,30.5,27.4,22,130.1));
        lista.add(new ListaPlata(2,70.2,30.5,27.4,22,150.1));

        AdapterListaPlata adapter = new AdapterListaPlata(getApplicationContext(),1,lista);

        ListView listView = (ListView) findViewById(R.id.listViewPlata);
        listView.setAdapter(adapter);
    }
}