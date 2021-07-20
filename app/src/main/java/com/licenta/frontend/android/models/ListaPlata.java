package com.licenta.frontend.android.models;

public class ListaPlata {
    private double enel;
    private double gaze;
    private double apaRece;
    private double apaCalda;
    private double totalPlata;
    private int apartament;
    private int scara;
    private double cheltuieliRepartizatePeNumarDePersoane;
    private double cheltuieliRepartizatePeApartament;
    private double cheltuieliComunePeCotaIndiviza;
    private double diferentaApaRece;
    private double diferentaApaCalda;
    private double restanteIntretinere;

    public ListaPlata(){

    }

    public ListaPlata(int apartament, double enel, double gaze, double apaRece, double apaCalda, double totalPlata) {
        this.enel = enel;
        this.gaze = gaze;
        this.apaRece = apaRece;
        this.apaCalda = apaCalda;
        this.totalPlata = totalPlata;
        this.apartament = apartament;
    }

    public double getRestanteIntretinere() {
        return restanteIntretinere;
    }

    public void setRestanteIntretinere(double restanteIntretinere) {
        this.restanteIntretinere = restanteIntretinere;
    }

    public int getScara() {
        return scara;
    }

    public void setScara(int scara) {
        this.scara = scara;
    }

    public double getCheltuieliRepartizatePeNumarDePersoane() {
        return cheltuieliRepartizatePeNumarDePersoane;
    }

    public void setCheltuieliRepartizatePeNumarDePersoane(double cheltuieliRepartizatePeNumarDePersoane) {
        this.cheltuieliRepartizatePeNumarDePersoane = cheltuieliRepartizatePeNumarDePersoane;
    }

    public double getCheltuieliRepartizatePeApartament() {
        return cheltuieliRepartizatePeApartament;
    }

    public void setCheltuieliRepartizatePeApartament(double cheltuieliRepartizatePeApartament) {
        this.cheltuieliRepartizatePeApartament = cheltuieliRepartizatePeApartament;
    }

    public double getCheltuieliComunePeCotaIndiviza() {
        return cheltuieliComunePeCotaIndiviza;
    }

    public void setCheltuieliComunePeCotaIndiviza(double cheltuieliComunePeCotaIndiviza) {
        this.cheltuieliComunePeCotaIndiviza = cheltuieliComunePeCotaIndiviza;
    }

    public double getDiferentaApaRece() {
        return diferentaApaRece;
    }

    public void setDiferentaApaRece(double diferentaApaRece) {
        this.diferentaApaRece = diferentaApaRece;
    }

    public double getDiferentaApaCalda() {
        return diferentaApaCalda;
    }

    public void setDiferentaApaCalda(double diferentaApaCalda) {
        this.diferentaApaCalda = diferentaApaCalda;
    }

    public double getEnel() {
        return enel;
    }

    public void setEnel(double enel) {
        this.enel = enel;
    }

    public double getGaze() {
        return gaze;
    }

    public void setGaze(double gaze) {
        this.gaze = gaze;
    }

    public double getApaRece() {
        return apaRece;
    }

    public void setApaRece(double apaRece) {
        this.apaRece = apaRece;
    }

    public double getApaCalda() {
        return apaCalda;
    }

    public void setApaCalda(double apaCalda) {
        this.apaCalda = apaCalda;
    }

    public double getTotalPlata() {
        return totalPlata;
    }

    public void setTotalPlata(double totalPlata) {
        this.totalPlata = totalPlata;
    }

    public int getApartament() {
        return apartament;
    }

    public void setApartament(int apartament) {
        this.apartament = apartament;
    }

    @Override
    public String toString() {
        return "ListaPlata{" +
                "enel=" + enel +
                ", gaze=" + gaze +
                ", apaRece=" + apaRece +
                ", apaCalda=" + apaCalda +
                ", totalPlata=" + totalPlata +
                ", apartament=" + apartament +
                '}';
    }
}
