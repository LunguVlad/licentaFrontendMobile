package com.licenta.frontend.android;

public class ListaPlata {
    private double enel;
    private double gaze;
    private double apaRece;
    private double apaCalda;
    private double totalPlata;
    private int apartament;

    public ListaPlata(int apartament, double enel, double gaze, double apaRece, double apaCalda, double totalPlata) {
        this.enel = enel;
        this.gaze = gaze;
        this.apaRece = apaRece;
        this.apaCalda = apaCalda;
        this.totalPlata = totalPlata;
        this.apartament = apartament;
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
