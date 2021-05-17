package com.licenta.frontend.android;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private int id;
    private String lastName;
    private String firstName;
    private String email;
    private String password;
    private int phoneNumber;
    private int accountType;
    private int scara;
    private int apartament;
    private int nrPersoane;
    private double cotaIndiviza;

    public User(int id, String lastName, String firstName, String email, String password, int phoneNumber, int accountType, int scara, int apartament, int nrPersoane, double cotaIndiviza) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.accountType = accountType;
        this.scara = scara;
        this.apartament = apartament;
        this.nrPersoane = nrPersoane;
        this.cotaIndiviza = cotaIndiviza;
    }

    protected User(Parcel in) {
        id = in.readInt();
        lastName = in.readString();
        firstName = in.readString();
        email = in.readString();
        password = in.readString();
        phoneNumber = in.readInt();
        accountType = in.readInt();
        scara = in.readInt();
        apartament = in.readInt();
        nrPersoane = in.readInt();
        cotaIndiviza = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(lastName);
        dest.writeString(firstName);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeInt(phoneNumber);
        dest.writeInt(accountType);
        dest.writeInt(scara);
        dest.writeInt(apartament);
        dest.writeInt(nrPersoane);
        dest.writeDouble(cotaIndiviza);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public int getScara() {
        return scara;
    }

    public void setScara(int scara) {
        this.scara = scara;
    }

    public int getApartament() {
        return apartament;
    }

    public void setApartament(int apartament) {
        this.apartament = apartament;
    }

    public int getNrPersoane() {
        return nrPersoane;
    }

    public void setNrPersoane(int nrPersoane) {
        this.nrPersoane = nrPersoane;
    }

    public double getCotaIndiviza() {
        return cotaIndiviza;
    }

    public void setCotaIndiviza(double cotaIndiviza) {
        this.cotaIndiviza = cotaIndiviza;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", accountType=" + accountType +
                ", scara=" + scara +
                ", apartament=" + apartament +
                ", nrPersoane=" + nrPersoane +
                ", cotaIndiviza=" + cotaIndiviza +
                '}';
    }

}
