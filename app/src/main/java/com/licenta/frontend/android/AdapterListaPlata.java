package com.licenta.frontend.android;

import android.app.Activity;
import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterListaPlata extends ArrayAdapter<ListaPlata> {

    private List<ListaPlata> listaPlata;
    private static LayoutInflater inflater = null;

    public AdapterListaPlata(@NonNull Context context, int resource, @NonNull List<ListaPlata> listaPlata) {
        super(context, resource, listaPlata);


        this.listaPlata = listaPlata;

        //inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount(){
        return listaPlata.size();
    }


//    public User getItem(int position){
//        return listaUseri.get(position);
//    }


    @Nullable
    @Override
    public ListaPlata getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ListaPlata listaPlata = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_template,parent,false);
        }

        TextView tvApartament = (TextView) convertView.findViewById(R.id.apartament);
        TextView tvEnelGaze = (TextView) convertView.findViewById(R.id.enel);
        TextView tvApaCalda = (TextView) convertView.findViewById(R.id.apaCalda);
        TextView tvApaRece = (TextView) convertView.findViewById(R.id.apaRece);
        TextView tvCheltuieliComune = (TextView) convertView.findViewById(R.id.cheltComune);
        TextView total = (TextView) convertView.findViewById(R.id.total);

        tvApartament.setText("Apartament: " + Integer.valueOf(listaPlata.getApartament()).toString());
        tvEnelGaze.setText("Enel + gaze: " + Double.valueOf(listaPlata.getEnel()+listaPlata.getGaze()).toString());
        tvApaCalda.setText("Apa calda: " + Double.valueOf(listaPlata.getApaCalda()).toString());
        tvApaRece.setText("Apa rece: " + Double.valueOf(listaPlata.getApaRece()).toString());
        tvCheltuieliComune.setText("Cheltuieli comune: " + 15.5);
        total.setText("Total de plata: " + Double.valueOf(listaPlata.getTotalPlata()).toString());

        return convertView;
    }
}
