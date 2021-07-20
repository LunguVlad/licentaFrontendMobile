package com.licenta.frontend.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.licenta.frontend.android.models.ListaPlata;

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
        TextView tvEnel = (TextView) convertView.findViewById(R.id.enel);
        TextView tvSuez = (TextView) convertView.findViewById(R.id.suez);
        TextView tvApaCalda = (TextView) convertView.findViewById(R.id.apaCalda);
        TextView tvApaRece = (TextView) convertView.findViewById(R.id.apaRece);
        TextView tvDiferentaApaCalda = (TextView) convertView.findViewById(R.id.diferentaApaCalda);
        TextView tvDiferentaApaRece = (TextView) convertView.findViewById(R.id.diferentaApaRece);
        TextView tvCheltuieliComuneCotaIndiviza = (TextView) convertView.findViewById(R.id.cheltComuneCotaIndiv);
        TextView tvCheltuieliComuneNumarPersoane = (TextView) convertView.findViewById(R.id.cheltComuneNumarPersoane);
        TextView tvCheltuieliComuneNumarApartamente = (TextView) convertView.findViewById(R.id.cheltComuneNumarApartamente);
        TextView tvScara = (TextView) convertView.findViewById(R.id.scara);
        TextView tvRestanteIntretinere = (TextView) convertView.findViewById(R.id.restanteIntretinere);

        TextView total = (TextView) convertView.findViewById(R.id.total);

        tvApartament.setText("Apartament: " + " " + Integer.valueOf(listaPlata.getApartament()).toString());
        tvScara.setText("Scara: " + " " + listaPlata.getScara());
        tvEnel.setText("Enel: " + " " + listaPlata.getEnel());
        tvSuez.setText("Gaze: " + " " + listaPlata.getGaze());
        tvApaCalda.setText("Apa calda: " + " " + listaPlata.getApaCalda());
        tvApaRece.setText("Apa rece: " + " " + listaPlata.getApaRece());
        tvDiferentaApaCalda.setText("Diferenta apa calda: " + " " + listaPlata.getDiferentaApaCalda());
        tvDiferentaApaRece.setText("Diferenta apa rece: " + " " + listaPlata.getDiferentaApaRece());
        tvCheltuieliComuneCotaIndiviza.setText("Cheltuieli comune pe cota indiviza: " + " " + listaPlata.getCheltuieliComunePeCotaIndiviza());
        tvCheltuieliComuneNumarPersoane.setText("Cheltuieli comune pe numar de persoane: " + " " + listaPlata.getCheltuieliRepartizatePeNumarDePersoane());
        tvCheltuieliComuneNumarApartamente.setText("Cheltuieli comune pe numar de apartamente" + " " + listaPlata.getCheltuieliRepartizatePeApartament());
        tvRestanteIntretinere.setText("Restante: " + " " + listaPlata.getRestanteIntretinere());
        total.setText("Total de plata: " + Double.valueOf(listaPlata.getTotalPlata()).toString());

        return convertView;
    }
}
