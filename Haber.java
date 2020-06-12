package com.example.mehme.haberappk;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Haber implements Serializable {
    
    public class HaberResim implements Serializable{
        @SerializedName("Length")
        private long length;

        public long getLength() {
            return length;
        }

        public void setLength(long length) {
            this.length = length;
        }
    }

    @SerializedName("Id")private long id;
    @SerializedName("Haber_Baslik")private String haberBaslik;
    @SerializedName("Haber_İcerik")private String haberIcerik;
    @SerializedName("Haber_Tur")private String haberTur;
    @SerializedName("Haber_Resim")private HaberResim haberResim;
    @SerializedName("Haber_Begenme")private long haberBegenme;
    @SerializedName("Haber_Begenmeme")private long haberBegenmeme;
    @SerializedName("Haber_YayinTarih")private String haberYayinTarih;
    @SerializedName("Haber_Goruntulenme")private long haberGoruntulenme;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHaberBaslik() {
        return haberBaslik;
    }

    public void setHaberBaslik(String haberBaslik) {
        this.haberBaslik = haberBaslik;
    }

    public String getHaberIcerik() {
        return haberIcerik;
    }

    public void setHaberIcerik(String haberIcerik) {
        this.haberIcerik = haberIcerik;
    }

    public String getHaberTur() {
        return haberTur;
    }

    public void setHaberTur(String haberTur) {
        this.haberTur = haberTur;
    }

    public HaberResim getHaberResim() {
        return haberResim;
    }

    public void setHaberResim(HaberResim haberResim) {
        this.haberResim = haberResim;
    }

    public long getHaberBegenme() {
        return haberBegenme;
    }

    public void setHaberBegenme(long haberBegenme) {
        this.haberBegenme = haberBegenme;
    }

    public long getHaberBegenmeme() {
        return haberBegenmeme;
    }

    public void setHaberBegenmeme(long haberBegenmeme) {
        this.haberBegenmeme = haberBegenmeme;
    }

    public String getHaberYayinTarih() {
        return haberYayinTarih;
    }

    public void setHaberYayinTarih(String haberYayinTarih) {
        this.haberYayinTarih = haberYayinTarih;
    }

    public long getHaberGoruntulenme() {
        return haberGoruntulenme;
    }

    public void setHaberGoruntulenme(long haberGoruntulenme) {
        this.haberGoruntulenme = haberGoruntulenme;
    }
}
