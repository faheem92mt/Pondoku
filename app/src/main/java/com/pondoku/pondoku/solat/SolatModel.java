package com.pondoku.pondoku.solat;

public class SolatModel {

    private String fajr;
    private String zuhr;
    private String asr;
    private String maghrib;
    private String isha;
    private String date;

    public SolatModel(String fajr, String zuhr, String asr, String maghrib, String isha, String date) {
        this.fajr = fajr;
        this.zuhr = zuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;
        this.date = date;
    }

    public SolatModel(String fajr) {
        this.fajr = fajr;
    }

    public String getFajr() {
        return fajr;
    }

    public void setFajr(String fajr) {
        this.fajr = fajr;
    }

    public String getZuhr() {
        return zuhr;
    }

    public void setZuhr(String zuhr) {
        this.zuhr = zuhr;
    }

    public String getAsr() {
        return asr;
    }

    public void setAsr(String asr) {
        this.asr = asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsha() {
        return isha;
    }

    public void setIsha(String isha) {
        this.isha = isha;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


}
